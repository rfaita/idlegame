package com.idle.game.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 *
 * @author rafael
 */
public class HeaderUtil {

    public static HttpHeaders getAuthHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }

}
