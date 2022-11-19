package io.alapierre.ksef.client.iterator;

import io.alapierre.ksef.client.model.rest.auth.SessionStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
@RequiredArgsConstructor
public class SessionStatusAdapter implements PageableResult<SessionStatus.InvoiceStatusList> {

    private final SessionStatus sessionStatus;

    @Override
    public int getNumberOfElements() {
        return sessionStatus.getNumberOfElements();
    }

    @Override
    public int getPageSize() {
        return sessionStatus.getPageSize();
    }

    @Override
    public int getPageOffset() {
        return sessionStatus.getPageOffset();
    }

    @Override
    public List<SessionStatus.InvoiceStatusList> getItems() {
        return sessionStatus.getInvoiceStatusList();
    }
}
