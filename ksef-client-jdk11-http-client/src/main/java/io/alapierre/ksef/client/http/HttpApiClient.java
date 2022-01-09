package io.alapierre.ksef.client.http;

import io.alapierre.ksef.client.AbstractApiClient;
import io.alapierre.ksef.client.ApiException;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public class HttpApiClient extends AbstractApiClient {

    @Override
    public <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public <B, R> Optional<R> putJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public <R> Optional<R> postXML(@NotNull String endpoint, @NotNull Object body, @NotNull Class<R> classOfR) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public void getStream(@NotNull String endpoint, @NotNull String token, @NotNull OutputStream os) throws ApiException {
        throw new IllegalStateException("Not implemented yet");
    }
}
