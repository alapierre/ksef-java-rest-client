package io.alapierre.ksef.sample;

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
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.token.facade.KsefTokenFacade;
import io.alapierre.ntt.ksef.api.dss.facade.KsefDssFacade;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public static void main(String[] args)  {

        try {
            val signedResponse = loginByToken();
            val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);
            val sessionToken = signedResponse.getSessionToken().getToken();
            invoiceApi.invoiceSend(new File("ksef-sample/src/main/resources/FA1.xml"), sessionToken);

        } catch (ApiException ex) {
            System.out.printf("Błąd wywołania API %d (%s) opis błędu %s", ex.getCode(), ex.getMessage(),  ex.getResponseBody());
        } catch (IOException | ParseException e) {
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

        InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);
        val facade = new KsefTokenFacade(sesjaApi);
        InitSignedResponse session = facade.authByToken(Environment.TEST, NIP_FIRMY, IdentifierType.onip, token);
        return session;
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

        val token = session.getSessionToken().getToken();
        KsefResultStream.builder(
                page -> new InvoiceQueryResponseAdapter(zapytaniaApi.invoiceQuery(token, request, 100, page)))
                .forEach(System.out::println);
    }

}
