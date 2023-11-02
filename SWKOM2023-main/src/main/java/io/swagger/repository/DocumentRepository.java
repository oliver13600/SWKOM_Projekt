package io.swagger.repository;


import io.swagger.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    // This interface will inherit standard CRUD operations from JpaRepository.
    // You can also add custom query methods if needed.
}

