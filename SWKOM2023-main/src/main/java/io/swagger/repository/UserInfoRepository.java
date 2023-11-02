package io.swagger.repository;

import io.swagger.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
    // This interface will inherit standard CRUD operations from JpaRepository.
    // You can also add custom query methods if needed.
}

