package com.idle.game.server.dto;

import java.io.Serializable;

/**
 *
 * @author rafael
 * @param <T>
 */
public class SocketMessage<T> implements Serializable {

    private String action;
    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SocketMessage(String action) {
        this.action = action;
    }

    public SocketMessage() {
    }

    public SocketMessage(String action, T object) {
        this.action = action;
        this.object = object;
    }

}
