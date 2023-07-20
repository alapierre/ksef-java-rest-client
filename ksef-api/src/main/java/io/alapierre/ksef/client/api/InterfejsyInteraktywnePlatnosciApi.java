package io.alapierre.ksef.client.api;

import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.model.rest.payment.KsefReferencesNumbers;
import io.alapierre.ksef.client.model.rest.payment.PaymentIdRequest;
import io.alapierre.ksef.client.model.rest.payment.PaymentIdResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.04.28
 */
@RequiredArgsConstructor
public class InterfejsyInteraktywnePlatnosciApi {

    private final ApiClient apiClient;

    public @NotNull PaymentIdResponse createPaymentIdentifier(@NonNull String token, final @NonNull PaymentIdRequest paymentIdRequest) throws ApiException {
        val endpoint = "/online/Payment/Identifier/Request";
        val ret = apiClient.postJson(endpoint, paymentIdRequest, PaymentIdResponse.class, token);
        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

    public KsefReferencesNumbers getReferenceNumbers(@NonNull String token, final @NonNull String paymentIdentifier) throws ApiException  {
        val endpoint = String.format("online/Payment/Identifier/GetReferenceNumbers/%s", paymentIdentifier);
        val ret = apiClient.getJson(endpoint, KsefReferencesNumbers.class, token);
        return ret.orElseThrow(() -> new ApiException("Nieprawidłowa odpowiedź z API"));
    }

}
