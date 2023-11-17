package io.alapierre.ksef.token.facade;

import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeResponse;
import io.alapierre.ksef.client.model.rest.auth.InitSignedResponse;
import io.alapierre.ksef.xml.model.AuthRequestUtil;
import io.alapierre.ksef.xml.model.AuthTokenRequestSerializer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.InitSessionTokenRequest;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Date;

import static io.alapierre.ksef.client.AbstractApiClient.Environment;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.10.15
 */
@Slf4j
@RequiredArgsConstructor
public class KsefTokenFacade {

    private final InterfejsyInteraktywneSesjaApi api;

    private boolean isSchemaValidationEnabled() {
        val res = Boolean.parseBoolean(System.getProperty("io.alapierre.ksef.validateAuthRequestXML", "true"));
        if (!res) log.info("AuthRequest XML validation is disabled");
        return res;
    }

    public InitSignedResponse authByToken(@NonNull Environment env, @NonNull String identifier, AuthorisationChallengeRequest.IdentifierType identifierType, @NotNull String token) throws ApiException, ParseException {
        AuthorisationChallengeResponse challengeResponse = api.authorisationChallengeCall(identifier, identifierType);
        log.debug("challengeResponse = {}", challengeResponse);
        Date timestamp = PublicKeyEncoder.parseChallengeTimestamp(challengeResponse.getTimestamp());
        PublicKeyEncoder encoder = PublicKeyEncoder.withBundledKey(env.name());
        String encryptedToken = encoder.encodeSessionToken(token, timestamp);
        InitSessionTokenRequest request = AuthRequestUtil.prepareTokenAuthRequest(challengeResponse.getChallenge(), identifier, encryptedToken);
        log.debug("Token request {}", request);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        AuthTokenRequestSerializer serializer = new AuthTokenRequestSerializer();
        serializer.toStream(request, os, isSchemaValidationEnabled());
        return api.initSessionTokenCall(os.toByteArray());
    }

}
