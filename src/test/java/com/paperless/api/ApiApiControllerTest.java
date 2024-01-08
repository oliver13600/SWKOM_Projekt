package com.paperless.api;

import com.paperless.services.dto.*;
import com.paperless.services.impl.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ApiApiControllerTest {

    @Mock
    private DocumentServiceImpl documentServiceImpl;

    @Mock
    private NativeWebRequest request;

    @InjectMocks
    private ApiApiController apiApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRequest() {
        assertNotNull(apiApiController.getRequest());
    }

    @Test
    void getDocument() {
        GetDocument200Response expectedResponse = new GetDocument200Response();
        when(documentServiceImpl.getDocument(anyInt(), anyInt(), anyBoolean())).thenReturn(expectedResponse);

        ResponseEntity<GetDocument200Response> response = apiApiController.getDocument(1, 1, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void uploadDocument() {
        MockMultipartFile file = new MockMultipartFile("document", "test.pdf", "text/plain", "test content".getBytes());
        List<MultipartFile> files = Collections.singletonList(file);

        ResponseEntity<Void> response = apiApiController.uploadDocument("Test Title", OffsetDateTime.now(), 1, Arrays.asList(1, 2), 1, files);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(documentServiceImpl, times(1)).uploadDocument(any(DocumentDTO.class), any(MultipartFile.class));
    }

    @Test
    void uploadDocumentUnsupported() {
        MockMultipartFile file = new MockMultipartFile("document", "test.txt", "text/plain", "test content".getBytes());
        List<MultipartFile> files = Collections.singletonList(file);

        ResponseEntity<Void> response = apiApiController.uploadDocument("Test Title", OffsetDateTime.now(), 1, Arrays.asList(1, 2), 1, files);

        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    void getDocuments() {
        GetDocuments200Response expectedResponse = new GetDocuments200Response();
        when(documentServiceImpl.getDocuments(anyInt(), anyInt(), anyString(), anyString(), anyList(), anyInt(), anyInt(), anyInt(), anyBoolean())).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<GetDocuments200Response> response = apiApiController.getDocuments(1, 10, "query", "ordering", Collections.singletonList(1), 1, 1, 1, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void updateDocument() {
        UpdateDocument200Response expectedResponse = new UpdateDocument200Response();
        UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest();
        when(documentServiceImpl.updateDocument(anyInt(), any(UpdateDocumentRequest.class))).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<UpdateDocument200Response> response = apiApiController.updateDocument(1, updateDocumentRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getDocumentPreview() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setContent(JsonNullable.of("Sample content for preview"));
        when(documentServiceImpl.getDocumentById(anyInt())).thenReturn(documentDTO);

        ResponseEntity<org.springframework.core.io.Resource> response = apiApiController.getDocumentPreview(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        //assertTrue(response.getBody().contains("Sample content"));
    }
}
