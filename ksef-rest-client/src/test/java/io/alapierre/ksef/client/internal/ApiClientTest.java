package io.alapierre.ksef.client.internal;

import io.alapierre.ksef.client.api.model.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.api.model.AuthorisationChallengeResponse;
import lombok.val;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.22
 */
@DisplayName("Tests for ApiClient")
class ApiClientTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    void testURLBuilder() {

        val url = "https://ksef-test.mf.gov.pl/api";

        HttpUrl ppp = HttpUrl.parse(url);

        if(ppp != null) {
            val n = ppp.newBuilder("/online/Session/AuthorisationChallenge").build();
            System.out.println(n);
        }
    }

    @Nested
    @DisplayName("Tests url creating")
    class TestCreateUrl {

        @Test
        @DisplayName("with endpoint prefix")
        void withPrefix() {
            val res = apiClient.createUrl("/someEndpoint");
            assertEquals("https://ksef-test.mf.gov.pl/api/someEndpoint", res);
        }

        @Test
        @DisplayName("without endpoint prefix")
        void withoutPrefix() {
            val res = apiClient.createUrl("someEndpoint");
            assertEquals("https://ksef-test.mf.gov.pl/api/someEndpoint", res);
        }
    }

    @Nested
    @DisplayName("Tests request making")
    class TestClient {

        @Test
        @DisplayName("Test request for online/Session/AuthorisationChallenge")
        void authRequest() throws IOException {

            Optional<AuthorisationChallengeResponse> resp = apiClient.postJson(
                    "online/Session/AuthorisationChallenge",
                    getAuthorisationChallengeRequest(),
                    AuthorisationChallengeResponse.class);

            assertTrue(resp.isPresent());
            System.out.println(resp);
        }

        private AuthorisationChallengeRequest getAuthorisationChallengeRequest() {
            return AuthorisationChallengeRequest.builder()
                    .contextIdentifier(AuthorisationChallengeRequest.ContextIdentifier.builder()
                            .identifierType(AuthorisationChallengeRequest.IdentifierType.onip)
                            .identifier("6891152920")
                            .build())
                    .build();
        }

    }

}
