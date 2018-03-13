package com.idle.game.server.util;

/**
 *
 * @author rafael
 */
public class Destination {

    public static String resourceRefresh(String user) {
        return "/queue/" + user + "#resource.refresh";
    }
    
}
