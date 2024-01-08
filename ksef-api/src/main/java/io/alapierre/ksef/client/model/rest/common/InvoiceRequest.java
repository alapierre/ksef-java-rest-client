package io.alapierre.ksef.client.model.rest.common;

import io.alapierre.ksef.client.model.rest.query.InvoiceQueryResponse;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequest {
  private InvoiceDetails invoiceDetails;
  private String ksefReferenceNumber;


  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class InvoiceDetails {
    private String dueValue;
    private String invoiceOryginalNumber;
    private InvoiceQueryResponse.SubjectTo subjectTo;

  }
}
