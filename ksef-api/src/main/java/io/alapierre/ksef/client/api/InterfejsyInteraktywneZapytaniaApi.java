package io.alapierre.ksef.client.api;

import io.alapierre.commons.date.DateUtils;
import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.model.rest.query.InvoiceQueryRequest;
import io.alapierre.ksef.client.model.rest.query.InvoiceQueryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.09
 */
@Slf4j
@RequiredArgsConstructor
public class InterfejsyInteraktywneZapytaniaApi {

    private final ApiClient apiClient;

    @NotNull
    public InvoiceQueryResponse invoiceQuery(@NotNull InvoiceQueryRequest request, int pageSize, int offset) throws ApiException {

        val endpoint = String.format("online/Query/Invoice/Sync/PageSize=%d&PageOffset=%d", pageSize, offset);

        val ret = apiClient.postJson(
                endpoint,
                request,
                InvoiceQueryResponse.class);

        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    public String convertDate(LocalDate date) {
        return convertDate(DateUtils.asDate(date));
    }

    public String convertDate(Date date) {
        val df = new SimpleDateFormat(ApiClient.QUERY_DATE_FORMAT_PATTERN);
        return df.format(date);
    }


}
