package io.alapierre.ksef.client.internal;

import com.google.gson.Gson;
import lombok.val;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.21
 */
public class ApiClient {

    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    private final Gson gson = new Gson();

    public <B, R> Optional<R> postJson(String endpoint, B body, Class<R> classOfR) throws IOException {

        RequestBody requestBody = RequestBody.create(gson.toJson(body), JSON);

        Request request = new Request.Builder()
                .url(createUrl(endpoint))
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body() != null
                    ? Optional.ofNullable(gson.fromJson(response.body().string(), classOfR))
                    : Optional.empty();
        }
    }

    @NotNull String createUrl(@NotNull String endpoint) {

        val baseUrl = "https://ksef-test.mf.gov.pl/api";
        val sb = new StringBuilder();

        sb.append(baseUrl);
        if (!endpoint.startsWith("/")) sb.append("/");
        sb.append(endpoint);

        return sb.toString();
    }

}
