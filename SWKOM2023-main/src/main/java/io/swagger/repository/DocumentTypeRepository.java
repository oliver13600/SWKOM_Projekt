
package io.swagger.repository;


import io.swagger.entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Long> {
    // This interface will inherit standard CRUD operations from JpaRepository.
    // You can also add custom query methods if needed.
}

