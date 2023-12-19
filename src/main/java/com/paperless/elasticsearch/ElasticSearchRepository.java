package com.paperless.elasticsearch;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticSearchRepository extends ElasticsearchRepository<EsDocument, Integer> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"title\": \"?0\"}}]}}")
    List<EsDocument> search(String query);
}
