package io.alapierre.ksef.client;

import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.util.Optional;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public interface ApiClient {

    String TOKEN_HEADER_NAME = "SessionToken";
    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    String QUERY_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException;

    <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR) throws ApiException;

    <R> Optional<R> getJsonWithAcceptHeader(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String accept) throws ApiException;

    <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR) throws ApiException;

    <B, R> Optional<R> postJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException;

    <R> Optional<R> getJson(@NotNull String endpoint, @NotNull Class<R> classOfR, @NotNull String token, @NotNull String accept) throws ApiException;

    <B, R> Optional<R> putJson(@NotNull String endpoint, @NotNull B body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException;

    <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR) throws ApiException;

    <R> Optional<R> postXMLFromBytes(@NotNull String endpoint, byte[] body, @NotNull Class<R> classOfR, @NotNull String token) throws ApiException;

    <R> Optional<R> postXML(@NotNull String endpoint, @NotNull Object body, @NotNull Class<R> classOfR) throws ApiException;

    void getStream(@NotNull String endpoint, @NotNull String token, @NotNull OutputStream os) throws ApiException;
    void postStream(@NotNull String endpoint, @NotNull Object body, @NotNull OutputStream os) throws ApiException;
}
