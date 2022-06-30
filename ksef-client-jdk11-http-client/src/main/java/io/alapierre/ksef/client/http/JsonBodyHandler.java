package io.alapierre.ksef.client.http;

import io.alapierre.ksef.client.JsonSerializer;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author Karol Bryzgiel {@literal karol.bryzgiel@soft-project.pl}
 * Copyrights by original author 2022.04.24
 */
public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<Optional<W>> {

    private final Class<W> tClass;
    private final JsonSerializer serializer;


    public JsonBodyHandler(Class<W> tClass, JsonSerializer serializer) {
        this.tClass = tClass;
        this.serializer = serializer;
    }

    @Override
    public HttpResponse.BodySubscriber<Optional<W>> apply(HttpResponse.ResponseInfo responseInfo) {
        return asJson(tClass);
    }

    private <T> HttpResponse.BodySubscriber<Optional<T>> asJson(Class<T> tClass) {
        HttpResponse.BodySubscriber<String> upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                (String body) -> body != null
                        ? serializer.fromJson(body, tClass)
                        : Optional.empty());
    }

}
