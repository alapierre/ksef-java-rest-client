package io.alapierre.ksef.client.model.rest.auth;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorisationChallengeRequest {

    private ContextIdentifier contextIdentifier;

    public enum IdentifierType {
        onip,
        nip,
        pesel
    }

}
