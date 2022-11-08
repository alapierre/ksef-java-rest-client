package io.alapierre.ksef.client.http;

import io.alapierre.io.IOUtils;
import io.alapierre.ksef.client.AbstractApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

/**
 * @author Karol Bryzgiel {@literal karol.bryzgiel@soft-project.pl}
 * Copyrights by original author 2022.04.24
 */
@Slf4j
public class HttpApiClient extends AbstractApiClient {

    private final HttpClient client = HttpClient.newHttpClient();

    public HttpApiClient(JsonSerializer serializer) {
        super(serializer);
    }

    public HttpApiClient(String url, JsonSerializer serializer) {
        super(serializer, url);
    }

    @Override
    public <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        HttpRequest request = prepareGetRequest(endpoint, Map.of(TOKEN_HEADER_NAME, token));
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR) throws ApiException {
        HttpRequest request = prepareGetRequest(endpoint, Collections.emptyMap());
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR) throws ApiException {
        HttpRequest request = preparePostRequest(endpoint, Collections.emptyMap(), HttpRequest.BodyPublishers.ofString(serializer.toJson(body)), true);
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        HttpRequest request = preparePostRequest(endpoint, Map.of(TOKEN_HEADER_NAME, token), HttpRequest.BodyPublishers.ofString(serializer.toJson(body)), true);
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <B, R> Optional<R> putJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        HttpRequest request = preparePostRequest(endpoint, Map.of(TOKEN_HEADER_NAME, token), HttpRequest.BodyPublishers.ofString(serializer.toJson(body)), false);
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR) throws ApiException {
        HttpRequest request = preparePostRequest(endpoint, Collections.emptyMap(), HttpRequest.BodyPublishers.ofByteArray(body), false);
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        HttpRequest request = preparePostRequest(endpoint, Map.of(TOKEN_HEADER_NAME, token), HttpRequest.BodyPublishers.ofByteArray(body), false);
        return callAndReturnJson(classOfR, request);
    }

    @Override
    public <R> Optional<R> postXML(@NotNull String endpoint, @NotNull Object body, @NotNull Class<R> classOfR) throws ApiException {
        try {
            HttpRequest request = preparePostRequest(endpoint, Collections.emptyMap(), HttpRequest.BodyPublishers.ofByteArray(marshalXML(body)), false);
            return callAndReturnJson(classOfR, request);
        } catch (JAXBException e) {
            throw new ApiException("Błąd wywołania API", e);
        }
    }

    @Override
    public void getStream(@NotNull String endpoint, @NotNull String token, @NotNull OutputStream os) throws ApiException {
        HttpRequest request = prepareGetRequest(endpoint, Collections.emptyMap());

        try {
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.body() != null) { // TODO: sprawdzić czy to jest potrzebne
                try (InputStream is = response.body()) {
                    IOUtils.copy(is, os);
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new ApiException(e);
        }

        throw new IllegalStateException("Not implemented yet");
    }

    private HttpRequest prepareGetRequest(@NotNull String endpoint, @NotNull Map<String, String> headers) throws ApiException {
        List<String> headersList = prepareHeaders(headers);
        URI uri = prepareURI(endpoint);
        return HttpRequest.newBuilder()
                .uri(uri)
                .headers(headersList.toArray(new String[0]))
                .GET()
                .build();
    }


    private HttpRequest preparePostRequest(@NotNull String endpoint,
                                           @NotNull Map<String, String> headers,
                                           @NotNull HttpRequest.BodyPublisher bodyPublisher,
                                           boolean post) throws ApiException {
        List<String> headersList = prepareHeaders(headers);
        URI uri = prepareURI(endpoint);

        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(uri)
                .headers(headersList.toArray(new String[0]));

        if (post) {
            httpRequestBuilder.POST(bodyPublisher);
        } else {
            httpRequestBuilder.PUT(bodyPublisher);
        }
        return httpRequestBuilder.build();
    }

    private URI prepareURI(@NotNull String endpoint) throws ApiException {
        String url = createUrl(endpoint);
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            log.error("URL {} is a malformed URL", url, e);
            throw new ApiException(e);
        }
    }

    private List<String> prepareHeaders(@NotNull Map<String, String> headers) {
        List<String> headersList = new LinkedList<>();
        headers.forEach((k, v) -> {
            headersList.add(k);
            headersList.add(v);
        });
        return headersList;
    }

    private <R> Optional<R> callAndReturnJson(@NotNull Class<R> classOfR, @NotNull HttpRequest request) throws ApiException {
        try {
            HttpResponse<Optional<R>> req = client.send(request, new JsonBodyHandler<>(classOfR, serializer));
            if (req.statusCode() != 200) {
                throw createException(req);
            }
            return req.body();
        } catch (IOException | InterruptedException e) {
            throw new ApiException(e);
        }
    }

    protected ApiException createException(@NotNull HttpResponse<?> response) {

        val responseBody = response.body();

        String body;
        body = responseBody != null ? responseBody.toString() : null;
        log.debug("responseBody: {}", body);

        val headers = response.headers().map();
        log.debug("headers: {}", headers);

        return mapExceptionResponseToException(response.statusCode(), HttpStatus.valueOf(response.statusCode()).getMessage(), headers, body);
    }
}
