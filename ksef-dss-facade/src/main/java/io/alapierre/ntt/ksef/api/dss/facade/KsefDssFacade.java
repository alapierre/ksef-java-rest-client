package io.alapierre.ntt.ksef.api.dss.facade;

import eu.europa.esig.dss.model.DSSDocument;
import io.alapierre.crypto.dss.signer.AbstractSigner;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.model.rest.auth.InitSignedResponse;
import io.alapierre.ksef.xml.model.AuthRequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest.IdentifierType;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.08
 */
@Slf4j
@RequiredArgsConstructor
public class KsefDssFacade {

    private final AbstractSigner signer;
    private final InterfejsyInteraktywneSesjaApi api;

    public InitSignedResponse authByDigitalSignature(String identifier, IdentifierType identifierType) throws ApiException, IOException {

        val challengeResponse = api.authorisationChallengeCall(identifier, identifierType);
        log.debug("challengeResponse = {}", challengeResponse);

        val auth = AuthRequestUtil.prepareAuthRequest(challengeResponse.getChallenge(), identifier);
        val toSigned = AuthRequestUtil.requestToBytes(auth);

        ByteArrayInputStream is = new ByteArrayInputStream(toSigned);
        DSSDocument signedDocument = signer.sign(is);

        ByteArrayOutputStream signed = new ByteArrayOutputStream();
        signedDocument.writeTo(signed);

        val signedResponse = api.initSessionSignedCall(signed.toByteArray());

        log.debug("signedResponse = {}", signedResponse);

        return signedResponse;
    }
}
