package io.alapierre.ksef.test;

import io.alapierre.ksef.client.AbstractApiClient;
import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneZapytaniaApi;
import io.alapierre.ksef.client.iterator.InvoiceQueryResponseAdapter;
import io.alapierre.ksef.client.iterator.KsefResultStream;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.model.rest.auth.InitSignedResponse;
import io.alapierre.ksef.client.model.rest.query.InvoiceQueryRequest;
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.token.facade.KsefTokenFacade;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.09.04
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class RequestsTests {

    JsonSerializer serializer = new GsonJsonSerializer();
    ApiClient client = new OkHttpApiClient(serializer);
    InterfejsyInteraktywneZapytaniaApi zapytaniaApi = new InterfejsyInteraktywneZapytaniaApi(client);
    InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

    String NIP_FIRMY = "9781399259";
    String authorisationToken = "30AC53BF6313480A4C12278907E718C82086E19FD56DF3F43C889A28572FDD4A";

    String sessionToken;

    @BeforeAll
    void login() throws Exception{

        val tokenFromEnv = System.getenv("KSEF_SESSION_TOKEN");
        if(tokenFromEnv != null && !tokenFromEnv.isBlank()) {
            sessionToken = tokenFromEnv;
            log.info("Using token from env");
        } else {
            val tokenResponse = loginByToken();
            log.info("Get token from KSeF");
            log.debug("token request rep: {}", tokenResponse);
            sessionToken = tokenResponse.getSessionToken().getToken();
        }
    }

    @Test
    void testQueryForInvoice() throws Exception {

        val request = InvoiceQueryRequest.builder()
                .queryCriteria(InvoiceQueryRequest.QueryCriteria.builder()
                        .subjectType("subject2")
                        .type("incremental")
                        .acquisitionTimestampThresholdFrom("2023-08-01T00:00:00")
                        .acquisitionTimestampThresholdTo("2023-09-01T00:00:00")
                        .build())
                .build();

        val queryJson = serializer.toJson(request);
        System.out.println(queryJson);

        KsefResultStream.builder(
                        page -> new InvoiceQueryResponseAdapter(zapytaniaApi.invoiceQuery(sessionToken, request, 100, page)))
                .forEach(System.out::println);
    }

    private InitSignedResponse loginByToken() throws ApiException, ParseException {

        val facade = new KsefTokenFacade(sesjaApi);
        return facade.authByToken(AbstractApiClient.Environment.TEST, NIP_FIRMY, AuthorisationChallengeRequest.IdentifierType.onip, authorisationToken);
    }

}
