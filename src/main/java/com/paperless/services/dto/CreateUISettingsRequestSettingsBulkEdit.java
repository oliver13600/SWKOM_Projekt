package com.paperless.services.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * CreateUISettingsRequestSettingsBulkEdit
 */

@JsonTypeName("CreateUISettings_request_settings_bulk_edit")
@Generated(value = "com.paperless.codegen.languages.SpringCodegen", date = "2023-10-22T12:32:07.613318Z[Etc/UTC]")
public class CreateUISettingsRequestSettingsBulkEdit {

  private Boolean applyOnClose;

  private Boolean confirmationDialogs;

  public CreateUISettingsRequestSettingsBulkEdit() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateUISettingsRequestSettingsBulkEdit(Boolean applyOnClose, Boolean confirmationDialogs) {
    this.applyOnClose = applyOnClose;
    this.confirmationDialogs = confirmationDialogs;
  }

  public CreateUISettingsRequestSettingsBulkEdit applyOnClose(Boolean applyOnClose) {
    this.applyOnClose = applyOnClose;
    return this;
  }

  /**
   * Get applyOnClose
   * @return applyOnClose
  */
  @NotNull 
  @Schema(name = "apply_on_close", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("apply_on_close")
  public Boolean getApplyOnClose() {
    return applyOnClose;
  }

  public void setApplyOnClose(Boolean applyOnClose) {
    this.applyOnClose = applyOnClose;
  }

  public CreateUISettingsRequestSettingsBulkEdit confirmationDialogs(Boolean confirmationDialogs) {
    this.confirmationDialogs = confirmationDialogs;
    return this;
  }

  /**
   * Get confirmationDialogs
   * @return confirmationDialogs
  */
  @NotNull 
  @Schema(name = "confirmation_dialogs", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("confirmation_dialogs")
  public Boolean getConfirmationDialogs() {
    return confirmationDialogs;
  }

  public void setConfirmationDialogs(Boolean confirmationDialogs) {
    this.confirmationDialogs = confirmationDialogs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateUISettingsRequestSettingsBulkEdit createUISettingsRequestSettingsBulkEdit = (CreateUISettingsRequestSettingsBulkEdit) o;
    return Objects.equals(this.applyOnClose, createUISettingsRequestSettingsBulkEdit.applyOnClose) &&
        Objects.equals(this.confirmationDialogs, createUISettingsRequestSettingsBulkEdit.confirmationDialogs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applyOnClose, confirmationDialogs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateUISettingsRequestSettingsBulkEdit {\n");
    sb.append("    applyOnClose: ").append(toIndentedString(applyOnClose)).append("\n");
    sb.append("    confirmationDialogs: ").append(toIndentedString(confirmationDialogs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

