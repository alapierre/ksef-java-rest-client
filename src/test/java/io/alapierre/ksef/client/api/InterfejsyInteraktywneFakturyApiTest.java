/*
 * KSeF
 * Krajowy Systemu e-Faktur
 *
 * OpenAPI spec version: 0.0.2
 * Contact: info.ksef@mf.gov.pl
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.alapierre.ksef.client.api;

import io.alapierre.ksef.client.model.SendInvoiceRequest;
import io.alapierre.ksef.client.model.SendInvoiceResponse;
import io.alapierre.ksef.client.model.StatusInvoiceResponse;
import org.junit.Test;
import org.junit.Ignore;


/**
 * API tests for InterfejsyInteraktywneFakturyApi
 */
@Ignore
public class InterfejsyInteraktywneFakturyApiTest {

    private final InterfejsyInteraktywneFakturyApi api = new InterfejsyInteraktywneFakturyApi();

    /**
     * Pobranie faktury
     *
     * Pobranie faktury
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getInvoiceTest() throws Exception {
        String kseFReferenceNumber = null;
        Object response = api.getInvoice(kseFReferenceNumber);

        // TODO: test validations
    }
    /**
     * Wysyłka faktury
     *
     * Wysyłka faktury
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void sendInvoiceTest() throws Exception {
        SendInvoiceRequest body = null;
        SendInvoiceResponse response = api.sendInvoice(body);

        // TODO: test validations
    }
    /**
     * Sprawdzenie statusu wysłanej faktury
     *
     * Sprawdzenie statusu wysłanej faktury
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void statusInvoiceTest() throws Exception {
        String invoiceElementReferenceNumber = null;
        StatusInvoiceResponse response = api.statusInvoice(invoiceElementReferenceNumber);

        // TODO: test validations
    }
}
