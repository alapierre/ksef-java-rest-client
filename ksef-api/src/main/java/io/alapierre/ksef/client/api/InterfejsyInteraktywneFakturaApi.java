package io.alapierre.ksef.client.api;

import io.alapierre.io.IOUtils;
import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.model.rest.invoice.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.28
 */
@SuppressWarnings("unused")
@Slf4j
@RequiredArgsConstructor
public class InterfejsyInteraktywneFakturaApi {

    private final ApiClient apiClient;

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
        val response = apiClient.putJson("online/Invoice/Send", request, SendInvoiceResponse.class, token);
        return response.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    public InvoiceStatusResponse invoiceStatus(@NotNull String token, @NotNull String referenceNumber) throws ApiException {
        val ret = apiClient.getJson("online/Invoice/Status/" + referenceNumber, InvoiceStatusResponse.class, token);
        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
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
                            .type("plain")
                            .invoiceBody(contentBase64)
                            .build())
                    .build();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Brak algorytmu liczenia sumy kontrolnej SHA-256", e);
        }
    }

    public void getInvoice(@NotNull String referenceNumber, @NotNull String token, @NotNull OutputStream os) throws ApiException {
        val endpoint = String.format("online/Invoice/Get/%s", referenceNumber);
        apiClient.getStream(endpoint, token, os);
    }

    /**
     * Pobiera UPO dla podanego numeru referencyjnego sesji interaktywnej lub wsadowej. Przekształca wynik zwracany
     * z API ze String na ciąg bajtów zakodowany w UTF-8. Jeśli UPO nie jest dostępne, pole UpoDTO.upo będzie miało
     * wartość null.
     *
     * @param referenceNumber numer referencyjny zakończonej sesji interaktywnej lub wsadowej
     *
     * @return Odpowiedź z API z UPO w postaci ciągu bajtów (jeśli UPO jest dostępne)
     */
    public UpoDTO getUpo(@NotNull String referenceNumber) throws ApiException {
        val endpoint = String.format("common/Status/%s", referenceNumber);
        val resp = apiClient.getJson(endpoint, UpoResponse.class);

        val upo = resp.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));

        val decodedUpo = upo.getUpo() != null && !upo.getUpo().isEmpty()
                ? Base64.getDecoder().decode(upo.getUpo())
                : null;

        return UpoDTO.builder()
                .processingCode(upo.getProcessingCode())
                .upo(decodedUpo)
                .referenceNumber(upo.getReferenceNumber())
                .processingDescription(upo.getProcessingDescription())
                .build();
    }
}
