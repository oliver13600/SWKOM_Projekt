package io.swagger.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import org.threeten.bp.OffsetDateTime;

@Entity
@Table(name = "documents") // Define the table name for this entity
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "correspondent")
    private Integer correspondent;

    @Column(name = "document_type")
    private Integer documentType;

    @Column(name = "storage_path")
    private Integer storagePath;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    // If tags are stored as a comma-separated values or similar
    @Column(name = "tags")
    private String tags; // This might need custom getter and setter logic if stored differently

    @Column(name = "created")
    private OffsetDateTime created;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified")
    private OffsetDateTime modified;

    @Column(name = "added")
    private OffsetDateTime added;

    @Column(name = "archive_serial_number")
    private String archiveSerialNumber;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "archived_file_name")
    private String archivedFileName;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(Integer correspondent) {
        this.correspondent = correspondent;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public Integer getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(Integer storagePath) {
        this.storagePath = storagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public void setModified(OffsetDateTime modified) {
        this.modified = modified;
    }

    public OffsetDateTime getAdded() {
        return added;
    }

    public void setAdded(OffsetDateTime added) {
        this.added = added;
    }

    public String getArchiveSerialNumber() {
        return archiveSerialNumber;
    }

    public void setArchiveSerialNumber(String archiveSerialNumber) {
        this.archiveSerialNumber = archiveSerialNumber;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getArchivedFileName() {
        return archivedFileName;
    }

    public void setArchivedFileName(String archivedFileName) {
        this.archivedFileName = archivedFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentEntity that = (DocumentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(correspondent, that.correspondent) &&
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
        return Objects.hash(id, correspondent, documentType, storagePath, title, content, tags, created, createdDate, modified, added, archiveSerialNumber, originalFileName, archivedFileName);
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
                "id=" + id +
                ", correspondent=" + correspondent +
                ", documentType=" + documentType +
                ", storagePath=" + storagePath +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags='" + tags + '\'' +
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

