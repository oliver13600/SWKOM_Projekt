package org.openapitools.repository;

import org.openapitools.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {
    // This interface will inherit standard CRUD operations from JpaRepository.
    // You can also add custom query methods if needed.
}

