package com.paperless.elasticsearch;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.paperless.services.dto.GetDocuments200ResponseResultsInner;
import lombok.Data;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import javax.validation.Valid;


import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Document
 */
@Data
@Document(indexName = "document")
@Generated(value = "com.paperless.codegen.languages.SpringCodegen", date = "2023-10-22T12:32:07.613318Z[Etc/UTC]")
public class EsDocumentDTO {
    //document for elastic search because the original document is making problems with elastic search (nosql dont like relations) and we need to have a document for elastic search and a document for the database
    @Id
    private Integer id;

    private String title;

    private String content;


}


