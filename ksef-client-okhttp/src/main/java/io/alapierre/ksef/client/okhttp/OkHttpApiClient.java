package io.alapierre.ksef.client.okhttp;

import io.alapierre.ksef.client.AbstractApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
@SuppressWarnings("unused")
@RequiredArgsConstructor
@Slf4j
public class OkHttpApiClient extends AbstractApiClient {

    private final JsonSerializer serializer;

    @Override
    public <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        return Optional.empty();
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR) throws ApiException {
        return Optional.empty();
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        return Optional.empty();
    }

    @Override
    public <B, R> Optional<R> putJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR) throws ApiException {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> postXML(@NotNull String endpoint, @NotNull Object body, @NotNull Class<R> classOfR) throws ApiException {
        return Optional.empty();
    }

    ApiException createException(Response response) {

        val responseBody = response.body();

        String body = null;
        try {
            body = responseBody != null ? responseBody.string() : null;
        } catch (IOException e) {
            log.warn("Response code: {} and can't read body for error response {}", response.code(), e.getMessage());
            body = e.getMessage();
        }

        val headers = response.headers().toMultimap();

        return new ApiException(response.code(), response.message(), headers, body);
    }

}
