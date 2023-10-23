package org.openapitools.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "document_type")
@Data  // Lombok annotation for generating getters, setters, toString, etc.
public class DocumentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String slug;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String match;

    @Column(name = "matching_algorithm", nullable = true)
    private Long matchingAlgorithm;

    @Column(name = "is_insensitive", nullable = true)
    private Boolean isInsensitive;

    @Column(name = "document_count", nullable = true)
    private Long documentCount;

    // Equals, HashCode and other methods are already taken care of by Lombok's @Data annotation
}

