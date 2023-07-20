package io.alapierre.ksef.client.model.rest.payment;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Tolerate;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.04.28
 */
@Data
@Builder
public class PaymentIdRequest {

    @Tolerate
    public PaymentIdRequest() {
    }

    @Singular("ksefReferenceNumber")
    private List<String> ksefReferenceNumberList;
}
