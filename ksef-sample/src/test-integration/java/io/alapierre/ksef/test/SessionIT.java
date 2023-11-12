package io.alapierre.ksef.test;

import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.11.07
 */
public class SessionIT extends BaseIT {

    @Test
    void sessionStatus() throws Exception {

        val initSessionResponse = login();
        val sessionToken = initSessionResponse.getSessionToken().getToken();
        System.out.println(initSessionResponse);

        val resp = sessionApi.sessionStatusByReference(sessionToken, initSessionResponse.getReferenceNumber(), 10, 0, false);

        System.out.println(resp);
    }

    @Test
    void upoUrl() throws Exception {
        val res = invoiceApi.sessionStatusWithUpoURL("20231107-SE-AD6366A80C-4A0B3BDAD8-1D");
        System.out.println(res);
    }

}
