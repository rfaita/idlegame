package com.idle.game.core;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class BaseObject implements Serializable {

    protected UUID uuid;

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

}
