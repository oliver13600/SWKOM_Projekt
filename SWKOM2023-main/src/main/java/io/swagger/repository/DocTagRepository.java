
package io.swagger.repository;

import io.swagger.entity.DocTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocTagRepository extends JpaRepository<DocTagEntity, Long> {

    // Custom repository methods based on the service implementation

    Optional<DocTagEntity> findBySlug(String slug);

    Optional<DocTagEntity> findByName(String name);

    List<DocTagEntity> findByColor(String color);

    List<DocTagEntity> findByMatchingAlgorithm(Long matchingAlgorithm);

    List<DocTagEntity> findByIsInsensitive(Boolean isInsensitive);

    List<DocTagEntity> findByIsInboxTag(Boolean isInboxTag);

    List<DocTagEntity> findByDocumentCount(Long documentCount);
}


