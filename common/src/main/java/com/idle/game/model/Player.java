package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "player")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Player implements Serializable {

    @Id
    private String id;
    private Integer level;
    private String name;
    private String linkedUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkedUser() {
        return linkedUser;
    }

    public void setLinkedUser(String linkedUser) {
        this.linkedUser = linkedUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 733 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return Objects.equals(this.id, other.id);
    }

}
