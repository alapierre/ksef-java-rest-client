package io.alapierre.ksef.xml.model;

import lombok.val;
import lombok.var;
import org.junit.jupiter.api.Test;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.23
 */
class AuthRequestSerializerTest {

    @Test
    void testSerializer() {

        AuthRequestSerializer serializer = new AuthRequestSerializer();

        val request = AuthRequestUtil.prepareAuthRequest(
                "20211001-CR-FFFFFFFFFF-FFFFFFFFFF-FF", "1111111111");

        var res = serializer.toString(request, true);

        System.out.println(res);

    }

}
