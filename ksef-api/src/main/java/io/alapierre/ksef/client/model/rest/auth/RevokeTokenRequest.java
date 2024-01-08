package io.alapierre.ksef.client.model.rest.auth;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevokeTokenRequest {

    private RevokeToken revokeToken;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RevokeToken {

        private SourceTokenIdentifier sourceTokenIdentifier;
        private String tokenNumber;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SourceTokenIdentifier {

        private String identifier;
        private AuthorisationChallengeRequest.IdentifierType type;
    }
}
