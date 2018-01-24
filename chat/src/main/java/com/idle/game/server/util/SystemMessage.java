package com.idle.game.server.util;

/**
 *
 * @author rafael
 */
public enum SystemMessage {
    
    JOIN("join.chat.room"), LEAVE("leave.chat.room"), PING("join.to.chat.room");
    
    private final String message;

    public String getMessage() {
        return message;
    }

    private SystemMessage(String message) {
        this.message = message;
    }
    
    
    
}
