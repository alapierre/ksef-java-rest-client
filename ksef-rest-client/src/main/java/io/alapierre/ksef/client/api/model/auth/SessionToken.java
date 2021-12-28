package io.alapierre.ksef.client.api.model.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.28
 */
@Data
@Builder
public class SessionToken {
    private String token;
    private Context context;
}
