package io.alapierre.ksef.client.okhttp;

import io.alapierre.io.IOUtils;
import io.alapierre.ksef.client.AbstractApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
@SuppressWarnings("unused")
@Slf4j
public class OkHttpApiClient extends AbstractApiClient {

    public static final String API_EXCEPTION = "Błąd wywołania API";

    private final OkHttpClient client = new OkHttpClient();

    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final MediaType XML = MediaType.get("application/xml; charset=utf-8");
    private final MediaType OCTET = MediaType.get("application/octet-stream; charset=utf-8");

    public OkHttpApiClient(JsonSerializer serializer) {
        super(serializer, Environment.TEST);
    }

    public OkHttpApiClient(JsonSerializer serializer, String url) {
        super(serializer, url);
    }

    public OkHttpApiClient(JsonSerializer serializer, Environment environment) {
        super(serializer, environment);
    }

    @Override
    public <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {

        val builder = new Request.Builder();
        builder.url(createUrl(endpoint));
        builder.addHeader(TOKEN_HEADER_NAME, token);
        builder.get();

        return callAndReturnJson(classOfR, builder.build());
    }

    @Override
    public <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR) throws ApiException {

        val builder = new Request.Builder();
        builder.url(createUrl(endpoint));
        builder.get();

        return callAndReturnJson(classOfR, builder.build());
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR) throws ApiException {
        return doPostJson(endpoint, body, classOfR, Collections.emptyMap());
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        return doPostJson(endpoint, body, classOfR, addAuthTokenHeader(token));
    }

    @Override
    public <B, R> Optional<R> putJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        RequestBody requestBody = RequestBody.create(serializer.toJson(body), JSON);
        Request request = createRequest(endpoint, requestBody, addAuthTokenHeader(token), false);
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR) throws ApiException {
        try {
            RequestBody requestBody = RequestBody.create(body, OCTET);
            return postAndReturnJson(endpoint, classOfR, requestBody, Collections.emptyMap());
        } catch (IOException e) {
            throw new ApiException(API_EXCEPTION, e);
        }
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        try {
            RequestBody requestBody = RequestBody.create(body, OCTET);
            return postAndReturnJson(endpoint, classOfR, requestBody, addAuthTokenHeader(token));
        } catch (IOException e) {
            throw new ApiException(API_EXCEPTION, e);
        }
    }

    @Override
    public <R> Optional<R> postXML(@NotNull String endpoint, @NotNull Object body, @NotNull Class<R> classOfR) throws ApiException {
        try {
            RequestBody requestBody = RequestBody.create(marshalXML(body), XML);
            return postAndReturnJson(endpoint, classOfR, requestBody, Collections.emptyMap());
        } catch (Exception e) {
            throw new ApiException(API_EXCEPTION, e);
        }
    }

    @Override
    public void getStream(@NotNull String endpoint, @NotNull String token, @NotNull OutputStream os) throws ApiException {

        val builder = new Request.Builder();
        builder.url(createUrl(endpoint));
        builder.addHeader(TOKEN_HEADER_NAME, token);
        builder.get();

        try (Response response = client.newCall(builder.build()).execute()) {

            if(!response.isSuccessful()) {
                throw createException(response);
            }

            if (response.body() != null) {
                try (val is = response.body().byteStream()) {
                    IOUtils.copy(is, os);
                }
            }
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }

    @Override
    public void postStream(@NotNull String endpoint, @NotNull Object body, @NotNull OutputStream os) throws ApiException {
        RequestBody requestBody = RequestBody.create(serializer.toJson(body), JSON);
        Request request = createRequest(endpoint, requestBody, Collections.emptyMap(), true);

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw createException(response);
            }

            if (response.body() != null) {
                try (val is = response.body().byteStream()) {
                    IOUtils.copy(is, os);
                }
            }
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }


    protected <B, R> Optional<R> doPostJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, Map<String, String> headers) throws ApiException {

        RequestBody requestBody = RequestBody.create(serializer.toJson(body), JSON);
        Request request = createRequest(endpoint, requestBody, headers, true);
        return callAndReturnJson(classOfR, request);
    }

    @NotNull
    protected Request createRequest(@NotNull String endpoint, @NotNull RequestBody requestBody, @NotNull Map<String, String> headers, boolean post) {

        val builder = new Request.Builder();
        builder.url(createUrl(endpoint));

        headers.forEach(builder::addHeader);

        if (post) builder.post(requestBody);
        else builder.put(requestBody);

        return builder.build();
    }

    @NotNull
    protected <R> Optional<R> callAndReturnJson(@NotNull Class<R> classOfR, Request request) throws ApiException {
        try (Response response = client.newCall(request).execute()) {

            if(!response.isSuccessful()) {
                throw createException(response);
            }

            return response.body() != null
                    ? serializer.fromJson(response.body().string(), classOfR)
                    : Optional.empty();
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }

    @NotNull
    protected Map<String, String> addAuthTokenHeader(String token) {
        val headers = new HashMap<String, String>();
        headers.put(TOKEN_HEADER_NAME, token);
        return headers;
    }

    @NotNull
    protected <R> Optional<R> postAndReturnJson(String endpoint, Class<R> classOfR, RequestBody requestBody, @NotNull Map<String, String> headers) throws ApiException, IOException {

        Request request = createRequest(endpoint, requestBody, headers, true);

        try (Response response = client.newCall(request).execute()) {

            if(!response.isSuccessful()) {
                throw createException(response);
            }

            return response.body() != null
                    ? serializer.fromJson(response.body().string(), classOfR)
                    : Optional.empty();
        }
    }

    protected ApiException createException(Response response) {

        val responseBody = response.body();

        String body;
        try {
            body = responseBody != null ? responseBody.string() : null;
            log.debug("responseBody: {}", body);
        } catch (IOException e) {
            log.warn("Response code: {} and can't read body for error response {}", response.code(), e.getMessage());
            body = e.getMessage();
        }

        val headers = response.headers().toMultimap();
        log.debug("headers: {}", headers);

        return mapHttpResponseStatusToException(response.code(), response.message(), headers, body);
    }

}
