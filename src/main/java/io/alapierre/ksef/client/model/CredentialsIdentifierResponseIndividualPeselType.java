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

/**
 * CredentialsIdentifierResponseIndividualPeselType
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class CredentialsIdentifierResponseIndividualPeselType extends CredentialsIdentifierResponseType {
  @SerializedName("identifier")
  private String credentialsIdentifierResponseIndividualPeselTypeIdentifier = null;

  public CredentialsIdentifierResponseIndividualPeselType credentialsIdentifierResponseIndividualPeselTypeIdentifier(String credentialsIdentifierResponseIndividualPeselTypeIdentifier) {
    this.credentialsIdentifierResponseIndividualPeselTypeIdentifier = credentialsIdentifierResponseIndividualPeselTypeIdentifier;
    return this;
  }

   /**
   * Get credentialsIdentifierResponseIndividualPeselTypeIdentifier
   * @return credentialsIdentifierResponseIndividualPeselTypeIdentifier
  **/
  @Schema(required = true, description = "")
  public String getCredentialsIdentifierResponseIndividualPeselTypeIdentifier() {
    return credentialsIdentifierResponseIndividualPeselTypeIdentifier;
  }

  public void setCredentialsIdentifierResponseIndividualPeselTypeIdentifier(String credentialsIdentifierResponseIndividualPeselTypeIdentifier) {
    this.credentialsIdentifierResponseIndividualPeselTypeIdentifier = credentialsIdentifierResponseIndividualPeselTypeIdentifier;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CredentialsIdentifierResponseIndividualPeselType credentialsIdentifierResponseIndividualPeselType = (CredentialsIdentifierResponseIndividualPeselType) o;
    return Objects.equals(this.credentialsIdentifierResponseIndividualPeselTypeIdentifier, credentialsIdentifierResponseIndividualPeselType.credentialsIdentifierResponseIndividualPeselTypeIdentifier) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(credentialsIdentifierResponseIndividualPeselTypeIdentifier, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CredentialsIdentifierResponseIndividualPeselType {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    credentialsIdentifierResponseIndividualPeselTypeIdentifier: ").append(toIndentedString(credentialsIdentifierResponseIndividualPeselTypeIdentifier)).append("\n");
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
