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
 * QueryCriteriaInvoiceRangeType
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class QueryCriteriaInvoiceRangeType extends QueryCriteriaInvoiceType {
  @SerializedName("invoicingDateFrom")
  private OffsetDateTime invoicingDateFrom = null;

  @SerializedName("invoicingDateTo")
  private OffsetDateTime invoicingDateTo = null;

  public QueryCriteriaInvoiceRangeType invoicingDateFrom(OffsetDateTime invoicingDateFrom) {
    this.invoicingDateFrom = invoicingDateFrom;
    return this;
  }

   /**
   * Get invoicingDateFrom
   * @return invoicingDateFrom
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getInvoicingDateFrom() {
    return invoicingDateFrom;
  }

  public void setInvoicingDateFrom(OffsetDateTime invoicingDateFrom) {
    this.invoicingDateFrom = invoicingDateFrom;
  }

  public QueryCriteriaInvoiceRangeType invoicingDateTo(OffsetDateTime invoicingDateTo) {
    this.invoicingDateTo = invoicingDateTo;
    return this;
  }

   /**
   * Get invoicingDateTo
   * @return invoicingDateTo
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getInvoicingDateTo() {
    return invoicingDateTo;
  }

  public void setInvoicingDateTo(OffsetDateTime invoicingDateTo) {
    this.invoicingDateTo = invoicingDateTo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryCriteriaInvoiceRangeType queryCriteriaInvoiceRangeType = (QueryCriteriaInvoiceRangeType) o;
    return Objects.equals(this.invoicingDateFrom, queryCriteriaInvoiceRangeType.invoicingDateFrom) &&
        Objects.equals(this.invoicingDateTo, queryCriteriaInvoiceRangeType.invoicingDateTo) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoicingDateFrom, invoicingDateTo, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryCriteriaInvoiceRangeType {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    invoicingDateFrom: ").append(toIndentedString(invoicingDateFrom)).append("\n");
    sb.append("    invoicingDateTo: ").append(toIndentedString(invoicingDateTo)).append("\n");
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
