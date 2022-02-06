package io.alapierre.ksef.client.model.rest.invoice;

import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.22
 */
@Data
public class UpoResponse {
    private String timestamp;
    private String referenceNumber;
    private int processingCode;
    private String processingDescription;
    private String upo;
}
