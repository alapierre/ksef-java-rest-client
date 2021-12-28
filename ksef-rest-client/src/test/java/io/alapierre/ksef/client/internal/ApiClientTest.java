package io.alapierre.ksef.client.internal;

import com.google.gson.Gson;
import io.alapierre.ksef.client.api.ApiException;
import io.alapierre.ksef.client.api.model.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.api.model.AuthorisationChallengeResponse;
import io.alapierre.ksef.client.api.model.auth.ContextIdentifier;
import lombok.val;
import lombok.var;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.InitSessionSignedRequest;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.ObjectFactory;
import pl.gov.mf.ksef.schema.gtw.svc.types._2021._10._01._0001.*;

import javax.xml.bind.JAXBException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        HttpUrl httpUrl = HttpUrl.parse(url);

        if(httpUrl != null) {
            val n = httpUrl.newBuilder("/online/Session/AuthorisationChallenge").build();
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
        void authRequest() throws ApiException {

            val authorisationChallenge = getAuthorisationChallengeRequest();
            val gson = new Gson();
            System.out.println(gson.toJson(authorisationChallenge));


            Optional<AuthorisationChallengeResponse> resp = apiClient.postJson(
                    "online/Session/AuthorisationChallenge",
                    getAuthorisationChallengeRequest(),
                    AuthorisationChallengeResponse.class);

            assertTrue(resp.isPresent());
            System.out.println(resp);
        }

        private AuthorisationChallengeRequest getAuthorisationChallengeRequest() {
            return AuthorisationChallengeRequest.builder()
                    .contextIdentifier(ContextIdentifier.builder()
                            .type(AuthorisationChallengeRequest.IdentifierType.onip)
                            .identifier("6891152920")
                            .build())
                    .build();
        }

    }

    @Nested
    @DisplayName("Tests request making")
    class TestXml {

        @Test
        void testMarshal() throws JAXBException {

            var factory = new ObjectFactory();
            var contextFactory = new pl.gov.mf.ksef.schema.gtw.svc.online.types._2021._10._01._0001.ObjectFactory();

            InitSessionSignedRequest request = factory.createInitSessionSignedRequest();

            var context = contextFactory.createAuthorisationContextSignedType();
            context.setType(AuthorisationTypeType.SERIAL_NUMBER);
            context.setChallenge("20211001-CR-FFFFFFFFFF-FFFFFFFFFF-FF");

            var documentTypeType = new DocumentTypeType();
            documentTypeType.setService(ServiceType.K_SE_F);

            var form = new FormCodeType();
            form.setSystemCode("FA (1)");
            form.setSchemaVersion("1-0E");
            form.setTargetNamespace("http://crd.gov.pl/wzor/2021/11/29/11089/");
            form.setValue("FA");

            documentTypeType.setFormCode(form);

            context.setDocumentType(documentTypeType);

            var identifier = new SubjectIdentifierByCompanyType();
            identifier.setIdentifier("1111111111");
            context.setIdentifier(identifier);

            request.setContext(context);

            val res = apiClient.marshalXML(request);

            System.out.println(new String(res));

        }


    }

}
