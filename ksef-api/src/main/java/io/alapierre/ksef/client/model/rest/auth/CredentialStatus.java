package io.alapierre.ksef.client.model.rest.auth;

import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.06
 */
@Data
public class CredentialStatus {

    private String timestamp;
    private String referenceNumber;
    private int processingCode;
    private String processingDescription;
    private String elementReferenceNumber;

}
