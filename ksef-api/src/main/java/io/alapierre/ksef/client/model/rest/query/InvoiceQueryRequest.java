package io.alapierre.ksef.client.model.rest.query;

import lombok.Builder;
import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.09
 */
@Data
@Builder
public class InvoiceQueryRequest {

    private QueryCriteria queryCriteria;

    @Data
    @Builder
    public static class QueryCriteria {
        private String subjectType;
        private String type;
        private String acquisitionTimestampThresholdFrom;
        private String acquisitionTimestampThresholdTo;
    }

}
