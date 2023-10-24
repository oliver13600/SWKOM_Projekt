package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.InlineResponse2002Notes;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse2002Results
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-24T13:29:17.597026669Z[GMT]")


public class InlineResponse2002Results   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("correspondent")
  private Integer correspondent = null;

  @JsonProperty("document_type")
  private Integer documentType = null;

  @JsonProperty("storage_path")
  private Integer storagePath = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("tags")
  @Valid
  private List<Integer> tags = new ArrayList<Integer>();

  @JsonProperty("created")
  private String created = null;

  @JsonProperty("created_date")
  private String createdDate = null;

  @JsonProperty("modified")
  private String modified = null;

  @JsonProperty("added")
  private String added = null;

  @JsonProperty("archive_serial_number")
  private Integer archiveSerialNumber = null;

  @JsonProperty("original_file_name")
  private String originalFileName = null;

  @JsonProperty("archived_file_name")
  private String archivedFileName = null;

  @JsonProperty("owner")
  private Integer owner = null;

  @JsonProperty("user_can_change")
  private Boolean userCanChange = null;

  @JsonProperty("notes")
  @Valid
  private List<InlineResponse2002Notes> notes = new ArrayList<InlineResponse2002Notes>();

  public InlineResponse2002Results id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public InlineResponse2002Results correspondent(Integer correspondent) {
    this.correspondent = correspondent;
    return this;
  }

  /**
   * Get correspondent
   * @return correspondent
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getCorrespondent() {
    return correspondent;
  }

  public void setCorrespondent(Integer correspondent) {
    this.correspondent = correspondent;
  }

  public InlineResponse2002Results documentType(Integer documentType) {
    this.documentType = documentType;
    return this;
  }

  /**
   * Get documentType
   * @return documentType
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getDocumentType() {
    return documentType;
  }

  public void setDocumentType(Integer documentType) {
    this.documentType = documentType;
  }

  public InlineResponse2002Results storagePath(Integer storagePath) {
    this.storagePath = storagePath;
    return this;
  }

  /**
   * Get storagePath
   * @return storagePath
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getStoragePath() {
    return storagePath;
  }

  public void setStoragePath(Integer storagePath) {
    this.storagePath = storagePath;
  }

  public InlineResponse2002Results title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public InlineResponse2002Results content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public InlineResponse2002Results tags(List<Integer> tags) {
    this.tags = tags;
    return this;
  }

  public InlineResponse2002Results addTagsItem(Integer tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
   **/
  @Schema(required = true, description = "")
      @NotNull

    public List<Integer> getTags() {
    return tags;
  }

  public void setTags(List<Integer> tags) {
    this.tags = tags;
  }

  public InlineResponse2002Results created(String created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public InlineResponse2002Results createdDate(String createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Get createdDate
   * @return createdDate
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public InlineResponse2002Results modified(String modified) {
    this.modified = modified;
    return this;
  }

  /**
   * Get modified
   * @return modified
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public InlineResponse2002Results added(String added) {
    this.added = added;
    return this;
  }

  /**
   * Get added
   * @return added
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getAdded() {
    return added;
  }

  public void setAdded(String added) {
    this.added = added;
  }

  public InlineResponse2002Results archiveSerialNumber(Integer archiveSerialNumber) {
    this.archiveSerialNumber = archiveSerialNumber;
    return this;
  }

  /**
   * Get archiveSerialNumber
   * @return archiveSerialNumber
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getArchiveSerialNumber() {
    return archiveSerialNumber;
  }

  public void setArchiveSerialNumber(Integer archiveSerialNumber) {
    this.archiveSerialNumber = archiveSerialNumber;
  }

  public InlineResponse2002Results originalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
    return this;
  }

  /**
   * Get originalFileName
   * @return originalFileName
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public InlineResponse2002Results archivedFileName(String archivedFileName) {
    this.archivedFileName = archivedFileName;
    return this;
  }

  /**
   * Get archivedFileName
   * @return archivedFileName
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getArchivedFileName() {
    return archivedFileName;
  }

  public void setArchivedFileName(String archivedFileName) {
    this.archivedFileName = archivedFileName;
  }

  public InlineResponse2002Results owner(Integer owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getOwner() {
    return owner;
  }

  public void setOwner(Integer owner) {
    this.owner = owner;
  }

  public InlineResponse2002Results userCanChange(Boolean userCanChange) {
    this.userCanChange = userCanChange;
    return this;
  }

  /**
   * Get userCanChange
   * @return userCanChange
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean isUserCanChange() {
    return userCanChange;
  }

  public void setUserCanChange(Boolean userCanChange) {
    this.userCanChange = userCanChange;
  }

  public InlineResponse2002Results notes(List<InlineResponse2002Notes> notes) {
    this.notes = notes;
    return this;
  }

  public InlineResponse2002Results addNotesItem(InlineResponse2002Notes notesItem) {
    this.notes.add(notesItem);
    return this;
  }

  /**
   * Get notes
   * @return notes
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<InlineResponse2002Notes> getNotes() {
    return notes;
  }

  public void setNotes(List<InlineResponse2002Notes> notes) {
    this.notes = notes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2002Results inlineResponse2002Results = (InlineResponse2002Results) o;
    return Objects.equals(this.id, inlineResponse2002Results.id) &&
        Objects.equals(this.correspondent, inlineResponse2002Results.correspondent) &&
        Objects.equals(this.documentType, inlineResponse2002Results.documentType) &&
        Objects.equals(this.storagePath, inlineResponse2002Results.storagePath) &&
        Objects.equals(this.title, inlineResponse2002Results.title) &&
        Objects.equals(this.content, inlineResponse2002Results.content) &&
        Objects.equals(this.tags, inlineResponse2002Results.tags) &&
        Objects.equals(this.created, inlineResponse2002Results.created) &&
        Objects.equals(this.createdDate, inlineResponse2002Results.createdDate) &&
        Objects.equals(this.modified, inlineResponse2002Results.modified) &&
        Objects.equals(this.added, inlineResponse2002Results.added) &&
        Objects.equals(this.archiveSerialNumber, inlineResponse2002Results.archiveSerialNumber) &&
        Objects.equals(this.originalFileName, inlineResponse2002Results.originalFileName) &&
        Objects.equals(this.archivedFileName, inlineResponse2002Results.archivedFileName) &&
        Objects.equals(this.owner, inlineResponse2002Results.owner) &&
        Objects.equals(this.userCanChange, inlineResponse2002Results.userCanChange) &&
        Objects.equals(this.notes, inlineResponse2002Results.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, correspondent, documentType, storagePath, title, content, tags, created, createdDate, modified, added, archiveSerialNumber, originalFileName, archivedFileName, owner, userCanChange, notes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2002Results {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    correspondent: ").append(toIndentedString(correspondent)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    storagePath: ").append(toIndentedString(storagePath)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    added: ").append(toIndentedString(added)).append("\n");
    sb.append("    archiveSerialNumber: ").append(toIndentedString(archiveSerialNumber)).append("\n");
    sb.append("    originalFileName: ").append(toIndentedString(originalFileName)).append("\n");
    sb.append("    archivedFileName: ").append(toIndentedString(archivedFileName)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    userCanChange: ").append(toIndentedString(userCanChange)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
