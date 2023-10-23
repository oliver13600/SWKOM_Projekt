package org.openapitools.services;

import org.openapitools.model.Correspondent;

import java.util.List;

public interface CorrespondentService {
    Correspondent getCorrespondentById(Long id);
    List<Correspondent> getAllCorrespondents();
    Correspondent createCorrespondent(Correspondent correspondent);
    Correspondent updateCorrespondent(Long id, Correspondent correspondent);
    void deleteCorrespondent(Long id);
}
