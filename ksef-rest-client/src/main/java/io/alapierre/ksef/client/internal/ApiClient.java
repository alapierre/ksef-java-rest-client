package io.alapierre.ksef.client.internal;

import com.google.gson.Gson;
import io.alapierre.ksef.client.api.ApiException;
import lombok.val;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.21
 */
public class ApiClient {

    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final MediaType XML = MediaType.get("application/xml; charset=utf-8");
    private final MediaType OCTET = MediaType.get("application/octet-stream; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {

        val builder = new Request.Builder();
        builder.url(createUrl(endpoint));
        builder.addHeader("SessionToken", token);
        builder.get();

        return callAndReturnJson(classOfR, builder.build());
    }

    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR) throws ApiException {
        return doPostJson(endpoint, body, classOfR, null);
    }

    public <B, R> Optional<R> putJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        RequestBody requestBody = RequestBody.create(gson.toJson(body), JSON);
        Request request = createRequest(endpoint, requestBody, token, false);
        return callAndReturnJson(classOfR, request);
    }

    public <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {
        return doPostJson(endpoint, body, classOfR, token);
    }

    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, @NotNull byte[] body, @NotNull Class<R> classOfR) throws ApiException {

        try {
            RequestBody requestBody = RequestBody.create(body, OCTET);
            return postAndReturnJson(endpoint, classOfR, requestBody, null);
        } catch (IOException e) {
            throw new ApiException("Błąd wywołania API", e);
        }
    }

    public <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, @NotNull byte[] body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException {

        try {
            RequestBody requestBody = RequestBody.create(body, OCTET);
            return postAndReturnJson(endpoint, classOfR, requestBody, token);
        } catch (IOException e) {
            throw new ApiException("Błąd wywołania API", e);
        }
    }

    public <R> Optional<R> postXML(@NotNull String endpoint, @NotNull Object body, @NotNull Class<R> classOfR) throws ApiException {

        try {
            RequestBody requestBody = RequestBody.create(marshalXML(body), XML);

            return postAndReturnJson(endpoint, classOfR, requestBody, null);

        } catch (JAXBException e) {
            throw new ApiException("Błąd konwersji obiektu  do XML", e);
        } catch (IOException e) {
            throw new ApiException("Błąd wywołania API", e);
        }
    }

    private  <B, R> Optional<R> doPostJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @Nullable String authToken) throws ApiException {

        RequestBody requestBody = RequestBody.create(gson.toJson(body), JSON);
        Request request = createRequest(endpoint, requestBody, authToken, true);
        return callAndReturnJson(classOfR, request);
    }

    @NotNull
    private <R> Optional<R> callAndReturnJson(@NotNull Class<R> classOfR, Request request) throws ApiException {
        try (Response response = client.newCall(request).execute()) {

            if(!response.isSuccessful()) {
                throw createException(response);
            }

            return response.body() != null
                    ? Optional.ofNullable(gson.fromJson(response.body().string(), classOfR))
                    : Optional.empty();
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }

    private @NotNull Request createRequest(@NotNull String endpoint, @NotNull RequestBody requestBody, String token, boolean post) {

        val builder = new Request.Builder();
        builder.url(createUrl(endpoint));
        if (token != null) builder.addHeader("SessionToken", token);
        if (post) builder.post(requestBody);
        else builder.put(requestBody);

        return builder.build();
    }

    @NotNull
    private <R> Optional<R> postAndReturnJson(String endpoint, Class<R> classOfR, RequestBody requestBody, String token) throws ApiException, IOException {

        Request request = createRequest(endpoint, requestBody, token, true);

        try (Response response = client.newCall(request).execute()) {

            if(!response.isSuccessful()) {
                throw createException(response);
            }

            return response.body() != null
                    ? Optional.ofNullable(gson.fromJson(response.body().string(), classOfR))
                    : Optional.empty();
        }
    }

    protected byte[] marshalXML(@NotNull Object o) throws JAXBException {

        val jc = JAXBContext.newInstance(o.getClass());
        val jaxbMarshaller = jc.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        val stream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(o, stream);

        return stream.toByteArray();
    }

    @NotNull String createUrl(@NotNull String endpoint) {

        val baseUrl = "https://ksef-test.mf.gov.pl/api";
        val sb = new StringBuilder();

        sb.append(baseUrl);
        if (!endpoint.startsWith("/")) sb.append("/");
        sb.append(endpoint);

        return sb.toString();
    }

    ApiException createException(Response response) {
        return new ApiException(response.code(), response.message());
    }

}
