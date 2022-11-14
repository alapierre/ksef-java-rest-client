package io.alapierre.ksef.client.exception;

import lombok.Value;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.08
 */
@Value
public class ExceptionResponse {

    Exception exception;

    @Value
    public static class Exception {
        List<ExceptionDetailList> exceptionDetailList;
        String referenceNumber;
        String serviceCode;
        String serviceCtx;
        String serviceName;
        String timestamp;
    }

    @Value
    public static class ExceptionDetailList {
        int exceptionCode;
        String exceptionDescription;
    }
}

