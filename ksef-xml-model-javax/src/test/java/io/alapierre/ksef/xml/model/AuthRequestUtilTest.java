package io.alapierre.ksef.xml.model;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.29
 */
class AuthRequestUtilTest {

    @Test
    void prepareTokenAuthRequest() {

        val enc = "uH6dmA9lr7/5izGMOTXVzIAe1awXZYZ61zLCEaImSLP+BftDtziV5I+4EaKvWZ+IUCkXHP8FEGDsLHfefUVLS5vbZ8r0W5UxiSqqOAKxvkE7pCfh31+AmKZxZa0TlXYK5EYa4RkJhm7HTgKe4WGZ/Y4G2PdCzdImtvZL49yqQQ3bLqeGeVJ9rMzkuxCbtKGbdopN1/V+64fvLClIuolWI+/z0FlxDVE9a0f8EFKPPpzGowVwJcn5PHBkvVh45vT+pyNbDSvEsyL/udRBXCioWbgRaZReRJNIg6YWZLTSHyi8BK4ofQVuhdcPwHjkETmEQ0aktj+uyqmxqvHbFTA93Q==";

        val res = AuthRequestUtil.prepareTokenAuthRequest("20211228-CR-B0E5A2E477-AEA77CD467-DA", "1111111111", enc);

        val serializer = new AuthTokenRequestSerializer();
        System.out.println(serializer.toString(res));

        Assertions.assertNotNull(res);

    }
}
