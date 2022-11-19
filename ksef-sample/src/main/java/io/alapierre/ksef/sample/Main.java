package io.alapierre.ksef.sample;

import eu.europa.esig.dss.model.DSSDocument;
import io.alapierre.commons.date.DateUtils;
import io.alapierre.crypto.dss.signer.P12Signer;
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
import io.alapierre.ksef.client.model.rest.query.InvoiceQueryResponse;
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.token.facade.KsefTokenFacade;
import io.alapierre.ntt.ksef.api.dss.facade.KsefDssFacade;
import lombok.val;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.alapierre.ksef.client.AbstractApiClient.Environment;
import static io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest.IdentifierType;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public class Main {

    public static final String NIP_FIRMY = "NIP firmy";
    private static final  File tokenFile = new File("token.p12");
    private static final  KeyStore.PasswordProtection pas = new KeyStore.PasswordProtection("_____token_password_____".toCharArray());

    public static void main(String[] args)  {

        try {

            JsonSerializer serializer = new GsonJsonSerializer();
            ApiClient client = new OkHttpApiClient(serializer);
            InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

            val signer = new P12Signer(pas, tokenFile);
            KsefDssFacade facade = new KsefDssFacade(signer, sesjaApi);

            val signedResponse = facade.authByDigitalSignature(NIP_FIRMY, IdentifierType.onip);

            // signedResponse.getSessionToken() zawiera token sesyjny

            val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);
            invoiceApi.invoiceSend(new File("FA1.xml"), signedResponse.getSessionToken().getToken());

        } catch (ApiException ex) {
            System.out.printf("Błąd wywołania API %d (%s) opis błędu %s", ex.getCode(), ex.getMessage(),  ex.getResponseBody());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void loginBySignature() throws IOException, ApiException {

        JsonSerializer serializer = new GsonJsonSerializer();
        ApiClient client = new OkHttpApiClient(serializer);
        InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);


        val signer = new P12Signer(pas, tokenFile);
        KsefDssFacade facade = new KsefDssFacade(signer, sesjaApi);

        facade.authByDigitalSignature(NIP_FIRMY, IdentifierType.onip);

    }

    public static ByteArrayOutputStream signRequest(byte[] toSigned) throws IOException {

        val signer = new P12Signer(pas, tokenFile);

        ByteArrayInputStream is = new ByteArrayInputStream(toSigned);
        DSSDocument signedDocument = signer.sign(is);

        ByteArrayOutputStream signed = new ByteArrayOutputStream();
        signedDocument.writeTo(signed);

        return signed;
    }

    @SuppressWarnings("DuplicatedCode")
    public static void loginByToken() throws Exception {

        JsonSerializer serializer = new GsonJsonSerializer();
        ApiClient client = new OkHttpApiClient(serializer, Environment.TEST);

        InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

        val facade = new KsefTokenFacade(sesjaApi);
        InitSignedResponse session = facade.authByToken(Environment.TEST, NIP_FIRMY, IdentifierType.onip, "token");

        val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);
        invoiceApi.invoiceSend(new File("FA1.xml"), session.getSessionToken().getToken());
    }

    @SuppressWarnings("DuplicatedCode")
    public static void loadIncomingInvoices() throws Exception {

        JsonSerializer serializer = new GsonJsonSerializer();
        ApiClient client = new OkHttpApiClient(serializer, Environment.TEST);

        InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

        val facade = new KsefTokenFacade(sesjaApi);
        InitSignedResponse session = facade.authByToken(Environment.TEST, NIP_FIRMY, IdentifierType.onip, "token");

        val zapytaniaApi = new InterfejsyInteraktywneZapytaniaApi(client);

        val request = InvoiceQueryRequest.builder()
                .queryCriteria(InvoiceQueryRequest.QueryCriteria.builder()
                        .subjectType("subject2")
                        .acquisitionTimestampThresholdFrom(zapytaniaApi.convertDate(DateUtils.firstDayOfMonth(LocalDate.now())))
                        .acquisitionTimestampThresholdTo(zapytaniaApi.convertDate(LocalDateTime.now()))
                        .build())
                .build();

        new KsefResultStream<InvoiceQueryResponse.InvoiceHeaderList>().stream(
                page -> new InvoiceQueryResponseAdapter(zapytaniaApi.invoiceQuery(session.getSessionToken().getToken(), request, 100, page)))
                .forEach(System.out::println);
    }

}
