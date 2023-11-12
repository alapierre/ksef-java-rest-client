package io.alapierre.ksef.test;

import io.alapierre.ksef.client.AbstractApiClient;
import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneFakturaApi;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.model.rest.auth.InitSignedResponse;
import io.alapierre.ksef.client.model.rest.auth.SessionTerminateResponse;
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.token.facade.KsefTokenFacade;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

import static io.alapierre.ksef.test.TestConfig.NIP;
import static io.alapierre.ksef.test.TestConfig.TOKEN;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.11.07
 */
public abstract class BaseIT {

    protected final JsonSerializer serializer = new GsonJsonSerializer();
    protected final ApiClient client = new OkHttpApiClient(serializer);
    protected final InterfejsyInteraktywneSesjaApi sessionApi = new InterfejsyInteraktywneSesjaApi(client);
    protected final InterfejsyInteraktywneFakturaApi invoiceApi = new InterfejsyInteraktywneFakturaApi(client);

    protected @NotNull InitSignedResponse login() throws ParseException, ApiException {
        val facade = new KsefTokenFacade(sessionApi);
        return facade.authByToken(AbstractApiClient.Environment.TEST, NIP, AuthorisationChallengeRequest.IdentifierType.onip, TOKEN);
    }

    protected @NotNull SessionTerminateResponse logout(String sessionToken) throws ApiException {
        return sessionApi.terminateSession(sessionToken);
    }

}
