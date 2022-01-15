package io.alapierre.ksef.client.api;

import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationToken;
import io.alapierre.ksef.client.model.rest.auth.CredentialStatus;
import io.alapierre.ksef.client.model.rest.auth.GenerateTokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.11
 */
@Slf4j
@RequiredArgsConstructor
public class InterfejsyInteraktywneUprawnieniaApi {

    public static final String BAD_API_RESPONSE = "Nieprawidłowa odpowiedź z API";

    private final ApiClient apiClient;

    /**
     * Sprawdzenie statusu poświadczeń
     *
     * @param token token sesyjny
     * @param credentialsElementReferenceNumber numer referencyjny obiektu poświadczeń referenceNumber z odpowiedzi na żądanie generowania tokena lub nadania uprawnień
     * @return Status poświadczeń
     * @throws ApiException w przypadku błędu komunikacji z API
     */
    public CredentialStatus credentialStatus(@NotNull String token, @NotNull String credentialsElementReferenceNumber) throws ApiException {
        val endpoint= String.format("online/Credentials/Status/%s", credentialsElementReferenceNumber);
        val ret = apiClient.getJson(endpoint, CredentialStatus.class, token);
        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

    /**
     * Generowanie tokena autoryzacyjnego — wersja uproszczona. Nadaje ten sam opis tokenowi i wszystkim zawartym w nim rolom.
     *
     * @param tokenDescription opis tokena i wszystkich nadanych ról
     * @param token token sesyjny
     * @param roles tablica ról (uprawnień) dla generowanego tokena autoryzacyjnego
     * @return Wygenerowany token oraz jego metadane
     * @throws ApiException w przypadku błędu komunikacji z API
     */
    @NotNull
    public AuthorisationToken generateToken(@NotNull String token, @NotNull String tokenDescription, GenerateTokenRequest.RoleType... roles) throws ApiException {

        val rolesConverted = Arrays.stream(roles).map(roleType -> GenerateTokenRequest.TokenCredentialsRoleList.builder()
                .roleDescription(tokenDescription)
                .roleType(roleType)
                .build()).collect(Collectors.toSet());

        return generateToken(tokenDescription, token, rolesConverted);
    }

    /**
     * Generowanie tokena autoryzacyjnego — wersja pełna. Pozwala nadać dowolne opisy dla ról w tokenie
     *
     * @param tokenDescription opis tokena
     * @param token token sesyjny
     * @param roles kolekcja unikalnych ról wraz z metadanymi
     * @return Wygenerowany token oraz jego metadane
     * @throws ApiException w przypadku błędu komunikacji z API
     */
    @NotNull
    public AuthorisationToken generateToken(@NotNull String tokenDescription, @NotNull String token, @NotNull Set<GenerateTokenRequest.TokenCredentialsRoleList> roles) throws ApiException {

        val req = GenerateTokenRequest.builder()
                .generateToken(GenerateTokenRequest.GenerateToken.builder()
                        .credentialsRoleList(roles)
                        .description(tokenDescription)
                        .build())
                .build();

        val ret = apiClient.postJson("online/Credentials/GenerateToken", req, AuthorisationToken.class, token);
        return ret.orElseThrow(() -> new ApiException(BAD_API_RESPONSE));
    }

}
