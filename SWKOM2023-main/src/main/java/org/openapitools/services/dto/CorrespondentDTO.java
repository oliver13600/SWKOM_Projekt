package io.swagger.services.dto;

import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CorrespondentDTO extends BaseDTO{

    private Long id;
    private String slug;
    private String name;
    private String match;
    private Long matchingAlgorithm;
    private Boolean isInsensitive;
    private Long documentCount;
    private OffsetDateTime lastCorrespondence;

    // Default constructor
    public CorrespondentDTO() {}

    // All arguments constructor
    public CorrespondentDTO(Long id, String slug, String name, String match, Long matchingAlgorithm, Boolean isInsensitive, Long documentCount, OffsetDateTime lastCorrespondence) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.match = match;
        this.matchingAlgorithm = matchingAlgorithm;
        this.isInsensitive = isInsensitive;
        this.documentCount = documentCount;
        this.lastCorrespondence = lastCorrespondence;
    }

    // Getters and Setters for all fields

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CorrespondentDTO that = (CorrespondentDTO) o;
        return Objects.equals(slug, that.slug) &&
                Objects.equals(name, that.name) &&
                Objects.equals(match, that.match) &&
                Objects.equals(matchingAlgorithm, that.matchingAlgorithm) &&
                Objects.equals(isInsensitive, that.isInsensitive) &&
                Objects.equals(documentCount, that.documentCount) &&
                Objects.equals(lastCorrespondence, that.lastCorrespondence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), slug, name, match, matchingAlgorithm, isInsensitive, documentCount, lastCorrespondence);
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", ") + // Uses BaseDTO's toString and removes the closing brace
                "slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", match='" + match + '\'' +
                ", matchingAlgorithm=" + matchingAlgorithm +
                ", isInsensitive=" + isInsensitive +
                ", documentCount=" + documentCount +
                ", lastCorrespondence=" + lastCorrespondence +
                '}';
    }
}

