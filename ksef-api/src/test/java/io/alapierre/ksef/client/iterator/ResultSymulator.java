package io.alapierre.ksef.client.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
public class ResultSymulator <T> implements PageSupplier <PageableResult<T>>{

    private final List<List<T>> parts;
    private final int totalElements;
    private final int pageSize;

    public ResultSymulator(List<T> elements, int pageSize) {
        parts = chopped(elements, pageSize);
        totalElements = elements.size();
        this.pageSize = pageSize;
    }

    static <T> List<List<T>> chopped(List<T> list, final int pageSize) {
        List<List<T>> parts = new ArrayList<>();
        final int size = list.size();
        for (int i = 0; i < size; i += pageSize) {
            parts.add(new ArrayList<>(
                    list.subList(i, Math.min(size, i + pageSize)))
            );
        }
        return parts;
    }


    @Override
    public PageableResult<T> get(int page) {
        return new PageableResult<>() {
            @Override
            public int getNumberOfElements() {
                return totalElements;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public int getPageOffset() {
                return page;
            }

            @Override
            public List<T> getItems() {
                return parts.get(page);
            }
        };
    }
}
