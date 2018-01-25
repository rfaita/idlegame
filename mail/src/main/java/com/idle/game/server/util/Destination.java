package com.idle.game.server.util;

/**
 *
 * @author rafael
 */
public class Destination {

    public static String publicMail() {
        return "/topic/public";
    }

    public static String privateMail(String user) {
        return "/queue/" + user + "#mail.private";
    }

    public static String privateMailUpdate(String user) {
        return "/queue/" + user + "#mail.private.update";
    }

    public static String privateMailDelete(String user) {
        return "/queue/" + user + "#mail.private.delete";
    }

}
