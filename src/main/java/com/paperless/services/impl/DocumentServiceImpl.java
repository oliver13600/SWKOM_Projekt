package com.paperless.services.impl;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.paperless.elasticsearch.ElasticSearchRepository;
import com.paperless.elasticsearch.EsDocument;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.entities.StoragePath;
import com.paperless.persistence.repositories.DocumentRepository;
import com.paperless.services.dto.DocumentDTO;
import com.paperless.services.dto.GetDocument200Response;
import com.paperless.services.dto.GetDocuments200Response;
import com.paperless.services.dto.UpdateDocument200Response;
import com.paperless.services.dto.UpdateDocumentRequest;
import com.paperless.services.mapper.DocumentMapper;
import com.paperless.services.mapper.GetDocument200ResponseMapper;
import com.paperless.services.mapper.UpdateDocument200ResponseMapper;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final GetDocument200ResponseMapper getDocument200ResponseMapper;
    private final UpdateDocument200ResponseMapper updateDocument200ResponseMapper;

    private final MinioClient minioClient;

    private final RabbitMQSender rabbitMQSender;

    private final OcrService ocrService;

    private final ElasticSearchRepository elasticSearchRepository;

    @Value("${minio.bucketName}")
    private String bucketName;


    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper, GetDocument200ResponseMapper getDocument200ResponseMapper, UpdateDocument200ResponseMapper updateDocument200ResponseMapper, MinioClient minioClient, RabbitMQSender rabbitMQSender, OcrService ocrService, ElasticSearchRepository elasticSearchRepository){
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.getDocument200ResponseMapper = getDocument200ResponseMapper;
        this.updateDocument200ResponseMapper = updateDocument200ResponseMapper;
        this.minioClient = minioClient;
        this.rabbitMQSender = rabbitMQSender;
        this.ocrService = ocrService;
        this.elasticSearchRepository = elasticSearchRepository;
    }

    @Override
    public GetDocument200Response getDocument(Integer id, Integer page, Boolean fullPerms) {
        Document foundEntity =  documentRepository.getReferenceById(id);
        return getDocument200ResponseMapper.entityToDto(foundEntity);
    }


    @Override
    public void uploadDocument(DocumentDTO documentDTO, MultipartFile file) {
        // Set metadata in documentDTO
        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setModified(OffsetDateTime.now());
        documentDTO.content("");
        log.info("Document metadata set.");
        // Convert DTO to entity
        Document documentToBeSaved = documentMapper.dtoToEntity(documentDTO);
        documentToBeSaved.setChecksum("checksum"); // Consider generating a real checksum

        try {
            String pdfContent = readPdfFile(file);
            documentToBeSaved.setContent(pdfContent);
            log.info("PDF content: "+ pdfContent + " read and set in DocumentDTO.");
        } catch (Exception e) {
            log.error("Error while reading PDF content.", e);
            return;
        }

        documentToBeSaved.setStorageType("pdf"); // Ensure this is correct
        documentToBeSaved.setMimeType("application/pdf"); // Use MIME type for PDF
        log.info("Document entity created.");

        // Generate object name and upload to MinIO
        String objectName = generateRandomName() + getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            log.info("Document uploaded to Minio.");

            // Send the MinIO path to RabbitMQ for OCR processing
            //String minioPath = bucketName + "/" + objectName;
            String minioPath = objectName;
            rabbitMQSender.sendToOcrDocumentInQueue(minioPath);
            log.info("Document path sent to OCR queue.");

            // Set storage path in document entity
            StoragePath pathToFile = new StoragePath();
            pathToFile.setPath(minioPath);
            pathToFile.setName(file.getOriginalFilename());
            pathToFile.setIsInsensitive(false); // Set a default value for isInsensitive
            pathToFile.setMatch("defaultMatchValue"); // Set a default value for match
            pathToFile.setMatchingAlgorithm(1); // Set a default value for matchingAlgorithm
            documentToBeSaved.setStoragePath(pathToFile);
            log.info("Document storage path set.");

            // Save document entity
            //documentRepository.save(documentToBeSaved);
            Document savedDocument = documentRepository.save(documentToBeSaved);
            indexDocument(savedDocument);
            log.info("Document saved and added to Queue for OCR processing.");
        } catch (Exception e) {
            log.error("Error while uploading file to Minio or sending to queue.", e);

        }
    }

    public String readPdfFile(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            PdfReader reader = new PdfReader(inputStream);
            StringBuilder content = new StringBuilder();

            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                content.append(PdfTextExtractor.getTextFromPage(reader, i));
            }

            return content.toString();
        }
        catch (Exception e){
            log.error("Error while reading PDF file.", e);
            return null;
        }

    }

    public List<DocumentDTO> searchDocuments(String query) {
        try {List<EsDocument> searchResults = elasticSearchRepository.search(query);
        log.info("Search results: " + searchResults);

        return searchResults.stream()
                .map(this::convertToDocumentDTO)
                .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while searching documents.", e);
            return null;
        }
    }

    private DocumentDTO convertToDocumentDTO(EsDocument esDocument) {
        try{
        // Assuming you have a constructor or setter methods to set properties
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setTitle(JsonNullable.of(esDocument.getTitle()));
        documentDTO.setContent(JsonNullable.of(esDocument.getContent()));
        // Set other properties as needed
        return documentDTO;
        } catch (Exception e) {
            log.error("Error while converting ES document to DTO.", e);
            return null;
        }
    }

    @Override
    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        List<DocumentDTO> documentDTOS = new ArrayList<>();

        log.info("Query String: -----------------------------------> " + query);
        log.info("Page: " + page + " PageSize: " + pageSize + " Ordering: " + ordering);
        if(query == null) {
            for (Document document : documentRepository.findAll()) {
                documentDTOS.add(documentMapper.entityToDto(document));
            }
        } else if(query.isEmpty()){
            for (Document document : documentRepository.findAll()) {
                documentDTOS.add(documentMapper.entityToDto(document));
            }
        } else { // ElasticSearch
            documentDTOS = searchDocuments(query);
        }

        GetDocuments200Response sampleResponse = new GetDocuments200Response();
        if (sampleResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sampleResponse.setCount(documentDTOS.size());
        sampleResponse.setNext(1);
        sampleResponse.setPrevious(1);
        sampleResponse.addAllItem(1);

        for(DocumentDTO documentDTO : documentDTOS) {
            sampleResponse.addResultsItem(documentDTO.toGetDocuments200ResponseResultsInner());
        }

        return ResponseEntity.ok(sampleResponse);
    }


    @Override
    public ResponseEntity<UpdateDocument200Response> updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest) {
        Document document = documentRepository.getReferenceById(id);
        EsDocument esDocument = elasticSearchRepository.findById(id).orElse(null);

        document.updateByUpdateDocumentRequest(updateDocumentRequest);

        if(esDocument != null){
            esDocument.updateByUpdateEsDocumentRequest(updateDocumentRequest);
            //elasticSearchRepository.deleteById(esDocument.getId());
            elasticSearchRepository.save(esDocument);
        }
        documentRepository.save(document);

        UpdateDocument200Response updateDocument200Response = updateDocument200ResponseMapper.entityToDto(document);
        //exception handling
        if (updateDocument200Response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Document updated: " + document);
        return ResponseEntity.ok(updateDocument200Response);
    }

    @Override
    public void sendDocumentToDB(String minioPath, String OCRText) {
        //setContent of document
        Document document = documentRepository.findByStoragePath_Path(minioPath);
        if(document == null){
            log.error("Document not found.");
        }
        else {
            log.info("Document found: " + document);
            document.setContent(OCRText);
            documentRepository.save(document);
            if(document.getContent() != null){
                log.info("Document content set.");
            }
            else {
                log.error("Document content not set.");
            }
        }
    }

    private String generateRandomName() {
        return UUID.randomUUID().toString();
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        return (lastDotIndex != -1) ? filename.substring(lastDotIndex) : "";
    }

    public void indexDocument(Document document) {
        try {
            EsDocument esDocument = convertToEsDocument(document);
            log.info("Indexing document: " + esDocument);
            elasticSearchRepository.save(esDocument);
        }
        catch (Exception e) {
            log.error("Error while indexing document.", e);
        }
    }


    private EsDocument convertToEsDocument(Document document) {
        EsDocument esDocument = new EsDocument();
        //exception handling
        if (esDocument == null) {
            log.error("Error converting document to ES document.");
            return null;
        }
        esDocument.setId(document.getId());
        esDocument.setTitle(document.getTitle());
        esDocument.setContent(document.getContent()); // Assuming you have a content field
        // Map other fields as necessary
        log.info("Converted document to ES document: " + esDocument);

        return esDocument;
    }

    @Override
    public DocumentDTO getDocumentById(Integer id) {
        // Retrieve the document entity by ID
        Document document = documentRepository.findById(id).orElse(null);
        log.info("Document found: " + document);
        // Check if the document entity is found
        if (document == null) {
            log.error("Document not found with ID: " + id);
            return null;
        }

        // Map the document entity to a DTO and return it
        return documentMapper.entityToDto(document);
    }

    @Override
    public void setBucketName(String s) {

        if (s == null) {
            log.error("Bucket name is null.");
            return;
        }
        bucketName = s;
        log.info("Bucket name set to: " + bucketName);


    }

    @Override
    public void deleteDocument(Integer id) {
        // Check if the document exists
        Document document = documentRepository.findById(id).orElse(null);
        EsDocument esDocument = elasticSearchRepository.findById(id).orElse(null);

        if (document != null) {
            // Delete the document from the database
            documentRepository.deleteById(id);
            log.info("Document deleted from database with ID: " + id);
        } else {
            log.error("Could not delete document from database with ID: " + id);
        }

        if(esDocument != null){
            // Delete the document from elasticsearch
            elasticSearchRepository.deleteById(id);
            log.info("Document deleted from elasticsearch with ID: " + id);
        } else {
            log.error("Could not delete document in elasticsearch with ID: " + id);
        }

        // Delete the document from MinIO
        try {
            String objectName = document.getStoragePath().getPath();
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
            log.info("Document deleted from MinIO with object name: " + objectName);
        } catch (Exception e) {
            log.error("Error while deleting document from MinIO", e);
        }

    }

    @Override
    public Resource getDocumentThumbnail(Integer id) {

        Document document = documentRepository.findById(id).orElse(null);

        if (document != null) {
            String documentPath = document.getStoragePath().getPath(); // Assuming this is the path to the PDF file in MinIO


            try (InputStream pdfStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(documentPath)
                    .build());
                 PDDocument pdDocument = PDDocument.load(pdfStream)) {

                PDFRenderer renderer = new PDFRenderer(pdDocument);
                BufferedImage image = renderer.renderImageWithDPI(0, 300); // Render the first page

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos); // You can choose "jpeg" or "png" depending on your requirement
                log.info("Thumbnail created for document with ID: " + id);

                return new ByteArrayResource(baos.toByteArray());
            } catch (Exception e) {
                log.error("Error getting document from minio document thumbnail: ", e);
                return null;
            }
        } else {
            log.info("File not found in Minio - ID: " + id);
            return null;
        }

    }
    @Override
    public Resource downloadDocument(Integer id) {
        Document document = documentRepository.findById(id).orElse(null);

        if (document != null) {
            String documentPath = document.getStoragePath().getPath(); // Assuming this is the path to the PDF file in MinIO

            try (InputStream pdfStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(documentPath)
                    .build())) {

                // Read the PDF content into a byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = pdfStream.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                log.info("Document downloaded from MinIO with ID: " + id);

                return new ByteArrayResource(baos.toByteArray());
            } catch (Exception e) {
                log.error("Error downloading document from MinIO: ", e);
                return null;
            }
        } else {
            log.info("File not found in Minio - ID: " + id);
            return null;
        }
    }

}
