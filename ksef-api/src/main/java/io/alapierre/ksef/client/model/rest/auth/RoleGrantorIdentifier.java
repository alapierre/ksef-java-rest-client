package io.alapierre.ksef.client.model.rest.auth;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.28
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleGrantorIdentifier {
    private String type;
    private String identifier;
}
