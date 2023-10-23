package org.openapitools.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Data
@Entity
public class DocTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String name;

    private String color;

    @Column(name = "match_value")  // "match" is a reserved keyword in some RDBMS
    private String match;

    @Column(name = "matching_algorithm")
    private Long matchingAlgorithm;

    @Column(name = "is_insensitive")
    private Boolean isInsensitive;

    @Column(name = "is_inbox_tag")
    private Boolean isInboxTag;

    @Column(name = "document_count")
    private Long documentCount;

    // No need for getters and setters since we are using @Data from Lombok
}
