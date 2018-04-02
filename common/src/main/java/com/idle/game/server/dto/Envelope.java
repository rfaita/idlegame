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
    private List<String> errors;

    public Envelope(T data) {
        this.data = data;
    }

    public Envelope(List<String> errors) {
        this.errors = errors;
    }

    public Envelope(String error) {
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public Envelope() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setError(String error) {
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

}
