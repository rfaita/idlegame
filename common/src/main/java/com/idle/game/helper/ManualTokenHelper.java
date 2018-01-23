package com.idle.game.helper;

import java.security.Principal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class ManualTokenHelper {

    private AccessToken accessToken;

    public void createAccessToken(Principal principal) {
        KeycloakSecurityContext ksc = ((KeycloakAuthenticationToken) principal).getAccount().getKeycloakSecurityContext();
        //Hacking, set the token inside the context
        this.accessToken = ksc.getToken();
        this.accessToken.setAccessTokenHash(ksc.getTokenString());
    }

    public String getToken() {
        return accessToken.getAccessTokenHash();
    }

    public String getNickName() {
        return accessToken.getPreferredUsername();
    }

    public String getUser() {
        return accessToken.getSubject();
    }

    public String getLocale() {
        return accessToken.getLocale();
    }

}
