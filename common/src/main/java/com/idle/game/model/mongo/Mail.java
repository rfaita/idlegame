package com.idle.game.model.mongo;

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

    private String fromUser;
    private String fromNickName;
    private String toUser;
    private String toNickName;
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

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromNickName() {
        return fromNickName;
    }

    public void setFromNickName(String fromNickName) {
        this.fromNickName = fromNickName;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
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
