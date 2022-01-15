package io.alapierre.ksef.client.model.rest.auth;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author Karol Bryzgiel {@literal karol.bryzgiel@soft-project.pl}
 * Copyrights by original author 2022.01.06
 */
@Data
@Builder
public class GenerateTokenRequest {

    private GenerateToken generateToken;

    @Data
    @Builder
    public static class GenerateToken {
        private String description;
        private Set<TokenCredentialsRoleList> credentialsRoleList;
    }

    @Data
    @Builder
    public static class TokenCredentialsRoleList {
        private RoleType roleType;
        private String roleDescription;
    }

    public enum RoleType {
        invoice_read,
        invoice_write,
        payment_confirmation_write,
        credentials_read,
        credentials_manage,
        enforcement_operations
    }

}
