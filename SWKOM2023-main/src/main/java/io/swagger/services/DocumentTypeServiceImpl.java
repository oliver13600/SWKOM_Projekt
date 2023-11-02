package io.swagger.services;

import io.swagger.model.DocumentType;
import io.swagger.entity.DocumentTypeEntity;
import io.swagger.repository.DocumentTypeRepository;
import io.swagger.services.mapper.DocumentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private DocumentTypeMapper documentTypeMapper;

    @Override
    public DocumentType createDocumentType(DocumentType documentType) {

        DocumentTypeEntity documentTypeEntity = documentTypeMapper.toEntity(documentType);
        DocumentTypeEntity savedDocumentType = documentTypeRepository.save(documentTypeEntity);
        return documentTypeMapper.toDTO(savedDocumentType);
    }

    @Override
    public DocumentType getDocumentTypeById(Long id) {
        Optional<DocumentTypeEntity> documentTypeEntityOptional = documentTypeRepository.findById(id);
        return documentTypeEntityOptional.map(documentTypeMapper::toDTO).orElse(null);
    }

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        List<DocumentTypeEntity> documentTypes = documentTypeRepository.findAll();
        return documentTypes.stream().map(documentTypeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DocumentType updateDocumentType(Long id, DocumentType documentType) {
        if (!documentTypeRepository.existsById(id)) {
            throw new RuntimeException("DocumentType with id " + id + " not found");
        }
        DocumentTypeEntity documentTypeEntity = documentTypeMapper.toEntity(documentType);
        documentTypeEntity.setId(id);
        DocumentTypeEntity updatedDocumentType = documentTypeRepository.save(documentTypeEntity);
        return documentTypeMapper.toDTO(updatedDocumentType);
    }

    @Override
    public void deleteDocumentType(Long id) {
        documentTypeRepository.deleteById(id);
    }


}

