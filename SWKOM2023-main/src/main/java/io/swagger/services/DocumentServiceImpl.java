package io.swagger.services;

import io.swagger.entity.DocumentEntity;
import io.swagger.model.Document;
import io.swagger.services.mapper.DocumentMapper;
import io.swagger.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public Document getDocumentById(Long id) {
        Optional<DocumentEntity> documentEntityOptional = documentRepository.findById(id);
        if (documentEntityOptional.isPresent()) {
            DocumentEntity documentEntity = documentEntityOptional.get();
            return documentMapper.toDTO(documentEntity);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> getAllDocuments() {
        List<DocumentEntity> documentEntities = documentRepository.findAll(); // Fix here
        return documentEntities.stream().map(documentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Document createDocument(Document document) {
        DocumentEntity documentEntity = documentMapper.toEntity(document); // Fix here
        DocumentEntity savedDocumentEntity = documentRepository.save(documentEntity); // Fix here
        return documentMapper.toDTO(savedDocumentEntity); // Fix here
    }



    @Override
    @Transactional
    public Document updateDocument(Long id, Document document) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document with id " + id + " not found");
        }
        DocumentEntity documentEntity = documentMapper.toEntity(document); // Fix here
        documentEntity.setId(id);  // Ensure the ID is set for the update
        DocumentEntity updatedDocumentEntity = documentRepository.save(documentEntity); // Fix here
        return documentMapper.toDTO(updatedDocumentEntity); // Fix here
    }

    @Override
    @Transactional
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document with id " + id + " not found");
        }
        documentRepository.deleteById(id);
    }
}
