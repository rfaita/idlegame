package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "friend")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Friend implements Serializable {

    @Id
    private String id;
    private String user;
    private String userFriend;
    private Boolean accepted = Boolean.FALSE;

    public Friend() {
    }

    public Friend(String user, String userFriend) {
        this.user = user;
        this.userFriend = userFriend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(String userFriend) {
        this.userFriend = userFriend;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

}
