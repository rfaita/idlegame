package com.idle.game.server.util;

/**
 *
 * @author rafael
 */
public class Destination {

    public static String publicMessages(String chatRoom) {
        return "/topic/" + chatRoom + "#messages.public";
    }

    public static String privateMessages(String user) {
        return "/queue/" + user + "#messages.private";
    }

    public static String connectedUsers(String chatRoom) {
        return "/topic/" + chatRoom + "#users.connected";
    }
}
