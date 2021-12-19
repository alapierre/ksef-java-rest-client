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
 * GrantCredentialsRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class GrantCredentialsRequest {
  @SerializedName("timestamp")
  private OffsetDateTime timestamp = null;

  @SerializedName("grantCredentials")
  private GrantCredentialsRequestType grantCredentials = null;

  public GrantCredentialsRequest timestamp(OffsetDateTime timestamp) {
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

  public GrantCredentialsRequest grantCredentials(GrantCredentialsRequestType grantCredentials) {
    this.grantCredentials = grantCredentials;
    return this;
  }

   /**
   * Get grantCredentials
   * @return grantCredentials
  **/
  @Schema(required = true, description = "")
  public GrantCredentialsRequestType getGrantCredentials() {
    return grantCredentials;
  }

  public void setGrantCredentials(GrantCredentialsRequestType grantCredentials) {
    this.grantCredentials = grantCredentials;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrantCredentialsRequest grantCredentialsRequest = (GrantCredentialsRequest) o;
    return Objects.equals(this.timestamp, grantCredentialsRequest.timestamp) &&
        Objects.equals(this.grantCredentials, grantCredentialsRequest.grantCredentials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, grantCredentials);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantCredentialsRequest {\n");

    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    grantCredentials: ").append(toIndentedString(grantCredentials)).append("\n");
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
