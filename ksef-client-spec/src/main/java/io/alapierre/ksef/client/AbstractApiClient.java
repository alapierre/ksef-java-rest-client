package io.alapierre.ksef.client;

import io.alapierre.ksef.client.exception.ExceptionResponse;
import io.alapierre.ksef.client.exception.TooManyRequestsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public abstract class AbstractApiClient implements ApiClient {

    private final String url;

    protected final JsonSerializer serializer;

    protected AbstractApiClient(JsonSerializer serializer) {
        url = Environment.TEST.getUrl();
        this.serializer = serializer;
    }

    protected AbstractApiClient(JsonSerializer serializer, String url) {
        this.url = url;
        this.serializer = serializer;
    }

    protected AbstractApiClient(JsonSerializer serializer, Environment environment) {
        url = environment.getUrl();
        this.serializer = serializer;
    }

    protected byte[] marshalXML(@NotNull Object o) throws JAXBException {

        val jc = JAXBContext.newInstance(o.getClass());
        val jaxbMarshaller = jc.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        val stream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(o, stream);

        return stream.toByteArray();
    }

    @NotNull
    protected String createUrl(@NotNull String endpoint) {

        val sb = new StringBuilder();
        sb.append(url);
        if (!endpoint.startsWith("/")) sb.append("/");
        sb.append(endpoint);

        return sb.toString();
    }

    @Nullable
    protected List<ApiException.ExceptionDetail> getExceptionDetails(@Nullable String body) {
        List<ApiException.ExceptionDetail> details;
        details = serializer.fromJson(body, ExceptionResponse.class).map(exceptionResponse -> {
            if(exceptionResponse.getException() != null) {
                val list = exceptionResponse.getException().getExceptionDetailList();
                return list.stream().map(exceptionDetailList -> ApiException.ExceptionDetail.builder()
                        .exceptionCode(exceptionDetailList.getExceptionCode())
                        .exceptionDescription(exceptionDetailList.getExceptionDescription())
                        .build()).collect(Collectors.toList());
            }
            return null;
        }).orElse(Collections.emptyList());
        return details;
    }

    protected ApiException mapExceptionResponseToException(int code, String message, Map<String, List<String>> headers, String body) {
        if(code == 429)
            return new TooManyRequestsException(code, message, headers, body, getExceptionDetails(body));

        return new ApiException(code, message, headers, body, getExceptionDetails(body));
    }

    @Getter
    @RequiredArgsConstructor
    public enum Environment {

        DEMO("https://ksef-demo.mf.gov.pl/api"),
        PROD("https://ksef.mf.gov.pl/api"),
        TEST("https://ksef-test.mf.gov.pl/api");

        private final String url;
    }
}
