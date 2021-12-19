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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * SubjectAuthorizedType
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-19T16:15:43.054535+01:00[Europe/Warsaw]")
public class SubjectAuthorizedType {
  @SerializedName("issuedToIdentifier")
  private SubjectIdentifierToType issuedToIdentifier = null;

  @SerializedName("issuedToName")
  private SubjectNameType issuedToName = null;

  /**
   * Gets or Sets subjectAuthorizedType
   */
  @JsonAdapter(SubjectAuthorizedTypeEnum.Adapter.class)
  public enum SubjectAuthorizedTypeEnum {
    ENFORCEMENT_AUTHORITY("enforcement_authority"),
    COURT_BAILIFF("court_bailiff"),
    TAX_REPRESENTATIVE("tax_representative");

    private String value;

    SubjectAuthorizedTypeEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static SubjectAuthorizedTypeEnum fromValue(String input) {
      for (SubjectAuthorizedTypeEnum b : SubjectAuthorizedTypeEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<SubjectAuthorizedTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final SubjectAuthorizedTypeEnum enumeration) throws IOException {
        jsonWriter.value(String.valueOf(enumeration.getValue()));
      }

      @Override
      public SubjectAuthorizedTypeEnum read(final JsonReader jsonReader) throws IOException {
        Object value = jsonReader.nextString();
        return SubjectAuthorizedTypeEnum.fromValue((String)(value));
      }
    }
  }  @SerializedName("subjectAuthorizedType")
  private SubjectAuthorizedTypeEnum subjectAuthorizedType = null;

  public SubjectAuthorizedType issuedToIdentifier(SubjectIdentifierToType issuedToIdentifier) {
    this.issuedToIdentifier = issuedToIdentifier;
    return this;
  }

   /**
   * Get issuedToIdentifier
   * @return issuedToIdentifier
  **/
  @Schema(required = true, description = "")
  public SubjectIdentifierToType getIssuedToIdentifier() {
    return issuedToIdentifier;
  }

  public void setIssuedToIdentifier(SubjectIdentifierToType issuedToIdentifier) {
    this.issuedToIdentifier = issuedToIdentifier;
  }

  public SubjectAuthorizedType issuedToName(SubjectNameType issuedToName) {
    this.issuedToName = issuedToName;
    return this;
  }

   /**
   * Get issuedToName
   * @return issuedToName
  **/
  @Schema(description = "")
  public SubjectNameType getIssuedToName() {
    return issuedToName;
  }

  public void setIssuedToName(SubjectNameType issuedToName) {
    this.issuedToName = issuedToName;
  }

  public SubjectAuthorizedType subjectAuthorizedType(SubjectAuthorizedTypeEnum subjectAuthorizedType) {
    this.subjectAuthorizedType = subjectAuthorizedType;
    return this;
  }

   /**
   * Get subjectAuthorizedType
   * @return subjectAuthorizedType
  **/
  @Schema(required = true, description = "")
  public SubjectAuthorizedTypeEnum getSubjectAuthorizedType() {
    return subjectAuthorizedType;
  }

  public void setSubjectAuthorizedType(SubjectAuthorizedTypeEnum subjectAuthorizedType) {
    this.subjectAuthorizedType = subjectAuthorizedType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubjectAuthorizedType subjectAuthorizedType = (SubjectAuthorizedType) o;
    return Objects.equals(this.issuedToIdentifier, subjectAuthorizedType.issuedToIdentifier) &&
        Objects.equals(this.issuedToName, subjectAuthorizedType.issuedToName) &&
        Objects.equals(this.subjectAuthorizedType, subjectAuthorizedType.subjectAuthorizedType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issuedToIdentifier, issuedToName, subjectAuthorizedType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubjectAuthorizedType {\n");

    sb.append("    issuedToIdentifier: ").append(toIndentedString(issuedToIdentifier)).append("\n");
    sb.append("    issuedToName: ").append(toIndentedString(issuedToName)).append("\n");
    sb.append("    subjectAuthorizedType: ").append(toIndentedString(subjectAuthorizedType)).append("\n");
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
