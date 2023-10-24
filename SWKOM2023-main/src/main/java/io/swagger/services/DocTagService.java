package io.swagger.services;

import io.swagger.model.DocTag;

import java.util.List;

public interface DocTagService {

    DocTag createDocTag(DocTag docTag);

    DocTag getDocTagById(Long id);

    List<DocTag> getAllDocTags();

    DocTag updateDocTag(Long id, DocTag docTag);

    void deleteDocTag(Long id);

    // Additional methods based on DocTag fields

    DocTag getDocTagBySlug(String slug);

    DocTag getDocTagByName(String name);

    List<DocTag> getDocTagsByColor(String color);

    List<DocTag> getDocTagsByMatchingAlgorithm(Long matchingAlgorithm);

    List<DocTag> getDocTagsByIsInsensitive(Boolean isInsensitive);

    List<DocTag> getDocTagsByIsInboxTag(Boolean isInboxTag);

    List<DocTag> getDocTagsByDocumentCount(Long documentCount);
}

