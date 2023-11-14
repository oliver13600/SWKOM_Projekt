package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.UiSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UiSettingsRepository extends JpaRepository<UiSettings, Integer> {
}
