package io.alapierre.ksef.client.iterator;

import io.alapierre.ksef.client.ApiException;
import lombok.val;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
public class KsefResultIterator<T> {

    private final Predicate<T> filter;

    public KsefResultIterator(Predicate<T> filter) {
        this.filter = filter;
    }

    public KsefResultIterator() {
        this.filter = t -> true;
    }

    public List<T> iterate(PageSupplier<PageableResult<T>> supplier) throws ApiException {

        int numberOfPages;
        int page = 0;

        val values = new LinkedList<T>();

        do {

            val result = supplier.get(page);
            val numberOfRows = result.getNumberOfElements();

            values.addAll(result.getItems().stream()
                    .filter(filter)
                    .collect(Collectors.toList()));

            numberOfPages = (int) Math.ceil((double) numberOfRows / result.getPageSize());
            page++;

        } while (page < numberOfPages);

        return values;
    }
}

