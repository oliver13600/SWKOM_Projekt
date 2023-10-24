package io.swagger.entity;

import javax.persistence.*;
import org.threeten.bp.OffsetDateTime;
import java.util.Objects;
import lombok.Data;

@Data
@Entity
@Table(name = "correspondents")
public class CorrespondentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "match")
    private String match;

    @Column(name = "matching_algorithm")
    private Long matchingAlgorithm;

    @Column(name = "is_insensitive")
    private Boolean isInsensitive;

    @Column(name = "document_count")
    private Long documentCount;

    @Column(name = "last_correspondence")
    private OffsetDateTime lastCorrespondence;


}
