package io.alapierre.ksef.client.model.rest.auth;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsRoleList {
    private String type;
    private String roleType;
    private String roleDescription;
    private RoleGrantorIdentifier roleGrantorIdentifier;
}
