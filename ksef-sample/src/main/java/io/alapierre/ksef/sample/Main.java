package io.alapierre.ksef.sample;

import io.alapierre.crypto.dss.signer.P12Signer;
import io.alapierre.ksef.api.dss.facade.KsefDssFacade;
import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneFakturaApi;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneZapytaniaApi;
import io.alapierre.ksef.client.iterator.InvoiceQueryResponseAdapter;
import io.alapierre.ksef.client.iterator.KsefResultStream;
import io.alapierre.ksef.client.model.rest.auth.InitSignedResponse;
import io.alapierre.ksef.client.model.rest.query.InvoiceQueryRequest;
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.token.facade.KsefTokenFacade;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.text.ParseException;

import static io.alapierre.ksef.client.AbstractApiClient.Environment;
import static io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest.IdentifierType;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public class Main {

    public static final String NIP_FIRMY = "9781399259";
    private static final  File tokenFile = new File("token.p12");
    private static final  KeyStore.PasswordProtection pas = new KeyStore.PasswordProtection("_____token_password_____".toCharArray());

    private static final JsonSerializer serializer = new GsonJsonSerializer();
    private static final ApiClient client = new OkHttpApiClient(serializer);
    private static final InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

    public static final String token = "30AC53BF6313480A4C12278907E718C82086E19FD56DF3F43C889A28572FDD4A";
    //"24BB2B31E766F3BB2FF7244964DABCC680D611C515F85420F270254AD0C6E7D7"

    public static void main(String[] args)  {

        try {
            val signedResponse = loginByToken();

            System.out.println("session token = " + signedResponse.getSessionToken().getToken());

            val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);
            val sessionToken = signedResponse.getSessionToken().getToken();

            val resp = invoiceApi.invoiceSend(new File("ksef-sample/src/main/resources/FA2.xml"), sessionToken);

            System.out.printf("ElementReferenceNumber %s, ReferenceNumber %s, ProcessingCode %d\n",
                    resp.getElementReferenceNumber(),
                    resp.getReferenceNumber(),
                    resp.getProcessingCode());

            loadIncomingInvoices(sessionToken);

            sesjaApi.terminateSession(sessionToken);
        } catch (ApiException ex) {
            System.out.printf("Błąd wywołania API %d (%s) opis błędu %s", ex.getCode(), ex.getMessage(),  ex.getResponseBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static InitSignedResponse loginBySignature() throws IOException, ApiException {

        val signer = new P12Signer(pas, tokenFile);
        KsefDssFacade facade = new KsefDssFacade(signer, sesjaApi);
        return facade.authByDigitalSignature(NIP_FIRMY, IdentifierType.onip);
    }

    @SuppressWarnings("DuplicatedCode")
    public static InitSignedResponse loginByToken() throws ApiException, ParseException {

        val facade = new KsefTokenFacade(sesjaApi);
        return facade.authByToken(Environment.TEST, NIP_FIRMY, IdentifierType.onip, token);
    }

    @SuppressWarnings("DuplicatedCode")
    public static void loadIncomingInvoices(String sessionToken) throws ParseException, ApiException {

        val zapytaniaApi = new InterfejsyInteraktywneZapytaniaApi(client);

        val request = InvoiceQueryRequest.builder()
                .queryCriteria(InvoiceQueryRequest.QueryCriteria.builder()
                        .subjectType("subject2")
                        .type("incremental")
                        .acquisitionTimestampThresholdFrom("2023-08-01T00:00:00")
                        .acquisitionTimestampThresholdTo("2023-09-01T00:00:00")
                        .build())
                .build();

        KsefResultStream.builder(
                page -> new InvoiceQueryResponseAdapter(zapytaniaApi.invoiceQuery(sessionToken, request, 100, page)))
                .forEach(System.out::println);
    }



}
