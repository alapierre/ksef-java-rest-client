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
import org.jetbrains.annotations.NotNull;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.InitSessionTokenRequest;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.10.15
 */
@Slf4j
@RequiredArgsConstructor
public class KsefTokenFacade {

    public static InitSignedResponse authByToken(@NonNull InterfejsyInteraktywneSesjaApi api, @NonNull String identifier, @NonNull String env, AuthorisationChallengeRequest.IdentifierType identifierType, @NotNull String token) throws ApiException, ParseException {
        AuthorisationChallengeResponse challengeResponse = api.authorisationChallengeCall(identifier, identifierType);
        log.debug("challengeResponse = {}", challengeResponse);
        Date timestamp = PublicKeyEncoder.parseChallengeTimestamp(challengeResponse.getTimestamp());
        PublicKeyEncoder encoder = PublicKeyEncoder.withBundledKey(env);
        String encryptedToken = encoder.encodeSessionToken(token, timestamp);
        InitSessionTokenRequest request = AuthRequestUtil.prepareTokenAuthRequest(challengeResponse.getChallenge(), identifier, encryptedToken);
        log.debug("Token request {}", request);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        AuthTokenRequestSerializer serializer = new AuthTokenRequestSerializer();
        serializer.toStream(request, os);
        return api.initSessionTokenCall(os.toByteArray());
    }

}
