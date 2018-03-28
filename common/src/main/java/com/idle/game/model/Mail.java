package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "mail")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Mail implements Serializable {

    @Id
    private String id;

    @Indexed(name = "expirationDate", expireAfterSeconds = 2592000)
    private final Date date = new Date();

    private String fromUserId;
    private String fromUserNickName;
    private String toUserId;
    private String toUserNickName;
    private Reward reward;
    private String text;

    private Boolean fromAdmin;

    private Boolean readed = Boolean.FALSE;
    private Boolean collected = Boolean.FALSE;

    private Boolean publicMail = Boolean.FALSE;

    public Boolean getPublicMail() {
        return publicMail;
    }

    public void setPublicMail(Boolean publicMail) {
        this.publicMail = publicMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserNickName() {
        return fromUserNickName;
    }

    public void setFromUserNickName(String fromUserNickName) {
        this.fromUserNickName = fromUserNickName;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserNickName() {
        return toUserNickName;
    }

    public void setToUserNickName(String toUserNickName) {
        this.toUserNickName = toUserNickName;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFromAdmin() {
        return fromAdmin;
    }

    public void setFromAdmin(Boolean fromAdmin) {
        this.fromAdmin = fromAdmin;
    }

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public Boolean getCollected() {
        return collected;
    }

    public void setCollected(Boolean collected) {
        this.collected = collected;
    }

    public Date getDate() {
        return date;
    }

}
