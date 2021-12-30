package io.alapierre.ksef.client.api;

import io.alapierre.io.IOUtils;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static io.alapierre.ksef.client.api.Constants.DATE_FORMAT_PATTERN;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.27
 */
public class PublicKeyEncoder {

    private final byte[] publicKey;

    public static PublicKeyEncoder withBundledKey() {
        val pk = PublicKeyEncoder.class.getClassLoader().getResourceAsStream("mfPublicKey.der");
        if (pk == null) throw new IllegalStateException("Can't load bundled key mfPublicKey.der from classpath");
        return new PublicKeyEncoder(pk);
    }

    @SneakyThrows
    public PublicKeyEncoder(@NotNull InputStream publicKeyStream) {
        publicKey = IOUtils.toByteArray(publicKeyStream);
    }

    public static @NotNull Date parseChallengeTimestamp(@NotNull String challengeTimestamp) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(challengeTimestamp);
    }

    @SneakyThrows
    public @NotNull String encodeSessionToken(@NotNull String token, @NotNull Date challengeTimestamp) {

        val x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
        val keyFactory = KeyFactory.getInstance("RSA");
        val publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        val message = (token + "|" + challengeTimestamp.getTime()).getBytes(StandardCharsets.UTF_8);

        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        val encrypted = cipher.doFinal(message);

        return Base64.getEncoder().encodeToString(encrypted);
    }
}
