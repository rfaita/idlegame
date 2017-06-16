package com.idle.game.server.service;

import java.security.Principal;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;

/**
 *
 * @author rafael
 */
public class BaseService {

    @Resource
    private SessionContext sessionContext;

    protected String getLoggedLinkedUser() {
        return getKeycloakPrincipal().getName();
    }

    protected String getLoggedLinkedUser(Principal principal) {
        return getKeycloakPrincipal(principal).getName();

    }

    protected KeycloakPrincipal<KeycloakSecurityContext> getKeycloakPrincipal(Principal principal) {
        return (KeycloakPrincipal<KeycloakSecurityContext>) (principal);
    }

    protected KeycloakPrincipal<KeycloakSecurityContext> getKeycloakPrincipal() {
        return getKeycloakPrincipal(sessionContext.getCallerPrincipal());
    }

    protected IDToken getIDToken(Principal principal) {
        return getKeycloakPrincipal(principal).getKeycloakSecurityContext().getIdToken();

    }

    protected IDToken getIDToken() {
        return getKeycloakPrincipal().getKeycloakSecurityContext().getIdToken();

    }

}
