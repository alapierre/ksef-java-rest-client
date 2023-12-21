package io.alapierre.ksef.client.qr;

import io.alapierre.ksef.client.AbstractApiClient;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.12.21
 */
class QrGeneratorTest {

    @Test
    void verificationLinkByHash() throws Exception {

        val link = QrGenerator.verificationLink(
                AbstractApiClient.Environment.TEST,
                "6891152920-20231221-B3242FB4B54B-DF",
                Files.readAllBytes(Path.of("src/test/resources/6891152920-20231221-B3242FB4B54B-DF.xml")));

        System.out.println(link);

        Assertions.assertEquals(
                "https://ksef-test.mf.gov.pl/web/verify/6891152920-20231221-B3242FB4B54B-DF/ssTckvmMFEeA3vp589ExHzTRVhbDksjcFzKoXi4K%2F%2F0%3D",
                link);
    }

    @Test
    void verificationLinkByKsef() {
    }

    @Test
    void barcode() throws Exception {

        val barcodeText = "https://ksef-test.mf.gov.pl/web/verify/6891152920-20231221-B3242FB4B54B-DF/ssTckvmMFEeA3vp589ExHzTRVhbDksjcFzKoXi4K%2F%2F0%3D";

        val res = QrGenerator.generateBarcode(barcodeText, 200, 200);
        File f = File.createTempFile("barcode", ".png");
        System.out.println(f);
        Files.write(f.toPath(), res);
    }

}
