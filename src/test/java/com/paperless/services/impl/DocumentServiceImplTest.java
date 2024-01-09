package com.paperless.services.impl;

import com.paperless.elasticsearch.ElasticSearchRepository;
import com.paperless.elasticsearch.EsDocument;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.entities.StoragePath;
import com.paperless.persistence.repositories.DocumentRepository;
import com.paperless.services.dto.*;
import com.paperless.services.mapper.DocumentMapper;
import com.paperless.services.mapper.GetDocument200ResponseMapper;
import com.paperless.services.mapper.UpdateDocument200ResponseMapper;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DocumentServiceImplTest {

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @Mock
    private GetDocument200ResponseMapper getDocument200ResponseMapper;

    @Mock
    private UpdateDocument200ResponseMapper updateDocument200ResponseMapper;

    @Mock
    private MinioClient minioClient;

    @Mock
    private RabbitMQSender rabbitMQSender;

    @Mock
    private OcrService ocrService;

    @Mock
    private ElasticSearchRepository elasticSearchRepository;

    private final String bucketName = "test-bucket"; // Set a test bucket name


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        documentService.setBucketName("test-bucket");
    }

    @Test
    void testUploadDocument() {

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setTitle(JsonNullable.of("Test Titel"));
        documentDTO.setOriginalFileName(JsonNullable.of("Test"));
        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setDocumentType(JsonNullable.of(1));
        documentDTO.setTags(JsonNullable.of(integerList));
        documentDTO.setCorrespondent(JsonNullable.of(1));

        // Mock data
        MultipartFile mockFile = new MockMultipartFile(
                "test.pdf",
                "test.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Sample PDF Content".getBytes()
        );

        when(documentMapper.dtoToEntity(any(DocumentDTO.class))).thenReturn(new Document());
        // Call the method to test
        documentService.uploadDocument(documentDTO, mockFile);

        // Verify interactions
        verify(documentMapper, times(1)).dtoToEntity(documentDTO);
    }

    @Test
    @DisplayName("Test Get Documents Successfully")
    void testGetDocuments() {
        // Mock behavior
        when(documentRepository.findAll()).thenReturn(Collections.singletonList(new Document()));
        when(documentMapper.entityToDto(any(Document.class))).thenReturn(new DocumentDTO());

        // Call the method to test
        ResponseEntity<GetDocuments200Response> responseEntity = documentService.getDocuments(1, 10, null, null, null, null, null, null, null);

        // Verify interactions and response
        verify(documentRepository, times(1)).findAll();
        verify(documentMapper, times(1)).entityToDto(any(Document.class));

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        // Additional assertions can be added to verify the contents of the response
    }


    @Test
    @DisplayName("Test Update Document Successfully")
    void testUpdateDocument() {
        // Mock data
        UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest();
        updateDocumentRequest.setTitle("Test Title");
        updateDocumentRequest.setCreatedDate(OffsetDateTime.now().toString());
        updateDocumentRequest.setModified(OffsetDateTime.now().toString());
        updateDocumentRequest.setAdded(OffsetDateTime.now().toString());
        Integer documentId = 1;

        // Mock behavior
        when(documentRepository.getReferenceById(documentId)).thenReturn(new Document());
        when(documentRepository.save(any(Document.class))).thenReturn(new Document());
        when(updateDocument200ResponseMapper.entityToDto(any(Document.class))).thenReturn(new UpdateDocument200Response());

        when(elasticSearchRepository.findById(documentId)).thenReturn(Optional.of(new EsDocument()));

        // Call the method to test
        ResponseEntity<UpdateDocument200Response> responseEntity = documentService.updateDocument(documentId, updateDocumentRequest);

        // Verify interactions and response
        verify(documentRepository, times(1)).getReferenceById(documentId);
        verify(documentRepository, times(1)).save(any(Document.class));
        verify(updateDocument200ResponseMapper, times(1)).entityToDto(any(Document.class));

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }
}
