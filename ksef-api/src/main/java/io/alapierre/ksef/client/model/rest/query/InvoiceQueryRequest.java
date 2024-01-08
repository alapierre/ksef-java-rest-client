package io.alapierre.ksef.client.model.rest.query;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceQueryRequest {

    private QueryCriteria queryCriteria;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QueryCriteria {
        private String subjectType;
        private String type;
        private String acquisitionTimestampThresholdFrom;
        private String acquisitionTimestampThresholdTo;
    }

}
