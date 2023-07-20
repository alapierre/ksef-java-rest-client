package io.alapierre.ksef.api.dss;

import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.token.PasswordInputCallback;
import eu.europa.esig.dss.token.PrefilledPasswordCallback;
import io.alapierre.crypto.dss.signer.AbstractSigner;
import io.alapierre.crypto.dss.signer.CardSigner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.nio.file.Paths;
import java.security.KeyStore;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.07.20
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalSignatureTest {

    private AbstractSigner signer;
    private final String pin = "";
    private final PasswordInputCallback callback = new PrefilledPasswordCallback(new KeyStore.PasswordProtection(pin.toCharArray()));

    @BeforeAll
    void init() {
        signer =  new CardSigner("/opt/proCertumSmartSign", "cryptoCertum3PKCS", 1, callback);
    }

    @Test
    @Disabled
    void testSign() throws Exception {

        DSSDocument signedDocument = signer.sign(Paths.get("src/test/resources", "pit_11.xml").toFile());

        File outFile = Paths.get("src/test/resources", "signed2.xml").toFile();
        signedDocument.save(outFile.getAbsolutePath());

    }

}
