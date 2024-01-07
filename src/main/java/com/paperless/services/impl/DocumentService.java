package com.paperless.services.impl;

import com.paperless.elasticsearch.EsDocument;
import com.paperless.persistence.entities.Document;
import com.paperless.services.dto.DocumentDTO;
import com.paperless.services.dto.GetDocument200Response;
import com.paperless.services.dto.GetDocuments200Response;
import com.paperless.services.dto.UpdateDocument200Response;
import com.paperless.services.dto.UpdateDocumentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    GetDocument200Response getDocument(Integer id, Integer page, Boolean fullPerms);

    void uploadDocument(DocumentDTO documentDTO, MultipartFile file);

    ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent);

    ResponseEntity<UpdateDocument200Response> updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest);

    void sendDocumentToDB(String minioPath, String OCRText);

    DocumentDTO getDocumentById(Integer id);

    void setBucketName(String s);

    void indexDocument(Document document);

    void deleteDocument(Integer id);
}
