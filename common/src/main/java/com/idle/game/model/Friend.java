package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    private String userId;
    private String friendUserId;
    private String friendUserNickName;
    private Date since;
    private Boolean reverse = Boolean.FALSE;
    private Boolean accepted = Boolean.FALSE;

    @Transient
    private transient User user;

    public Friend() {
    }

    public Friend(String userId, String friendUserId, String friendUserNickName) {
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.friendUserNickName = friendUserNickName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReverse(Boolean reverse) {
        this.reverse = reverse;
    }

    public Boolean getReverse() {
        return reverse;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getFriendUserNickName() {
        return friendUserNickName;
    }

    public void setFriendUserNickName(String friendUserNickName) {
        this.friendUserNickName = friendUserNickName;
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

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

}
