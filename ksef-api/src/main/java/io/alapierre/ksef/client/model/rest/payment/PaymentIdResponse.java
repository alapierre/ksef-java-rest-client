package io.alapierre.ksef.client.model.rest.payment;

import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.04.28
 */
@Data
public class PaymentIdResponse {

    private String paymentIdentifier;
    private String referenceNumber;
    private String timestamp;

}
