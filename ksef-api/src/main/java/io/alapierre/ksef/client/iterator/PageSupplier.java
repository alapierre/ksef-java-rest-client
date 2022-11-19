package io.alapierre.ksef.client.iterator;

import io.alapierre.ksef.client.ApiException;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
@FunctionalInterface
public interface PageSupplier<T> {
    T get(int page) throws ApiException;
}
