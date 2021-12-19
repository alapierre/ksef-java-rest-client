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
import org.threeten.bp.OffsetDateTime;
/**
 * RevokeCredentialsRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class RevokeCredentialsRequest {
  @SerializedName("timestamp")
  private OffsetDateTime timestamp = null;

  @SerializedName("revokeCredentials")
  private RevokeCredentialsRequestType revokeCredentials = null;

  public RevokeCredentialsRequest timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Get timestamp
   * @return timestamp
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public RevokeCredentialsRequest revokeCredentials(RevokeCredentialsRequestType revokeCredentials) {
    this.revokeCredentials = revokeCredentials;
    return this;
  }

   /**
   * Get revokeCredentials
   * @return revokeCredentials
  **/
  @Schema(required = true, description = "")
  public RevokeCredentialsRequestType getRevokeCredentials() {
    return revokeCredentials;
  }

  public void setRevokeCredentials(RevokeCredentialsRequestType revokeCredentials) {
    this.revokeCredentials = revokeCredentials;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevokeCredentialsRequest revokeCredentialsRequest = (RevokeCredentialsRequest) o;
    return Objects.equals(this.timestamp, revokeCredentialsRequest.timestamp) &&
        Objects.equals(this.revokeCredentials, revokeCredentialsRequest.revokeCredentials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, revokeCredentials);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevokeCredentialsRequest {\n");

    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    revokeCredentials: ").append(toIndentedString(revokeCredentials)).append("\n");
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
