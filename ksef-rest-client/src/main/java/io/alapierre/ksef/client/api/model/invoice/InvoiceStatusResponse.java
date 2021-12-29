package io.alapierre.ksef.client.api.model.invoice;

import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.29
 */
@Data
public class InvoiceStatusResponse {

    private String timestamp;
    private String referenceNumber;
    private int processingCode;
    private String processingDescription;
    private String elementReferenceNumber;
    private InvoiceStatus invoiceStatus;

    @Data
    public static class InvoiceStatus {
        private String invoiceNumber;
        private String ksefReferenceNumber;
        private String acquisitionTimestamp;
    }

}
