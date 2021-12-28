package io.alapierre.ksef.client.api;

import io.alapierre.io.IOUtils;
import io.alapierre.ksef.client.api.model.invoice.SendInvoiceRequest;
import io.alapierre.ksef.client.api.model.invoice.SendInvoiceResponse;
import io.alapierre.ksef.client.internal.ApiClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.28
 */
@Slf4j
public class InterfejsyInteraktywneSesjaFaktura {

    private final ApiClient apiClient = new ApiClient();

    public @NotNull SendInvoiceResponse invoiceSend(File file, @NotNull String token) throws IOException, ApiException {
        try (InputStream in = new FileInputStream(file)) {
            return invoiceSend(in, token);
        }
    }

    public @NotNull SendInvoiceResponse invoiceSend(InputStream inputStream, @NotNull String token) throws IOException, ApiException {
        return invoiceSend(IOUtils.toByteArray(inputStream), token);
    }

    public @NotNull SendInvoiceResponse invoiceSend(byte[] invoiceBytes, @NotNull String token) throws ApiException {
        val request = prepareSendInvoiceRequest(invoiceBytes);
        val response = apiClient.postJson("online/Invoice/Send", request, SendInvoiceResponse.class, token);
        return response.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    public static SendInvoiceRequest prepareSendInvoiceRequest(byte[] invoiceBytes) {

        try {
            val messageDigest = MessageDigest.getInstance("SHA-256");
            val digest = messageDigest.digest(invoiceBytes);
            val digestBase64 = Base64.getEncoder().encodeToString(digest);
            val contentBase64 = Base64.getEncoder().encodeToString(invoiceBytes);

            return SendInvoiceRequest.builder()
                    .invoiceHash(SendInvoiceRequest.InvoiceHash.builder()
                            .fileSize(invoiceBytes.length)
                            .hashSHA(SendInvoiceRequest.InvoiceHash.HashSHA.builder()
                                    .algorithm("SHA-256")
                                    .encoding("Base64")
                                    .value(digestBase64)
                                    .build())
                            .build())
                    .invoicePayload(SendInvoiceRequest.InvoicePayload.builder()
                            .payloadType("plain")
                            .invoiceBody(contentBase64)
                            .build())
                    .build();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Brak algorytmu liczenia sumy kontrolnej SHA-256", e);
        }
    }

}
