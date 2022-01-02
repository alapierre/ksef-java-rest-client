package io.alapierre.ksef.client.model.rest.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.28
 */
@Data
@Builder
public class CredentialsRoleList {
    private String type;
    private String roleType;
    private String roleDescription;
    private RoleGrantorIdentifier roleGrantorIdentifier;
}
