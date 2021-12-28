package io.alapierre.ksef.client.api;

import io.alapierre.ksef.client.api.model.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.api.model.AuthorisationChallengeRequest.IdentifierType;
import io.alapierre.ksef.client.api.model.AuthorisationChallengeResponse;
import io.alapierre.ksef.client.api.model.auth.ContextIdentifier;
import io.alapierre.ksef.client.api.model.auth.InitSignedResponse;
import io.alapierre.ksef.client.internal.ApiClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.23
 */
@SuppressWarnings("unused")
@Slf4j
public class InterfejsyInteraktywneSesjaApi {

    private final ApiClient apiClient = new ApiClient();

    public AuthorisationChallengeResponse authorisationChallengeCall(String identifier, IdentifierType identifierType) throws ApiException {

        val ret = apiClient.postJson(
                "online/Session/AuthorisationChallenge",
                getAuthorisationChallengeRequest(identifier, identifierType),
                AuthorisationChallengeResponse.class);

       return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    public InitSignedResponse initSessionSignedCall(String challenge, String identifier, byte[] signedRequest) throws ApiException {

        val ret = apiClient.postXMLFromBytes(
                "online/Session/InitSigned",
                signedRequest,
                InitSignedResponse.class
        );

        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    protected AuthorisationChallengeRequest getAuthorisationChallengeRequest(String identifier, IdentifierType identifierType) {
        return AuthorisationChallengeRequest.builder()
                .contextIdentifier(ContextIdentifier.builder()
                        .type(identifierType)
                        .identifier(identifier)
                        .build())
                .build();
    }



}
