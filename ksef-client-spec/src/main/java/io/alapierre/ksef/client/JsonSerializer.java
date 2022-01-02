package io.alapierre.ksef.client;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public interface JsonSerializer {

    <R> Optional<R> fromJson(@Nullable String json, @NotNull Class<R> classOfR);

    @NotNull
    String toJson(@Nullable Object object);

    String name();

}
