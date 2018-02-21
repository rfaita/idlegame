package com.idle.game.model.mongo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class ChatRoomUser implements Comparable<ChatRoomUser> {

    private String user;
    private String nickName;
    private String email;
    private Boolean online;
    private final Date joinedAt = new Date();

    public ChatRoomUser() {

    }

    public ChatRoomUser(String user, String nickName, String email) {
        this.user = user;
        this.nickName = nickName;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    @Override
    public String toString() {
        return this.nickName;
    }

    @Override
    public int compareTo(ChatRoomUser chatRoomUser) {
        return this.getUser().compareTo(chatRoomUser.getUser());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.user);
        hash = 13 * hash + Objects.hashCode(this.nickName);
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
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.nickName, other.nickName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

}
