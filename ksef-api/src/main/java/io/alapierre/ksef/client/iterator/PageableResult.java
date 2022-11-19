package io.alapierre.ksef.client.iterator;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
public interface PageableResult<T> {
    int getNumberOfElements();

    int getPageSize();

    int getPageOffset();

    List<T> getItems();
}
