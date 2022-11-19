package io.alapierre.ksef.client.iterator;

import io.alapierre.ksef.client.ApiException;
import lombok.val;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
public class KsefResultStream<T> {

    private int currentIndex = 0; // element index in currant page

    private int totalIndex = 0; // element index in whole result

    private PageableResult<T> currentPage;

    private int currentPageNumber = 0;

    private int currentPageSize;

    private int totalNumberOfElements;

    private PageSupplier<PageableResult<T>> supplier;

    public static <T> Stream<T> builder(PageSupplier<PageableResult<T>> supplier) throws ApiException {
        val in = new KsefResultStream<T>();
        return in.stream(supplier);
    }

    public Stream<T> stream(PageSupplier<PageableResult<T>> supplier) throws ApiException {

        this.supplier = supplier;
        nextPage(currentPageNumber);

        if(currentPage.getNumberOfElements() == 0) return Stream.empty();

        return Stream.iterate(next().apply(null), hasNext(), next());
    }

    private Predicate<T> hasNext() {
        return t -> totalIndex <= totalNumberOfElements;
    }

    private UnaryOperator<T> next() {
        return t -> {

            // Stream.iterate calls next before hasNext
            // this will protect for IndexOutOfBoundsException
            if( totalIndex == totalNumberOfElements) {
                totalIndex++;
                return null;
            }

            if(currentIndex >= currentPageSize) {
                try {
                    nextPage(++currentPageNumber);
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            }
            totalIndex++;
            return currentPage.getItems().get(currentIndex++);
        };
    }

    private void nextPage(int pageNumber) throws ApiException {
        currentPage = supplier.get(pageNumber);
        currentPageSize = currentPage.getItems().size();
        totalNumberOfElements = currentPage.getNumberOfElements();
        currentIndex = 0;
    }
}
