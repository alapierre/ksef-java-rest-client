package io.alapierre.ksef.client.api;

import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.model.rest.auth.*;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest.IdentifierType;
import io.alapierre.ksef.client.model.rest.auth.GenerateTokenRequest.RoleType;
import io.alapierre.ksef.client.model.rest.auth.GenerateTokenRequest.TokenCredentialsRoleList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.23
 */
@SuppressWarnings("unused")
@Slf4j
@RequiredArgsConstructor
public class InterfejsyInteraktywneSesjaApi {

    public static final String BAD_API_RESPONSE = "Nieprawidłowa odpowiedź z API";
    private final ApiClient apiClient;

    @NotNull
    public AuthorisationChallengeResponse authorisationChallengeCall(@NotNull String identifier, @NotNull IdentifierType identifierType) throws ApiException {

        val ret = apiClient.postJson(
                "online/Session/AuthorisationChallenge",
                getAuthorisationChallengeRequest(identifier, identifierType),
                AuthorisationChallengeResponse.class);

       return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

    @NotNull
    public InitSignedResponse initSessionSignedCall(@NotNull String challenge, @NotNull String identifier, byte[] signedRequest) throws ApiException {

        val ret = apiClient.postXMLFromBytes(
                "online/Session/InitSigned",
                signedRequest,
                InitSignedResponse.class
        );

        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

    @NotNull
    public InitSignedResponse initSessionTokenCall(byte[] tokenRequest) throws ApiException {

        val ret = apiClient.postXMLFromBytes(
                "online/Session/InitToken",
                tokenRequest,
                InitSignedResponse.class
        );

        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

    @NotNull
    public SessionStatus sessionStatus(@NotNull String token, int pageSize, int pageOffset) throws ApiException {

        val endpoint= String.format("online/Session/Status?PageSize=%d&PageOffset=%d", pageSize, pageOffset);

        val ret = apiClient.getJson(endpoint, SessionStatus.class, token);
        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

    @NotNull
    public SessionTerminateResponse terminateSession(@NotNull String token) throws ApiException {
        val ret = apiClient.getJson("online/Session/Terminate", SessionTerminateResponse.class, token);
        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

    public CredentialStatus credentialStatus(String credentialsElementReferenceNumber, String token) throws ApiException {
        val endpoint= String.format("online/Credentials/Status/%s", credentialsElementReferenceNumber);
        val ret = apiClient.getJson(endpoint, CredentialStatus.class, token);
        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

    /**
     * @deprecated please use io.alapierre.ksef.client.api.InterfejsyInteraktywneUprawnieniaApi.generateToken()
     * @see io.alapierre.ksef.client.api.InterfejsyInteraktywneUprawnieniaApi
     */
    @NotNull
    @Deprecated
    public AuthorisationToken generateToken(@NotNull String tokenDescription, @NotNull String token, RoleType... roles) throws ApiException {

        val rolesConverted = Arrays.stream(roles).map(roleType -> TokenCredentialsRoleList.builder()
                .roleDescription(tokenDescription)
                .roleType(roleType)
                .build()).collect(Collectors.toSet());

        return generateToken(tokenDescription, token, rolesConverted);
    }

    /**
     * @deprecated please use io.alapierre.ksef.client.api.InterfejsyInteraktywneUprawnieniaApi.generateToken()
     * @see io.alapierre.ksef.client.api.InterfejsyInteraktywneUprawnieniaApi
     */
    @Deprecated()
    @NotNull
    public AuthorisationToken generateToken(@NotNull String tokenDescription, @NotNull String token, @NotNull Set<TokenCredentialsRoleList> roles) throws ApiException {

        val req = GenerateTokenRequest.builder()
                .generateToken(GenerateTokenRequest.GenerateToken.builder()
                        .credentialsRoleList(roles)
                        .description(tokenDescription)
                        .build())
                .build();

        val ret = apiClient.postJson("online/Credentials/GenerateToken", req, AuthorisationToken.class, token);
        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
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
