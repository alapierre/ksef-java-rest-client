package io.alapierre.ksef.client.api.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.21
 */
@Data
@Builder
public class AuthorisationChallengeRequest {

    private ContextIdentifier contextIdentifier;

    @Data
    @Builder
    public static class ContextIdentifier {
        private IdentifierType identifierType;
        private String identifier;
    }

    public enum IdentifierType {
        onip,
        nip,
        pesel
    }

}
