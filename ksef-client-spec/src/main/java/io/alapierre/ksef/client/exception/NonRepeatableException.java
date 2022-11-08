package io.alapierre.ksef.client.exception;

import io.alapierre.ksef.client.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.08
 */
public abstract class NonRepeatableException extends ApiException {

    public NonRepeatableException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, throwable, code, responseHeaders, responseBody, details);
    }

    public NonRepeatableException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable, code, responseHeaders, responseBody);
    }

    public NonRepeatableException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, code, responseHeaders, responseBody);
    }

    public NonRepeatableException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, message, responseHeaders, responseBody);
    }
}
