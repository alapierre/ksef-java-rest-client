package io.alapierre.ksef.client.model.rest.auth;

import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.29
 */
@Data
public class SessionTerminateResponse {
    private String timestamp;
    private String referenceNumber;
    private int processingCode;
    private String processingDescription;
}
