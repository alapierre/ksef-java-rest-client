package io.alapierre.ksef.client.model.rest.invoice;

import lombok.Builder;
import lombok.Value;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.22
 */
@Value
@Builder
public class UpoDTO {
    String referenceNumber;
    int processingCode;
    String processingDescription;
    byte[] upo;
}
