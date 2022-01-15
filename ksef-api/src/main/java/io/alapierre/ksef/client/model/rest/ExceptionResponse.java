package io.alapierre.ksef.client.model.rest;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.10
 */
@Data
public class ExceptionResponse {

    private Exception exception;

    @Data
    public static class Exception{
        private String serviceCtx;
        private String serviceCode;
        private String serviceName;
        private String timestamp;
        private String referenceNumber;
        private ArrayList<ExceptionDetailList> exceptionDetailList;
    }

    @Data
    public static class ExceptionDetailList{
        private int exceptionCode;
        private String exceptionDescription;
    }

}
