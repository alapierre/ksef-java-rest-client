package io.alapierre.ksef.client.api.model.auth;

import io.alapierre.ksef.client.api.model.AuthorisationChallengeRequest;
import lombok.Builder;
import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.28
 */
@Data
@Builder
public class ContextIdentifier {
    private AuthorisationChallengeRequest.IdentifierType type;
    private String identifier;
}
