package io.alapierre.ksef.client.iterator;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
class KsefResultStreamTest {

    @Test
    void testStream() throws Exception {

        //ResultSymulator<Integer> symulator = new ResultSymulator<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 2);
        ResultSymulator<Integer> symulator = new ResultSymulator<>(List.of(1, 2, 3), 2);

        val stream = new KsefResultStream<Integer>();
        stream.stream(symulator)
                .forEach(System.out::println);


    }


}
