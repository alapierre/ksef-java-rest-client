package io.alapierre.ksef.client.serializer.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.alapierre.ksef.client.JsonSerializer;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public class JacksonJsonSerializer implements JsonSerializer {

    private final ObjectMapper objectMapper;

    public JacksonJsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JacksonJsonSerializer() {
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    public <R> Optional<R> fromJson(@Nullable String json, @NotNull Class<R> classOfR) {
        return !"".equals(json)
                ? Optional.ofNullable(objectMapper.readValue(json, classOfR))
                : Optional.empty();
    }

    @SneakyThrows
    @Override
    public @NotNull String toJson(@Nullable Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @Override
    public String name() {
        return "jackson";
    }
}
