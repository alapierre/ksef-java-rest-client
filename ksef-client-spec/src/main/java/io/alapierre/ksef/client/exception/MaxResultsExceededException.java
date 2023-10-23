package io.alapierre.ksef.client.exception;

import java.util.List;
import java.util.Map;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.10.23
 */
public class MaxResultsExceededException extends NonRepeatableException {

    public MaxResultsExceededException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, null, code, responseHeaders, responseBody, details);
    }

    public MaxResultsExceededException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody, List<ExceptionDetail> details) {
        super(message, throwable, code, responseHeaders, responseBody, details);
    }

    public MaxResultsExceededException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable, code, responseHeaders, responseBody);
    }

    public MaxResultsExceededException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, code, responseHeaders, responseBody);
    }

    public MaxResultsExceededException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        super(code, message, responseHeaders, responseBody);
    }
}
