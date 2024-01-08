package io.alapierre.ksef.client.exception;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    Exception exception;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Exception {
        List<ExceptionDetailList> exceptionDetailList;
        String referenceNumber;
        String serviceCode;
        String serviceCtx;
        String serviceName;
        String timestamp;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExceptionDetailList {
        int exceptionCode;
        String exceptionDescription;
    }
}

