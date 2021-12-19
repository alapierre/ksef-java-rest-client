/*
 * KSeF
 * Krajowy Systemu e-Faktur
 *
 * OpenAPI spec version: 0.0.2
 * Contact: info.ksef@mf.gov.pl
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.alapierre.ksef.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
/**
 * SessionContextType
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class SessionContextType {
  @SerializedName("contextIdentifier")
  private SubjectIdentifierByType contextIdentifier = null;

  @SerializedName("contextName")
  private SubjectNameType contextName = null;

  @SerializedName("credentialsRoleList")
  private List<CredentialsRoleResponseType> credentialsRoleList = new ArrayList<CredentialsRoleResponseType>();

  public SessionContextType contextIdentifier(SubjectIdentifierByType contextIdentifier) {
    this.contextIdentifier = contextIdentifier;
    return this;
  }

   /**
   * Get contextIdentifier
   * @return contextIdentifier
  **/
  @Schema(required = true, description = "")
  public SubjectIdentifierByType getContextIdentifier() {
    return contextIdentifier;
  }

  public void setContextIdentifier(SubjectIdentifierByType contextIdentifier) {
    this.contextIdentifier = contextIdentifier;
  }

  public SessionContextType contextName(SubjectNameType contextName) {
    this.contextName = contextName;
    return this;
  }

   /**
   * Get contextName
   * @return contextName
  **/
  @Schema(description = "")
  public SubjectNameType getContextName() {
    return contextName;
  }

  public void setContextName(SubjectNameType contextName) {
    this.contextName = contextName;
  }

  public SessionContextType credentialsRoleList(List<CredentialsRoleResponseType> credentialsRoleList) {
    this.credentialsRoleList = credentialsRoleList;
    return this;
  }

  public SessionContextType addCredentialsRoleListItem(CredentialsRoleResponseType credentialsRoleListItem) {
    this.credentialsRoleList.add(credentialsRoleListItem);
    return this;
  }

   /**
   * Get credentialsRoleList
   * @return credentialsRoleList
  **/
  @Schema(required = true, description = "")
  public List<CredentialsRoleResponseType> getCredentialsRoleList() {
    return credentialsRoleList;
  }

  public void setCredentialsRoleList(List<CredentialsRoleResponseType> credentialsRoleList) {
    this.credentialsRoleList = credentialsRoleList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionContextType sessionContextType = (SessionContextType) o;
    return Objects.equals(this.contextIdentifier, sessionContextType.contextIdentifier) &&
        Objects.equals(this.contextName, sessionContextType.contextName) &&
        Objects.equals(this.credentialsRoleList, sessionContextType.credentialsRoleList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contextIdentifier, contextName, credentialsRoleList);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionContextType {\n");

    sb.append("    contextIdentifier: ").append(toIndentedString(contextIdentifier)).append("\n");
    sb.append("    contextName: ").append(toIndentedString(contextName)).append("\n");
    sb.append("    credentialsRoleList: ").append(toIndentedString(credentialsRoleList)).append("\n");
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
