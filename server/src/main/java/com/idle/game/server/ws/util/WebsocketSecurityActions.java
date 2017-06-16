package com.idle.game.server.ws.util;


import java.security.Principal;
import java.security.acl.Group;
import java.util.Set;
import javax.security.auth.Subject;
import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.identity.Identity;
import org.jboss.security.identity.Role;
import org.jboss.security.identity.extensions.CredentialIdentityFactory;
import org.jboss.security.identity.plugins.SimpleRoleGroup;

/**
 *
 * @author rafael
 */
public class WebsocketSecurityActions {

    public static Principal getPrincipal() {
        return SecurityContextAssociation.getPrincipal();
    }

    public static Subject getSubject() {
        return SecurityContextAssociation.getSubject();
    }

    public static Object getCredential() {
        return SecurityContextAssociation.getCredential();
    }

    public static void setSubjectInfo(final Principal principal, final Subject subject, final Object credential) {
        final SecurityContext securityContext = SecurityContextAssociation.getSecurityContext();
        final Role roleGroup = getRoleGroup(subject);
        final Identity identity = CredentialIdentityFactory.createIdentity(principal, credential, roleGroup);
        securityContext.getUtil().createSubjectInfo(identity, subject);
    }

    public static Role getRoleGroup(final Subject subject) {
        final Set<Group> groups = subject.getPrincipals(Group.class);
        for (Group group : groups) {
            if ("Roles".equals(group.getName())) {
                return new SimpleRoleGroup(group);
            }
        }
        return null;
    }
}