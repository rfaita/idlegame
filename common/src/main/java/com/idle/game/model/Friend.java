package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
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
    private String nameFriend;
    private Date since;
    private Boolean reverse = Boolean.FALSE;
    private Boolean accepted = Boolean.FALSE;

    public Friend() {
    }

    public Friend(String user, String userFriend, String nameFriend) {
        this.user = user;
        this.userFriend = userFriend;
        this.nameFriend = nameFriend;
    }

    public Boolean getReverse() {
        return reverse;
    }

    public void setReverse(Boolean reverse) {
        this.reverse = reverse;
    }

    public String getNameFriend() {
        return nameFriend;
    }

    public void setNameFriend(String nameFriend) {
        this.nameFriend = nameFriend;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
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
