package org.paperless.persistence.repositories;

import org.paperless.persistence.entities.CorrespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DocumentsCorrespondentRepository extends JpaRepository<CorrespondentEntity, Integer> {
}
