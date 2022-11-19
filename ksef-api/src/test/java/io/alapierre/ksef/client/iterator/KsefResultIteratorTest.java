package io.alapierre.ksef.client.iterator;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
class KsefResultIteratorTest {

    @Test
    void testIterate() throws Exception {

        val iterator = new KsefResultIterator<Integer>();

        ResultSymulator<Integer> symulator = new ResultSymulator<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 2);
        val res = iterator.iterate(symulator);

        System.out.println("elements: " + res.size());
        res.forEach(System.out::println);

        Assertions.assertEquals(11,res.size());
    }

    @Test
    void testIterateWithFilter() throws Exception {

        val iterator = new KsefResultIterator<Integer>(integer -> integer != 1);

        ResultSymulator<Integer> symulator = new ResultSymulator<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 2);
        val res = iterator.iterate(symulator);

        System.out.println("elements: " + res.size());
        res.forEach(System.out::println);

        Assertions.assertEquals(10,res.size());
    }

}
