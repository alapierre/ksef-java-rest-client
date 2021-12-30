package io.alapierre.ksef.client.api;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.30
 */
class PublicKeyEncoderTest {

    @Test
    void createRequest() throws URISyntaxException, IOException {

        val pk = PublicKeyEncoder.class.getClassLoader().getResourceAsStream("mfPublicKey.der");

        PublicKeyEncoder encoder = PublicKeyEncoder.withBundledKey();

        val token = encoder.encodeSessionToken("1234", new Date());

        Assertions.assertNotNull(token);

        System.out.println(token);
    }

    @Test
    void testParseDate() throws ParseException {

        //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        val str = "2021-12-30T13:13:30.170Z";
        val formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        System.out.println(formatter.format(new Date()));

        val d = formatter.parse(str);
        System.out.println(d);
    }

}
