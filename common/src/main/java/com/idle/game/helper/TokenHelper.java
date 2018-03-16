package com.idle.game.helper;

import java.util.Set;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class TokenHelper {

    @Autowired
    private AccessToken accessToken;

    public String getNickName() {
        return accessToken.getPreferredUsername();
    }

    public String getToken() {
        return accessToken.getAccessTokenHash();
    }

    public String getSubject() {
        return accessToken.getSubject();
    }

    public String getEmail() {
        return accessToken.getEmail();
    }

    public String getLocale() {
        return accessToken.getLocale();
    }

    public Boolean isAdmin() {
        return this.getRoles().contains("ADMIN");
    }

    public Set<String> getRoles() {
        return accessToken.getRealmAccess().getRoles();
    }

}
