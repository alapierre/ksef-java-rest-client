package io.alapierre.ksef.client.exception;

import io.alapierre.ksef.client.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.10.23
 */
public class NoAuthorizationException extends ApiException {

    public NoAuthorizationException() {
    }

    public NoAuthorizationException(Throwable throwable) {
        super(throwable);
    }

    public NoAuthorizationException(String message) {
        super(message);
    }

    public NoAuthorizationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, throwable, code, responseHeaders, responseBody, details);
    }

    public NoAuthorizationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable, code, responseHeaders, responseBody);
    }

    public NoAuthorizationException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, code, responseHeaders, responseBody);
    }

    public NoAuthorizationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders) {
        super(message, throwable, code, responseHeaders);
    }

    public NoAuthorizationException(int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, responseHeaders, responseBody);
    }

    public NoAuthorizationException(int code, String message) {
        super(code, message);
    }

    public NoAuthorizationException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(code, message, responseHeaders, responseBody, details);
    }

    public NoAuthorizationException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, message, responseHeaders, responseBody);
    }

    public NoAuthorizationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
