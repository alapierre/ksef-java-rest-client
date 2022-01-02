package io.alapierre.ksef.client.serializer.gson;

import com.google.gson.Gson;
import io.alapierre.ksef.client.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
@Slf4j
public class GsonJsonSerializer implements JsonSerializer {

    public GsonJsonSerializer() {
        log.debug("Initializing GsonJsonSerializer");
        gson = new Gson();
    }

    public GsonJsonSerializer(Gson gson) {
        log.debug("Initializing GsonJsonSerializer with provided gson instance");
        this.gson = gson;
    }

    private final Gson gson;

    @Override
    public <R>  Optional<R> fromJson(@Nullable String json, @NotNull Class<R> classOfR) {
        return json != null
            ? Optional.ofNullable(gson.fromJson(json, classOfR))
            : Optional.empty();
    }

    @Override
    public @NotNull String toJson(@Nullable Object object) {
        return gson.toJson(object);
    }

    @Override
    public String name() {
        return "gson";
    }
}
