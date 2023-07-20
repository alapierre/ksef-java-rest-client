package io.alapierre.ksef.client.test;

import lombok.val;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.HttpStatusCode.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.04.10
 */
public class MockServerInit {

    public static void prepareMockServer(ClientAndServer mockServer) {
        mockServer.when( request()
                        .withPath("/online/Session/AuthorisationChallenge"))
                .respond( r -> response()
                        .withStatusCode(CREATED_201.code())
                        .withBody("{\n" +
                                "  \"timestamp\": \"2021-10-01T12:13:14Z\",\n" +
                                "  \"challenge\": \"20211001-CR-FFFFFFFFFF-FFFFFFFFFF-FF\"\n" +
                                "}")
                );

        mockServer.when( request()
                        .withPath("/test"))
                .respond( r -> response()
                        .withStatusCode(OK_200.code())
                        .withBody("{\n" +
                                "  \"timestamp\": \"2021-10-01T12:13:14Z\",\n" +
                                "  \"challenge\": \"20211001-CR-FFFFFFFFFF-FFFFFFFFFF-FF\"\n" +
                                "}")
                );

        mockServer.when( request()
                        .withPath("/auth"))
                .respond( r -> {
                            val token = r.getHeader("SessionToken");
                            if (token == null || token.isEmpty()) {
                                return response()
                                        .withStatusCode(BAD_REQUEST_400.code());
                            } else
                                return response()
                                        .withStatusCode(OK_200.code())
                                        .withBody("{\n" +
                                                "  \"timestamp\": \"2021-10-01T12:13:14Z\",\n" +
                                                "  \"challenge\": \"20211001-CR-FFFFFFFFFF-FFFFFFFFFF-FF\"\n" +
                                                "}");
                        }
                );
    }

}
