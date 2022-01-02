package io.alapierre.ksef.client.api;

import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.model.rest.auth.*;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest.IdentifierType;
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

    @NotNull
    public InitSignedResponse initSessionTokenCall(byte[] tokenRequest) throws ApiException {

        val ret = apiClient.postXMLFromBytes(
                "online/Session/InitToken",
                tokenRequest,
                InitSignedResponse.class
        );

        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    @NotNull
    public SessionStatus sessionStatus(@NotNull String token, int pageSize, int pageOffset) throws ApiException {

        val endpoint= String.format("online/Session/Status?PageSize=%d&PageOffset=%d", pageSize, pageOffset);

        val ret = apiClient.getJson(endpoint, SessionStatus.class, token);
        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    @NotNull
    public SessionTerminateResponse terminateSession(@NotNull String token) throws ApiException {
        val ret = apiClient.getJson("online/Session/Terminate", SessionTerminateResponse.class, token);
        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    @NotNull
    protected AuthorisationChallengeRequest getAuthorisationChallengeRequest(String identifier, IdentifierType identifierType) {
        return AuthorisationChallengeRequest.builder()
                .contextIdentifier(ContextIdentifier.builder()
                        .type(identifierType)
                        .identifier(identifier)
                        .build())
                .build();
    }
}
