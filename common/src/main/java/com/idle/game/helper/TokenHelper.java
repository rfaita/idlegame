package com.idle.game.helper;

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

    public String getUser() {
        return accessToken.getSubject();
    }

    public String getLocale() {
        return accessToken.getLocale();
    }

}
