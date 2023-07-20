package io.alapierre.ksef.client.model.rest.payment;

import lombok.Data;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.04.28
 */
@Data
public class KsefReferencesNumbers {

    private List<String> ksefReferenceNumberList;
    private String referenceNumber;
    private String timestamp;
}
