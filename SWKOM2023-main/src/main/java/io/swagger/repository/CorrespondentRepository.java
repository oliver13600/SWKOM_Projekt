package io.swagger.repository;

import io.swagger.entity.CorrespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondentRepository extends JpaRepository<CorrespondentEntity, Long> {

    // Here you can add any custom queries if required,
    // but JpaRepository provides most of the basic CRUD operations.

}
