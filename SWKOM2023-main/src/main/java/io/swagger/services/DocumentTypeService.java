package io.swagger.services;

import io.swagger.model.DocumentType;

import java.util.List;

public interface DocumentTypeService {

    DocumentType createDocumentType(DocumentType documentType);

    DocumentType getDocumentTypeById(Long id);

    List<DocumentType> getAllDocumentTypes();

    DocumentType updateDocumentType(Long id, DocumentType documentType);

    void deleteDocumentType(Long id);

    // Additional methods based on DocumentType fields
/*
    DocumentType getDocumentTypeBySlug(String slug);

    DocumentType getDocumentTypeByName(String name);

    DocumentType getDocumentTypeByMatch(String match);

    List<DocumentType> getDocumentTypesByMatchingAlgorithm(Long matchingAlgorithm);

    List<DocumentType> getDocumentTypesByIsInsensitive(Boolean isInsensitive);

    List<DocumentType> getDocumentTypesByDocumentCount(Long documentCount);*/
}

