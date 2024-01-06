package com.paperless.services.mapper;

import com.paperless.persistence.entities.*;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.DocumentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DocumentMapperTests {

    @Mock
    private CorrespondentRepository correspondentRepository;

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @Mock
    private StoragePathRepository storagePathRepository;

    @InjectMocks
    private DocumentMapper documentMapper = new DocumentMapperImpl();

    public DocumentMapperTests() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void documentMapperEntityToDTOTest() {
        // Mocking repository behavior
        when(correspondentRepository.findById(1)).thenReturn(Optional.of(new Correspondent()));
        when(documentTypeRepository.findById(1)).thenReturn(Optional.of(new DocumentType()));
        when(storagePathRepository.findById(1)).thenReturn(Optional.of(new StoragePath()));

        Document document = new Document();
        document.setTitle("Test");
        document.setContent("This is a test");
        document.setCorrespondent(new Correspondent());
        document.setDocumentType(new DocumentType());
        document.setStoragePath(new StoragePath());

        DocumentDTO documentDTO = documentMapper.entityToDto(document);

        assertEquals(document.getTitle(), documentDTO.getTitle().get());
        assertEquals(document.getContent(), documentDTO.getContent().get());

    }

    @Test
    void documentMapperDtoToEntityTest() {

        when(correspondentRepository.findById(1)).thenReturn(Optional.of(new Correspondent()));
        when(documentTypeRepository.findById(1)).thenReturn(Optional.of(new DocumentType()));
        when(storagePathRepository.findById(1)).thenReturn(Optional.of(new StoragePath()));

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.title("Test");
        documentDTO.content("This is a test");
        documentDTO.correspondent(1);
        documentDTO.documentType(1);
        documentDTO.storagePath(1);

        Document document = documentMapper.dtoToEntity(documentDTO);

        assertEquals(documentDTO.getTitle().get(), document.getTitle());
        assertEquals(documentDTO.getContent().get(), document.getContent());

    }
}
