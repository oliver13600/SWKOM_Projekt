package com.paperless.services.impl;

import com.paperless.persistence.entities.*;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.DocumentDTO;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
public class OcrServiceTest {

    @InjectMocks
    private OcrService ocrService;

    @Mock
    private MinioClient minioClient;

    @Mock
    private RabbitTemplate rabbitTemplate;

    private final String bucketName = "test-bucket";

    private static final String OCR_DOCUMENT_OUT_QUEUE_NAME = "OCR_DOCUMENT_OUT";

    @BeforeEach
    void setUp() {
        ocrService.setBucketName(bucketName);
    }

    @Test
    void testProcessDocument() throws Exception {
        String minioPath = "test/document.pdf";
        String ocrResult = "This is the OCR result.";

        // Mock MinioClient to return a sample InputStream
        GetObjectResponse getObjectResponse = Mockito.mock(GetObjectResponse.class);
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        when(minioClient.getObject(any(GetObjectArgs.class))).thenReturn(getObjectResponse);

        // Spy on OcrService to mock doOcr method
        OcrService spyOcrService = Mockito.spy(ocrService);
        doReturn(ocrResult).when(spyOcrService).doOcr(any());

        // Execute processDocument
        spyOcrService.processDocument(minioPath);

        // Verify interactions
        verify(minioClient).getObject(any(GetObjectArgs.class));
        verify(spyOcrService).doOcr(any());
        verify(rabbitTemplate).convertAndSend(eq("OCR_DOCUMENT_OUT"), eq(ocrResult));
    }
}


