package io.swagger.services.dto;

import java.util.List;
import java.util.Objects;
import org.threeten.bp.OffsetDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDTO extends BaseDTO{

    private Long id;
    private Long correspondent;
    private Long documentType;
    private String storagePath;
    private String title;
    private String content;
    private List<Long> tags;
    private OffsetDateTime created;
    private OffsetDateTime createdDate;
    private OffsetDateTime modified;
    private OffsetDateTime added;
    private String archiveSerialNumber;
    private String originalFileName;
    private String archivedFileName;

    // Default constructor
    public DocumentDTO() {}

    // All arguments constructor
    public DocumentDTO(Long id, Long correspondent, Long documentType, String storagePath, String title, String content, List<Long> tags, OffsetDateTime created, OffsetDateTime createdDate, OffsetDateTime modified, OffsetDateTime added, String archiveSerialNumber, String originalFileName, String archivedFileName) {
        this.id = id;
        this.correspondent = correspondent;
        this.documentType = documentType;
        this.storagePath = storagePath;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.created = created;
        this.createdDate = createdDate;
        this.modified = modified;
        this.added = added;
        this.archiveSerialNumber = archiveSerialNumber;
        this.originalFileName = originalFileName;
        this.archivedFileName = archivedFileName;
    }

    // Getters and Setters for all fields

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DocumentDTO that = (DocumentDTO) o;
        return Objects.equals(correspondent, that.correspondent) &&
                Objects.equals(documentType, that.documentType) &&
                Objects.equals(storagePath, that.storagePath) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(tags, that.tags) &&
                Objects.equals(created, that.created) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(modified, that.modified) &&
                Objects.equals(added, that.added) &&
                Objects.equals(archiveSerialNumber, that.archiveSerialNumber) &&
                Objects.equals(originalFileName, that.originalFileName) &&
                Objects.equals(archivedFileName, that.archivedFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), correspondent, documentType, storagePath, title, content, tags, created, createdDate, modified, added, archiveSerialNumber, originalFileName, archivedFileName);
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", ") +
                ", correspondent=" + correspondent +
                ", documentType=" + documentType +
                ", storagePath='" + storagePath + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", created=" + created +
                ", createdDate=" + createdDate +
                ", modified=" + modified +
                ", added=" + added +
                ", archiveSerialNumber='" + archiveSerialNumber + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", archivedFileName='" + archivedFileName + '\'' +
                '}';
    }


}
