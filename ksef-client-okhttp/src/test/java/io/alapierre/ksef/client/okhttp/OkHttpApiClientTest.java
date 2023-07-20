package io.alapierre.ksef.client.okhttp;

import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeResponse;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.client.test.MockServerInit;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockserver.integration.ClientAndServer;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.08
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OkHttpApiClientTest {

    private ApiClient client;

    private final ClientAndServer mockServer =  new ClientAndServer(8080);

    @BeforeAll
    void init() {
        JsonSerializer serializer = new GsonJsonSerializer();
        client = new OkHttpApiClient(serializer, "http://localhost:8080");
        MockServerInit.prepareMockServer(mockServer);
    }

    @Test
    void postJson() throws Exception {

        try {
            val res = client.postJson(
                    "online/Session/AuthorisationChallenge",
                    "{\n" +
                            "  \"contextIdentifier\": {\n" +
                            "    \"type\": \"onip\",\n" +
                            "    \"identifier\": \"1111111111\"\n" +
                            "  }\n" +
                            "}",
                    AuthorisationChallengeResponse.class);

            System.out.println(res.orElseThrow(RuntimeException::new).getTimestamp());

        } catch (ApiException e) {
            procesException(e);
        }
    }

    @Test
    void postJsonWithAuth() throws Exception {

        try {
            val res = client.postJson(
                    "auth",
                    "{\n" +
                            "  \"contextIdentifier\": {\n" +
                            "    \"type\": \"onip\",\n" +
                            "    \"identifier\": \"1111111111\"\n" +
                            "  }\n" +
                            "}",
                    AuthorisationChallengeResponse.class, "token");

            System.out.println(res.orElseThrow(RuntimeException::new).getTimestamp());

        } catch (ApiException e) {
            procesException(e);
        }
    }

    @Test
    void getJson() throws Exception {
        val res = client.getJson("/test", AuthorisationChallengeResponse.class);
        System.out.println(res.orElseThrow(RuntimeException::new).getTimestamp());
    }

    @Test
    void getJsonWithAuth() throws Exception {
        val res = client.getJson("/auth", AuthorisationChallengeResponse.class, "token");
        System.out.println(res.orElseThrow(RuntimeException::new).getTimestamp());
    }


    private static void procesException(ApiException e) throws ApiException {
        System.out.printf("code: %d, message: %s\n", e.getCode(), e.getMessage());

        if( e.getExceptionDetails() != null) {
            e.getExceptionDetails().forEach(System.out::println);
        } else {
            System.out.println("details is null");
        }
        throw e;
    }


}
