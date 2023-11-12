package io.alapierre.ksef.client.exception;

import io.alapierre.ksef.client.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.10.23
 */
public class NoAuthenticationException extends ApiException {

    public NoAuthenticationException() {
    }

    public NoAuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public NoAuthenticationException(String message) {
        super(message);
    }

    public NoAuthenticationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, throwable, code, responseHeaders, responseBody, details);
    }

    public NoAuthenticationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable, code, responseHeaders, responseBody);
    }

    public NoAuthenticationException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, code, responseHeaders, responseBody);
    }

    public NoAuthenticationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders) {
        super(message, throwable, code, responseHeaders);
    }

    public NoAuthenticationException(int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, responseHeaders, responseBody);
    }

    public NoAuthenticationException(int code, String message) {
        super(code, message);
    }

    public NoAuthenticationException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(code, message, responseHeaders, responseBody, details);
    }

    public NoAuthenticationException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, message, responseHeaders, responseBody);
    }

    public NoAuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
