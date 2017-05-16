package com.idle.game.server.dto;

import java.io.Serializable;

/**
 *
 * @author rafael
 * @param <T>
 */
public class Envelope<T> implements Serializable {

    private T data;
    private Error error;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
    
    
    
    
}
