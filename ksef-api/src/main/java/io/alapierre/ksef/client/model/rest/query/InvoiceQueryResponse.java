package io.alapierre.ksef.client.model.rest.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.09
 */
@Data
public class InvoiceQueryResponse {

    private String timestamp;
    private String referenceNumber;
    private int numberOfElements;
    private int pageSize;
    private int pageOffset;
    private List<InvoiceHeaderList> invoiceHeaderList;

    @Data
    public static class InvoiceHeaderList{
        private String invoiceReferenceNumber;
        private String ksefReferenceNumber;
        private String invoicingDate;
        private String acquisitionTimestamp;
        private SubjectBy subjectBy;
        private Object subjectByK;
        private SubjectTo subjectTo;
        private List<Object> subjectToKList;
        private List<Object> subjectsOtherList;
        private List<Object> subjectsAuthorizedList;
        private String currency;
        private String net;
        private String vat;
        private String gross;
        private boolean faP17Annotation;
        private String invoiceType;
        private String schemaVersion;
        private boolean hidden;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubjectTo{
        private IssuedToIdentifier issuedToIdentifier;
        private IssuedToName issuedToName;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IssuedToName{
        private String type;
        private String tradeName;
        private String fullName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IssuedToIdentifier{
        private String type;
        private String identifier;
    }

    @Data
    public static class SubjectBy{
        private IssuedByIdentifier issuedByIdentifier;
        private IssuedByName issuedByName;
    }

    @Data
    public static class IssuedByIdentifier{
        private String type;
        private String identifier;
    }

    @Data
    public static class IssuedByName{
        private String type;
        private String tradeName;
        private String fullName;
    }

}
