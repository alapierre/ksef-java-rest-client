package io.alapierre.ksef.client.api;

import io.alapierre.ksef.client.api.model.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.api.model.AuthorisationChallengeRequest.IdentifierType;
import io.alapierre.ksef.client.api.model.AuthorisationChallengeResponse;
import io.alapierre.ksef.client.api.model.auth.ContextIdentifier;
import io.alapierre.ksef.client.api.model.auth.InitSignedResponse;
import io.alapierre.ksef.client.api.model.auth.SessionStatus;
import io.alapierre.ksef.client.api.model.auth.SessionTerminateResponse;
import io.alapierre.ksef.client.internal.ApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.23
 */
@SuppressWarnings("unused")
@Slf4j
@RequiredArgsConstructor
public class InterfejsyInteraktywneSesjaApi {

    private final ApiClient apiClient;

    @NotNull
    public AuthorisationChallengeResponse authorisationChallengeCall(@NotNull String identifier, @NotNull IdentifierType identifierType) throws ApiException {

        val ret = apiClient.postJson(
                "online/Session/AuthorisationChallenge",
                getAuthorisationChallengeRequest(identifier, identifierType),
                AuthorisationChallengeResponse.class);

       return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    @NotNull
    public InitSignedResponse initSessionSignedCall(@NotNull String challenge, @NotNull String identifier, byte[] signedRequest) throws ApiException {

        val ret = apiClient.postXMLFromBytes(
                "online/Session/InitSigned",
                signedRequest,
                InitSignedResponse.class
        );

        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    public SessionStatus sessionStatus(@NotNull String token, int pageSize, int pageOffset) throws ApiException {

        val endpoint= String.format("online/Session/Status?PageSize=%d&PageOffset=%d", pageSize, pageOffset);

        val ret = apiClient.getJson(endpoint, SessionStatus.class, token);
        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    public SessionTerminateResponse terminateSession(@NotNull String token) throws ApiException {
        val ret = apiClient.getJson("online/Session/Terminate", SessionTerminateResponse.class, token);
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
