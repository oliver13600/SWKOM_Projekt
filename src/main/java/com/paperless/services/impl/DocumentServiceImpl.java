package com.paperless.services.impl;

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
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @Value("${minio.bucketName}")
    private String bucketName;


    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper, GetDocument200ResponseMapper getDocument200ResponseMapper, UpdateDocument200ResponseMapper updateDocument200ResponseMapper, MinioClient minioClient, RabbitMQSender rabbitMQSender, OcrService ocrService){
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.getDocument200ResponseMapper = getDocument200ResponseMapper;
        this.updateDocument200ResponseMapper = updateDocument200ResponseMapper;
        this.minioClient = minioClient;
        this.rabbitMQSender = rabbitMQSender;
        this.ocrService = ocrService;
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

            documentRepository.save(documentToBeSaved);
            log.info("Document saved and added to Queue for OCR processing.");
        } catch (Exception e) {
            log.error("Error while uploading file to Minio or sending to queue.", e);
            throw new RuntimeException("Failed to upload file to Minio or send to OCR queue", e);
        }
    }

    @Override
    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (Document document : documentRepository.findAll()) {
            documentDTOS.add(documentMapper.entityToDto(document));
        }


        GetDocuments200Response sampleResponse = new GetDocuments200Response();
        sampleResponse.setCount(100);
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

        document.updateByUpdateDocumentRequest(updateDocumentRequest);

        documentRepository.save(document);

        UpdateDocument200Response updateDocument200Response = updateDocument200ResponseMapper.entityToDto(document);

        return ResponseEntity.ok(updateDocument200Response);
    }

    private String generateRandomName() {
        return UUID.randomUUID().toString();
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        return (lastDotIndex != -1) ? filename.substring(lastDotIndex) : "";
    }

}
