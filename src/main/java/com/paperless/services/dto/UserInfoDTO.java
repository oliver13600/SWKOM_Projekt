package com.paperless.services.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * UserInfo
 */

@Generated(value = "com.paperless.codegen.languages.SpringCodegen", date = "2023-10-22T12:32:07.613318Z[Etc/UTC]")
public class UserInfoDTO {

  private JsonNullable<String> username = JsonNullable.<String>undefined();

  private JsonNullable<String> password = JsonNullable.<String>undefined();

  public UserInfoDTO username(String username) {
    this.username = JsonNullable.of(username);
    return this;
  }

  /**
   * Get username
   * @return username
  */
  
  @Schema(name = "username", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("username")
  public JsonNullable<String> getUsername() {
    return username;
  }

  public void setUsername(JsonNullable<String> username) {
    this.username = username;
  }

  public UserInfoDTO password(String password) {
    this.password = JsonNullable.of(password);
    return this;
  }

  /**
   * Get password
   * @return password
  */
  
  @Schema(name = "password", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("password")
  public JsonNullable<String> getPassword() {
    return password;
  }

  public void setPassword(JsonNullable<String> password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInfoDTO userInfoDTO = (UserInfoDTO) o;
    return equalsNullable(this.username, userInfoDTO.username) &&
        equalsNullable(this.password, userInfoDTO.password);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(username), hashCodeNullable(password));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserInfo {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

