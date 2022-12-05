package io.alapierre.ksef.client.iterator;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
class KsefResultStreamTest {

    @Test
    void testStream() throws Exception {

        ResultSymulator<Integer> symulator = new ResultSymulator<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 2);
        //ResultSymulator<Integer> symulator = new ResultSymulator<>(List.of(1, 2, 3), 2);

        val result = KsefResultStream.builder(symulator)
                .collect(Collectors.toList());

        assertThat(result, hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

    }


}
