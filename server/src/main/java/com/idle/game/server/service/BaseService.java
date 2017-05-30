package com.idle.game.server.service;

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

    protected KeycloakPrincipal<KeycloakSecurityContext> getKeycloakPrincipal() {
        return (KeycloakPrincipal<KeycloakSecurityContext>) (sessionContext.getCallerPrincipal());
    }

    protected IDToken getIDToken() {
        return getKeycloakPrincipal().getKeycloakSecurityContext().getIdToken();

    }

}
