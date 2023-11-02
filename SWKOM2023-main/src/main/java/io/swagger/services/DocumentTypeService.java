package io.swagger.services;

import io.swagger.model.DocumentType;

import java.util.List;

public interface DocumentTypeService {

    DocumentType createDocumentType(DocumentType documentType);

    DocumentType getDocumentTypeById(Long id);

    List<DocumentType> getAllDocumentTypes();

    DocumentType updateDocumentType(Long id, DocumentType documentType);

    void deleteDocumentType(Long id);


}

