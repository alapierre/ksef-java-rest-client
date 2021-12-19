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
 * InvoiceDivisionPlainPartType
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class InvoiceDivisionPlainPartType {
  @SerializedName("partReferenceNumber")
  private String partReferenceNumber = null;

  @SerializedName("partName")
  private String partName = null;

  @SerializedName("partNumber")
  private Integer partNumber = null;

  @SerializedName("partRangeFrom")
  private OffsetDateTime partRangeFrom = null;

  @SerializedName("partRangeTo")
  private OffsetDateTime partRangeTo = null;

  @SerializedName("partExpiration")
  private OffsetDateTime partExpiration = null;

  @SerializedName("plainPartHash")
  private FileUnlimitedHashType plainPartHash = null;

  public InvoiceDivisionPlainPartType partReferenceNumber(String partReferenceNumber) {
    this.partReferenceNumber = partReferenceNumber;
    return this;
  }

   /**
   * Get partReferenceNumber
   * @return partReferenceNumber
  **/
  @Schema(required = true, description = "")
  public String getPartReferenceNumber() {
    return partReferenceNumber;
  }

  public void setPartReferenceNumber(String partReferenceNumber) {
    this.partReferenceNumber = partReferenceNumber;
  }

  public InvoiceDivisionPlainPartType partName(String partName) {
    this.partName = partName;
    return this;
  }

   /**
   * Get partName
   * @return partName
  **/
  @Schema(required = true, description = "")
  public String getPartName() {
    return partName;
  }

  public void setPartName(String partName) {
    this.partName = partName;
  }

  public InvoiceDivisionPlainPartType partNumber(Integer partNumber) {
    this.partNumber = partNumber;
    return this;
  }

   /**
   * Get partNumber
   * @return partNumber
  **/
  @Schema(required = true, description = "")
  public Integer getPartNumber() {
    return partNumber;
  }

  public void setPartNumber(Integer partNumber) {
    this.partNumber = partNumber;
  }

  public InvoiceDivisionPlainPartType partRangeFrom(OffsetDateTime partRangeFrom) {
    this.partRangeFrom = partRangeFrom;
    return this;
  }

   /**
   * Get partRangeFrom
   * @return partRangeFrom
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getPartRangeFrom() {
    return partRangeFrom;
  }

  public void setPartRangeFrom(OffsetDateTime partRangeFrom) {
    this.partRangeFrom = partRangeFrom;
  }

  public InvoiceDivisionPlainPartType partRangeTo(OffsetDateTime partRangeTo) {
    this.partRangeTo = partRangeTo;
    return this;
  }

   /**
   * Get partRangeTo
   * @return partRangeTo
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getPartRangeTo() {
    return partRangeTo;
  }

  public void setPartRangeTo(OffsetDateTime partRangeTo) {
    this.partRangeTo = partRangeTo;
  }

  public InvoiceDivisionPlainPartType partExpiration(OffsetDateTime partExpiration) {
    this.partExpiration = partExpiration;
    return this;
  }

   /**
   * Get partExpiration
   * @return partExpiration
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getPartExpiration() {
    return partExpiration;
  }

  public void setPartExpiration(OffsetDateTime partExpiration) {
    this.partExpiration = partExpiration;
  }

  public InvoiceDivisionPlainPartType plainPartHash(FileUnlimitedHashType plainPartHash) {
    this.plainPartHash = plainPartHash;
    return this;
  }

   /**
   * Get plainPartHash
   * @return plainPartHash
  **/
  @Schema(required = true, description = "")
  public FileUnlimitedHashType getPlainPartHash() {
    return plainPartHash;
  }

  public void setPlainPartHash(FileUnlimitedHashType plainPartHash) {
    this.plainPartHash = plainPartHash;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InvoiceDivisionPlainPartType invoiceDivisionPlainPartType = (InvoiceDivisionPlainPartType) o;
    return Objects.equals(this.partReferenceNumber, invoiceDivisionPlainPartType.partReferenceNumber) &&
        Objects.equals(this.partName, invoiceDivisionPlainPartType.partName) &&
        Objects.equals(this.partNumber, invoiceDivisionPlainPartType.partNumber) &&
        Objects.equals(this.partRangeFrom, invoiceDivisionPlainPartType.partRangeFrom) &&
        Objects.equals(this.partRangeTo, invoiceDivisionPlainPartType.partRangeTo) &&
        Objects.equals(this.partExpiration, invoiceDivisionPlainPartType.partExpiration) &&
        Objects.equals(this.plainPartHash, invoiceDivisionPlainPartType.plainPartHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partReferenceNumber, partName, partNumber, partRangeFrom, partRangeTo, partExpiration, plainPartHash);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InvoiceDivisionPlainPartType {\n");

    sb.append("    partReferenceNumber: ").append(toIndentedString(partReferenceNumber)).append("\n");
    sb.append("    partName: ").append(toIndentedString(partName)).append("\n");
    sb.append("    partNumber: ").append(toIndentedString(partNumber)).append("\n");
    sb.append("    partRangeFrom: ").append(toIndentedString(partRangeFrom)).append("\n");
    sb.append("    partRangeTo: ").append(toIndentedString(partRangeTo)).append("\n");
    sb.append("    partExpiration: ").append(toIndentedString(partExpiration)).append("\n");
    sb.append("    plainPartHash: ").append(toIndentedString(plainPartHash)).append("\n");
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
