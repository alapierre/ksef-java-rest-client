package io.alapierre.ksef.client.exception;

import java.util.List;
import java.util.Map;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.08
 */
public class TooManyRequestsException extends NonRepeatableException {

    public TooManyRequestsException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, throwable, code, responseHeaders, responseBody, details);
    }

    public TooManyRequestsException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable, code, responseHeaders, responseBody);
    }

    public TooManyRequestsException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, code, responseHeaders, responseBody);
    }

    public TooManyRequestsException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, message, responseHeaders, responseBody);
    }

    public TooManyRequestsException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, null, code, responseHeaders, responseBody, details);
    }
}
