package org.openapitools.services;

import org.openapitools.entity.DocTagEntity;
import org.openapitools.model.DocTag;
import org.openapitools.services.mapper.DocTagMapper;
import org.openapitools.repository.DocTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocTagServiceImpl implements DocTagService {

    @Autowired
    private DocTagRepository docTagRepository;

    @Autowired
    private DocTagMapper docTagMapper;

    @Override
    @Transactional
    public DocTag createDocTag(DocTag docTag) {
        DocTagEntity docTagEntity = docTagMapper.toEntity(docTag);
        DocTagEntity savedDocTag = docTagRepository.save(docTagEntity);
        return docTagMapper.toDTO(savedDocTag);
    }

    @Override
    @Transactional(readOnly = true)
    public DocTag getDocTagById(Long id) {
        Optional<DocTagEntity> docTagEntityOptional = docTagRepository.findById(id);
        return docTagEntityOptional.map(docTagMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocTag> getAllDocTags() {
        List<DocTagEntity> docTags = docTagRepository.findAll();
        return docTags.stream().map(docTagMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DocTag updateDocTag(Long id, DocTag docTag) {
        if (!docTagRepository.existsById(id)) {
            throw new RuntimeException("DocTag with id " + id + " not found");
        }
        DocTagEntity docTagEntity = docTagMapper.toEntity(docTag);
        docTagEntity.setId(id);
        DocTagEntity updatedDocTag = docTagRepository.save(docTagEntity);
        return docTagMapper.toDTO(updatedDocTag);
    }

    @Override
    @Transactional
    public void deleteDocTag(Long id) {
        docTagRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DocTag getDocTagBySlug(String slug) {
        Optional<DocTagEntity> docTagEntityOptional = docTagRepository.findBySlug(slug);
        return docTagEntityOptional.map(docTagMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public DocTag getDocTagByName(String name) {
        Optional<DocTagEntity> docTagEntityOptional = docTagRepository.findByName(name);
        return docTagEntityOptional.map(docTagMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocTag> getDocTagsByColor(String color) {
        List<DocTagEntity> docTagEntities = docTagRepository.findByColor(color);
        return docTagEntities.stream().map(docTagMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocTag> getDocTagsByMatchingAlgorithm(Long matchingAlgorithm) {
        List<DocTagEntity> docTagEntities = docTagRepository.findByMatchingAlgorithm(matchingAlgorithm);
        return docTagEntities.stream().map(docTagMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocTag> getDocTagsByIsInsensitive(Boolean isInsensitive) {
        List<DocTagEntity> docTagEntities = docTagRepository.findByIsInsensitive(isInsensitive);
        return docTagEntities.stream().map(docTagMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocTag> getDocTagsByIsInboxTag(Boolean isInboxTag) {
        List<DocTagEntity> docTagEntities = docTagRepository.findByIsInboxTag(isInboxTag);
        return docTagEntities.stream().map(docTagMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocTag> getDocTagsByDocumentCount(Long documentCount) {
        List<DocTagEntity> docTagEntities = docTagRepository.findByDocumentCount(documentCount);
        return docTagEntities.stream().map(docTagMapper::toDTO).collect(Collectors.toList());
    }
}

