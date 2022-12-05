package io.alapierre.ksef.client.iterator;

import io.alapierre.ksef.client.model.rest.query.InvoiceQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
@RequiredArgsConstructor
public class InvoiceQueryResponseAdapter implements PageableResult<InvoiceQueryResponse.InvoiceHeaderList>{

    private final InvoiceQueryResponse response;

    @Override
    public int getNumberOfElements() {
        return response.getNumberOfElements();
    }

    @Override
    public int getPageSize() {
        return response.getPageSize();
    }

    @Override
    public int getPageOffset() {
        return response.getPageOffset();
    }

    @Override
    public List<InvoiceQueryResponse.InvoiceHeaderList> getItems() {
        return response.getInvoiceHeaderList();
    }
}
