package io.alapierre.ksef.client.api.model.invoice;

import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.27
 */
@Data
public class SendInvoiceResponse {
    private String timestamp;
    private String referenceNumber;
    private int processingCode;
    private String processingDescription;
    private String elementReferenceNumber;
}
