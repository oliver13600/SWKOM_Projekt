package io.swagger.entity;

import javax.persistence.*;
import org.threeten.bp.OffsetDateTime;
import java.util.Objects;

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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public Long getMatchingAlgorithm() {
        return matchingAlgorithm;
    }

    public void setMatchingAlgorithm(Long matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
    }

    public Boolean getIsInsensitive() {
        return isInsensitive;
    }

    public void setIsInsensitive(Boolean isInsensitive) {
        this.isInsensitive = isInsensitive;
    }

    public Long getDocumentCount() {
        return documentCount;
    }

    public void setDocumentCount(Long documentCount) {
        this.documentCount = documentCount;
    }

    public OffsetDateTime getLastCorrespondence() {
        return lastCorrespondence;
    }

    public void setLastCorrespondence(OffsetDateTime lastCorrespondence) {
        this.lastCorrespondence = lastCorrespondence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorrespondentEntity that = (CorrespondentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(slug, that.slug) &&
                Objects.equals(name, that.name) &&
                Objects.equals(match, that.match) &&
                Objects.equals(matchingAlgorithm, that.matchingAlgorithm) &&
                Objects.equals(isInsensitive, that.isInsensitive) &&
                Objects.equals(documentCount, that.documentCount) &&
                Objects.equals(lastCorrespondence, that.lastCorrespondence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slug, name, match, matchingAlgorithm, isInsensitive, documentCount, lastCorrespondence);
    }

    @Override
    public String toString() {
        return "CorrespondentEntity{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", match='" + match + '\'' +
                ", matchingAlgorithm=" + matchingAlgorithm +
                ", isInsensitive=" + isInsensitive +
                ", documentCount=" + documentCount +
                ", lastCorrespondence=" + lastCorrespondence +
                '}';
    }
}
