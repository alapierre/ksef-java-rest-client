package io.alapierre.ksef.client.model.rest.payment;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.04.28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIdRequest {

    @Singular("ksefReferenceNumber")
    private List<String> ksefReferenceNumberList;
}
