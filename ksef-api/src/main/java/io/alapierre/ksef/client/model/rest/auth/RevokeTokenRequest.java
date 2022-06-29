package io.alapierre.ksef.client.model.rest.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevokeTokenRequest {

    private RevokeToken revokeToken;

    @Data
    @Builder
    public static class RevokeToken {

        private SourceTokenIdentifier sourceTokenIdentifier;
        private String tokenNumber;
    }

    @Data
    @Builder
    public static class SourceTokenIdentifier {

        private String identifier;
        private AuthorisationChallengeRequest.IdentifierType type;
    }
}
