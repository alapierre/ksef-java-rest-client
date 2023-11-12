package io.alapierre.ksef.client;

import io.alapierre.ksef.client.exception.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.*;
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

    protected ApiException mapHttpResponseStatusToException(int code, String message, Map<String, List<String>> headers, String body) {

        val exceptionsFromKSef = getExceptionDetails(body);
        val errorCodes = exceptionsFromKSef != null ? extractKsefExceptionsCodes(exceptionsFromKSef) : Collections.emptySet();

        if(code == 429)
            return new TooManyRequestsException(code, message, headers, body, exceptionsFromKSef);
        else if (code == 400) {

            if(errorCodes.contains(21177)) return new MaxResultsExceededException(code, message, headers, body, exceptionsFromKSef);
            if(errorCodes.contains(21301)) return new NoAuthorizationException(message, code, headers, body);
            if(errorCodes.contains(21304)) return new NoAuthenticationException(code, message, headers, body, exceptionsFromKSef);

            return new BadRequestException(code, message, headers, body, exceptionsFromKSef);
        }

        return new ApiException(code, message, headers, body, getExceptionDetails(body));
    }

    protected Set<Integer> extractKsefExceptionsCodes(List<ApiException.ExceptionDetail> list) {
        return list.stream().map(ApiException.ExceptionDetail::getExceptionCode).collect(Collectors.toSet());
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
