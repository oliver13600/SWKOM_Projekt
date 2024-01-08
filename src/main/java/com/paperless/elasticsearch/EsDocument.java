package com.paperless.elasticsearch;

import com.paperless.services.dto.UpdateDocumentRequest;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;

@Getter
@Setter
@Document(indexName = "document")
public class EsDocument {

    @Id
    private Integer id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "content")
    private String content;

    public void updateByUpdateEsDocumentRequest (UpdateDocumentRequest update) {
        setTitle(update.getTitle());
        setContent(update.getContent());
    }

}

