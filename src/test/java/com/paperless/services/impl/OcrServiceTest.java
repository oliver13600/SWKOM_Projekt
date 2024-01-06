package com.paperless.services.impl;

import com.paperless.persistence.entities.*;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.DocumentDTO;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class OcrServiceTest {

    @InjectMocks
    private OcrService ocrService;

    @Mock
    private MinioClient minioClient;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private DocumentService documentService;

    private final String bucketName = "test-bucket"; // Set a test bucket name

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ocrService.setBucketName(bucketName); // Set the bucket name in OcrService
    }

    @Test
    void testProcessDocument() throws Exception {
        // Define test data
        String minioPath = "test/document.jpg";
        String ocrResult = "This is the OCR result.";

        // Mock MinioClient to return a sample GetObjectResponse
        GetObjectResponse getObjectResponse = Mockito.mock(GetObjectResponse.class);
        when(minioClient.getObject(any())).thenReturn(getObjectResponse);

        // Mock Tesseract OCR using spy
        OcrService spyOcrService = Mockito.spy(ocrService);
        doReturn(ocrResult).when(spyOcrService).doOcr(argThat(file -> file.getName().startsWith("ocr_")));

        // Mock DocumentService
        doNothing().when(documentService).sendDocumentToDB(anyString(), anyString());

        // Mock RabbitTemplate
        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString());

        // Call the method to test
        spyOcrService.processDocument(minioPath);

        // Verify that the expected methods were called with specific arguments
        verify(minioClient).getObject(argThat(args -> args.object().equals(minioPath)));
        verify(spyOcrService).doOcr(argThat(file -> file.getName().startsWith("ocr_")));
        verify(documentService).sendDocumentToDB(eq(minioPath), eq(ocrResult));
        verify(rabbitTemplate).convertAndSend(eq("OCR_DOCUMENT_OUT"), eq(ocrResult));
    }


}
