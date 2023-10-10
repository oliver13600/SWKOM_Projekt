package io.swagger.services;

import io.swagger.entity.DocumentEntity;
import io.swagger.repository.DocumentRepository;
import io.swagger.services.DocumentService;
import io.swagger.services.dto.DocumentDTO;
import io.swagger.services.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
/*
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public DocumentDTO getDocumentById(Integer id) {
        return null;
    }

    @Override
    public DocumentDTO getDocumentById(Long id) {
        Optional<DocumentEntity> documentEntityOptional = documentRepository.findById(Math.toIntExact(id));
        if (documentEntityOptional.isPresent()) {
            DocumentEntity documentEntity = documentEntityOptional.get();
            return documentMapper.toDTO(documentEntity);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentDTO> getAllDocuments() {
        List<DocumentEntity> documentEntities = documentRepository.findAll(); // Fix here
        return documentEntities.stream().map(documentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        DocumentEntity documentEntity = documentMapper.toEntity(documentDTO); // Fix here
        DocumentEntity savedDocumentEntity = documentRepository.save(documentEntity); // Fix here
        return documentMapper.toDTO(savedDocumentEntity); // Fix here
    }

    @Override
    public DocumentDTO updateDocument(Integer id, DocumentDTO documentDTO) {
        return null;
    }

    @Override
    public void deleteDocument(Integer id) {

    }

    @Override
    @Transactional
    public DocumentDTO updateDocument(Long id, DocumentDTO documentDTO) {
        if (!documentRepository.existsById(Math.toIntExact(id))) {
            throw new RuntimeException("Document with id " + id + " not found");
        }
        DocumentEntity documentEntity = documentMapper.toEntity(documentDTO); // Fix here
        documentEntity.setId(Math.toIntExact(id));  // Ensure the ID is set for the update
        DocumentEntity updatedDocumentEntity = documentRepository.save(documentEntity); // Fix here
        return documentMapper.toDTO(updatedDocumentEntity); // Fix here
    }

    @Override
    @Transactional
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(Math.toIntExact(id))) {
            throw new RuntimeException("Document with id " + id + " not found");
        }
        documentRepository.deleteById(Math.toIntExact(id));
    }*/
}
