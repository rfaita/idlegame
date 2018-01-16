package com.idle.game.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 * @param <T>
 */
public class Envelope<T> implements Serializable {

    private T data;
    private List<Error> errors;

    public Envelope(T data) {
        this.data = data;
    }

    public Envelope() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void setError(Error error) {
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

}
