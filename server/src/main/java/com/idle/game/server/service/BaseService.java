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

    protected IDToken getIDToken() {
        KeycloakPrincipal<KeycloakSecurityContext> kcPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) (sessionContext.getCallerPrincipal());
        return kcPrincipal.getKeycloakSecurityContext().getIdToken();

    }


}
