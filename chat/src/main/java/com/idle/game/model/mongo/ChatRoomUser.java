package com.idle.game.model.mongo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class ChatRoomUser implements Comparable<ChatRoomUser> {

    private String userId;
    private String userNickName;
    private String email;
    private Boolean online;
    private final Date joinedAt = new Date();

    public ChatRoomUser() {

    }

    public ChatRoomUser(String userId, String userNickName, String email) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.email = email;
        this.online = Boolean.FALSE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    @Override
    public String toString() {
        return this.getUserNickName();
    }

    @Override
    public int compareTo(ChatRoomUser chatRoomUser) {
        return this.getUserId().compareTo(chatRoomUser.getUserId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.userId);
        hash = 13 * hash + Objects.hashCode(this.userNickName);
        hash = 13 * hash + Objects.hashCode(this.email);
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
        final ChatRoomUser other = (ChatRoomUser) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.userNickName, other.userNickName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

}
