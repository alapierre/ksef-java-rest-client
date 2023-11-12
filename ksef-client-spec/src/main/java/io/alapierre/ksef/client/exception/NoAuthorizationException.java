package io.alapierre.ksef.client.exception;

import java.util.List;
import java.util.Map;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.10.23
 */
public class NoAuthorizationException extends NonRepeatableException {

    public NoAuthorizationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, throwable, code, responseHeaders, responseBody, details);
    }

    public NoAuthorizationException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable, code, responseHeaders, responseBody);
    }

    public NoAuthorizationException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, code, responseHeaders, responseBody);
    }

    public NoAuthorizationException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, message, responseHeaders, responseBody);
    }
}
