package io.alapierre.ksef.client.model.rest.auth;

import lombok.Data;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.29
 */
@Data
public class SessionStatus {

    private String timestamp;
    private String referenceNumber;
    private int numberOfElements;
    private int pageSize;
    private int pageOffset;
    private int processingCode;
    private String processingDescription;
    private List<InvoiceStatusList> invoiceStatusList;

    @Data
    public static class InvoiceStatusList{
        private int processingCode;
        private String processingDescription;
        private String elementReferenceNumber;
        private String invoiceNumber;
        private String ksefReferenceNumber;
        private String acquisitionTimestamp;
    }

}
