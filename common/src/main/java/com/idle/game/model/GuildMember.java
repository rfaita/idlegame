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
@Document(collection = "guildMember")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GuildMember implements Serializable {

    @Id
    private String id;
    private String guildId;
    private String userId;
    private String userNickname;
    private Date since;
    private Boolean accepted = Boolean.FALSE;
    private GuildMemberType type = GuildMemberType.MEMBER;

    public GuildMember() {
    }

    public GuildMember(String guildId, String userId, String userNickname) {
        this.guildId = guildId;
        this.userId = userId;
        this.userNickname = userNickname;
    }

    public GuildMemberType getType() {
        return type;
    }

    public void setType(GuildMemberType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

}
