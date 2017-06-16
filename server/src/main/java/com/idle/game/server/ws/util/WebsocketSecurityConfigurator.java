package com.idle.game.server.ws.util;

import java.security.Principal;
import javax.security.auth.Subject;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 *
 * @author rafael
 */
public class WebsocketSecurityConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        final Principal principal = request.getUserPrincipal();
        final Subject subject = WebsocketSecurityActions.getSubject();
        final Object credential = WebsocketSecurityActions.getCredential();

        sec.getUserProperties().put(WebsocketSecurityInterceptor.SESSION_PRINCIPAL, principal);
        sec.getUserProperties().put(WebsocketSecurityInterceptor.SESSION_SUBJECT, subject);
        sec.getUserProperties().put(WebsocketSecurityInterceptor.SESSION_CREDENTIAL, credential);
    }

}
