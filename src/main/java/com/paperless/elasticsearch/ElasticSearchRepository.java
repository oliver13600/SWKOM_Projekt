package com.paperless.elasticsearch;


import com.paperless.persistence.entities.Document;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ElasticSearchRepository extends ElasticsearchRepository<EsDocument, Integer> {

    @Query("{\"bool\": {\"should\": [{\"wildcard\": {\"title\": \"*?0*\"}}, {\"wildcard\": {\"content\": \"*?0*\"}}]}}")
    List<EsDocument> search(String query);

    @Override
    Optional<EsDocument> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
