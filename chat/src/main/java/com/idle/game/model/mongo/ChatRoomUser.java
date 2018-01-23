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
    private final Date joinedAt = new Date();

    public ChatRoomUser() {

    }

    public ChatRoomUser(String user, String nickName) {
        this.user = user;
        this.nickName = nickName;
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
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.user);
        hash = 41 * hash + Objects.hashCode(this.nickName);
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
        return Objects.equals(this.nickName, other.nickName);
    }

}
