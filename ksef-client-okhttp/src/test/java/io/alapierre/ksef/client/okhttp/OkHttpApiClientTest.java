package io.alapierre.ksef.client.okhttp;

import io.alapierre.ksef.client.AbstractApiClient;
import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.08
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OkHttpApiClientTest {

    private ApiClient client;

    @BeforeAll
    void init() {
        JsonSerializer serializer = new GsonJsonSerializer();
        client = new OkHttpApiClient(serializer, AbstractApiClient.Environment.TEST);
    }

    @Test
    void testExceptionMapper() {

        try {
            client.postJson(
                    "online/Session/AuthorisationChallenge",
                    "ala ma kota",
                    String.class);
        } catch (ApiException e) {

            System.out.printf("code: %d, message: %s\n", e.getCode(), e.getMessage());

            if( e.getExceptionDetails() != null) {
                e.getExceptionDetails().forEach(System.out::println);
            } else {
                System.out.println("details is null");
            }

        }


    }


}
