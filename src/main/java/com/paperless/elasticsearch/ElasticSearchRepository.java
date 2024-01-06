package com.paperless.elasticsearch;


import com.paperless.persistence.entities.Document;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticSearchRepository extends ElasticsearchRepository<EsDocument, Integer> {

    @Query("{\"bool\": {\"should\": [{\"match\": {\"title\": \"?0\"}}, {\"wildcard\": {\"content\": \"*?0*\"}}]}}")
    List<EsDocument> search(String query);

}
