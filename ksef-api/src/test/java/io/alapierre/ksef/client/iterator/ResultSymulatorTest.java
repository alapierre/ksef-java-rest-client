package io.alapierre.ksef.client.iterator;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.11.19
 */
class ResultSymulatorTest {

    @Test
    void testResults() {

        ResultSymulator<Integer> symulator = new ResultSymulator<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 2);

        for (int i = 0; i < 6; i++) {
            val r = symulator.get(i);
            System.out.printf("page num: %d\n", r.getPageOffset());
            r.getItems().forEach(System.out::println);
        }

    }

}
