package io.alapierre.ksef.client.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.alapierre.ksef.client.AbstractApiClient.Environment;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneFakturaApi;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.12.21
 */
@Slf4j
public class QrGenerator {

    private QrGenerator() {
    }

    /**
     * Generates a verification link for a given environment, KSeF number, and invoice.
     *
     * @param environment The environment to generate the link for.
     * @param ksefNumber The KSEF number.
     * @param invoice The invoice as a byte array.
     * @return The verification link.
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available.
     * @throws UnsupportedEncodingException if the UTF-8 charset is not supported.
     */
    @SneakyThrows
    public static String verificationLink(Environment environment, String ksefNumber, byte[] invoice) {

        val digest = MessageDigest.getInstance("SHA-256");
        val sig = Base64.getEncoder().encodeToString(digest.digest(invoice));
        val encoded = URLEncoder.encode(sig, StandardCharsets.UTF_8);

        return String.format("%s/web/verify/%s/%s", environment.getUrl().replace("/api", ""), ksefNumber, encoded);
    }

    /**
     * Returns the verification link for invoice downloaded from KSeF.
     *
     * @param api The InterfejsyInteraktywneFakturaApi ksef API.
     * @param environment The environment to generate the link for.
     * @param ksefNumber The KSEF invoice number.
     * @param token The token for authentication.
     * @return The verification link as a string.
     * @throws ApiException if an error occurs during the KSeF API call.
     */
    public static String verificationLink(InterfejsyInteraktywneFakturaApi api, Environment environment, String ksefNumber, String token) throws ApiException {
        val out = new ByteArrayOutputStream();
        api.getInvoice(ksefNumber, token, out);
        return verificationLink(environment, ksefNumber, out.toByteArray());
    }

    /**
     * Generates a QR Code as a byte array for a given barcode text, width, and height.
     *
     * @param barcodeText The text that will be encoded in the barcode.
     * @param width The width of the barcode in pixels.
     * @param height The height of the barcode in pixels.
     * @return The generated barcode as a byte array.
     * @throws BarcodeGenerationException if a problem with barcode generation occurs
     */
    public static byte[] generateBarcode(@NonNull String barcodeText, int width, int height)  {
        try {
            val buf = new ByteArrayOutputStream();
            writeBarcode(barcodeText, width, height, buf);
            return buf.toByteArray();
        } catch (IOException ex) {
            log.debug(ex.getLocalizedMessage(), ex);
            throw new BarcodeGenerationException(ex);
        }
    }

    /**
     * Generates a QR Code barcode image with the given barcode text, width, and height, and writes it to the specified output stream as a PNG.
     *
     * @param barcodeText The text to be encoded in the barcode.
     * @param width The width of the barcode image in pixels.
     * @param height The height of the barcode image in pixels.
     * @param out The output stream to write the barcode image to. The OutputStream should be closed by the calling code.
     * @throws IOException if an I/O error occurs while writing the image to the output stream.
     * @throws BarcodeGenerationException if a problem with barcode generation occurs
     */
    public static void writeBarcode(@NonNull String barcodeText, int width, int height, OutputStream out) throws IOException {

        if (width <= 0 || height <= 0) {
            throw new BarcodeGenerationException("width and height must be positive", null);
        }

        try {
            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
        } catch (WriterException ex) {
            log.debug(ex.getLocalizedMessage(), ex);
            throw new BarcodeGenerationException("Failed to generate barcode: " + ex.getLocalizedMessage());
        }
    }

}
