package com.paperless.services.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * GetDocument200ResponsePermissionsView
 */

@JsonTypeName("GetDocument_200_response_permissions_view")
@Generated(value = "com.paperless.codegen.languages.SpringCodegen", date = "2023-10-22T12:32:07.613318Z[Etc/UTC]")
public class PermissionsView {

  @Valid
  private List<Integer> users = new ArrayList<>();

  @Valid
  private List<Integer> groups = new ArrayList<>();

  public PermissionsView() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PermissionsView(List<Integer> users, List<Integer> groups) {
    this.users = users;
    this.groups = groups;
  }

  public PermissionsView users(List<Integer> users) {
    this.users = users;
    return this;
  }

  public PermissionsView addUsersItem(Integer usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
  */
  @NotNull 
  @Schema(name = "users", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("users")
  public List<Integer> getUsers() {
    return users;
  }

  public void setUsers(List<Integer> users) {
    this.users = users;
  }

  public PermissionsView groups(List<Integer> groups) {
    this.groups = groups;
    return this;
  }

  public PermissionsView addGroupsItem(Integer groupsItem) {
    if (this.groups == null) {
      this.groups = new ArrayList<>();
    }
    this.groups.add(groupsItem);
    return this;
  }

  /**
   * Get groups
   * @return groups
  */
  @NotNull 
  @Schema(name = "groups", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("groups")
  public List<Integer> getGroups() {
    return groups;
  }

  public void setGroups(List<Integer> groups) {
    this.groups = groups;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PermissionsView permissionsView = (PermissionsView) o;
    return Objects.equals(this.users, permissionsView.users) &&
        Objects.equals(this.groups, permissionsView.groups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users, groups);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetDocument200ResponsePermissionsView {\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
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

