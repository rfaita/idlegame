package com.idle.game.server.ws.util;



import java.security.Principal;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.security.auth.Subject;
import javax.websocket.Session;

/**
 *
 * @author rafael
 */
public class WebsocketSecurityInterceptor {

    public static final String SESSION_PRINCIPAL = "websocket.security.principal";
    public static final String SESSION_SUBJECT = "websocket.security.subject";
    public static final String SESSION_CREDENTIAL = "websocket.security.credential";

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        final Object[] params = ctx.getParameters();
        Session session = null;
        for (Object param : params) {
            if (param instanceof Session) {
                session = (Session) param;
                break;
            }
        }

        if (session != null) {
            final Principal principal = (Principal) session.getUserProperties().get(SESSION_PRINCIPAL);
            final Subject subject = (Subject) session.getUserProperties().get(SESSION_SUBJECT);
            final Object credential = session.getUserProperties().get(SESSION_CREDENTIAL);

            if (principal != null && subject != null) {
                WebsocketSecurityActions.setSubjectInfo(principal, subject, credential);
            }

        }

        return ctx.proceed();
    }

}