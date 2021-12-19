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
 * QueryCriteriaInvoiceIncrementalType
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class QueryCriteriaInvoiceIncrementalType extends QueryCriteriaInvoiceType {
  @SerializedName("acquisitionTimestampThresholdFrom")
  private OffsetDateTime acquisitionTimestampThresholdFrom = null;

  @SerializedName("acquisitionTimestampThresholdTo")
  private OffsetDateTime acquisitionTimestampThresholdTo = null;

  public QueryCriteriaInvoiceIncrementalType acquisitionTimestampThresholdFrom(OffsetDateTime acquisitionTimestampThresholdFrom) {
    this.acquisitionTimestampThresholdFrom = acquisitionTimestampThresholdFrom;
    return this;
  }

   /**
   * Get acquisitionTimestampThresholdFrom
   * @return acquisitionTimestampThresholdFrom
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getAcquisitionTimestampThresholdFrom() {
    return acquisitionTimestampThresholdFrom;
  }

  public void setAcquisitionTimestampThresholdFrom(OffsetDateTime acquisitionTimestampThresholdFrom) {
    this.acquisitionTimestampThresholdFrom = acquisitionTimestampThresholdFrom;
  }

  public QueryCriteriaInvoiceIncrementalType acquisitionTimestampThresholdTo(OffsetDateTime acquisitionTimestampThresholdTo) {
    this.acquisitionTimestampThresholdTo = acquisitionTimestampThresholdTo;
    return this;
  }

   /**
   * Get acquisitionTimestampThresholdTo
   * @return acquisitionTimestampThresholdTo
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getAcquisitionTimestampThresholdTo() {
    return acquisitionTimestampThresholdTo;
  }

  public void setAcquisitionTimestampThresholdTo(OffsetDateTime acquisitionTimestampThresholdTo) {
    this.acquisitionTimestampThresholdTo = acquisitionTimestampThresholdTo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryCriteriaInvoiceIncrementalType queryCriteriaInvoiceIncrementalType = (QueryCriteriaInvoiceIncrementalType) o;
    return Objects.equals(this.acquisitionTimestampThresholdFrom, queryCriteriaInvoiceIncrementalType.acquisitionTimestampThresholdFrom) &&
        Objects.equals(this.acquisitionTimestampThresholdTo, queryCriteriaInvoiceIncrementalType.acquisitionTimestampThresholdTo) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acquisitionTimestampThresholdFrom, acquisitionTimestampThresholdTo, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryCriteriaInvoiceIncrementalType {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    acquisitionTimestampThresholdFrom: ").append(toIndentedString(acquisitionTimestampThresholdFrom)).append("\n");
    sb.append("    acquisitionTimestampThresholdTo: ").append(toIndentedString(acquisitionTimestampThresholdTo)).append("\n");
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
