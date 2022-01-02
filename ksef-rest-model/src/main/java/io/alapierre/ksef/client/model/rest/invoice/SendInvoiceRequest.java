package io.alapierre.ksef.client.model.rest.invoice;

import lombok.Builder;
import lombok.Value;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.27
 */
@Value
@Builder
public class SendInvoiceRequest {

    InvoiceHash invoiceHash;
    InvoicePayload invoicePayload;

    @Value
    @Builder
    public static class InvoicePayload{
        String type;
        String invoiceBody;
    }

    @Value
    @Builder
    public static class InvoiceHash{
        HashSHA hashSHA;
        int fileSize;

        @Value
        @Builder
        public static class HashSHA {
            String algorithm;
            String encoding;
            String value;
        }

    }

}
