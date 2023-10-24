package io.swagger.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import org.threeten.bp.OffsetDateTime;
import lombok.Data;
// DOCTAG, DOCTYPE, USER ENTITY
@Data
@Entity
@Table(name = "documents") // Define the table name for this entity
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


}

