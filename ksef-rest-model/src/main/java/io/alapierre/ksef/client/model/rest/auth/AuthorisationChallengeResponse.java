package io.alapierre.ksef.client.model.rest.auth;

import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.21
 */
@Data
public class AuthorisationChallengeResponse {
    private String timestamp;
    private String challenge;
}
