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
@Document(collection = "guildMember")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GuildMember implements Serializable {

    @Id
    private String id;
    private String guildId;
    private String userMemberId;
    private String nameMember;
    private Date since;
    private Boolean accepted = Boolean.FALSE;
    private GuildMemberType type = GuildMemberType.MEMBER;

    @Transient
    private transient Player player;

    public GuildMember() {
    }

    public GuildMember(String guildId, String userMemberId, String nameMember) {
        this.guildId = guildId;
        this.userMemberId = userMemberId;
        this.nameMember = nameMember;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public String getUserMemberId() {
        return userMemberId;
    }

    public void setUserMemberId(String userMemberId) {
        this.userMemberId = userMemberId;
    }

    public String getNameMember() {
        return nameMember;
    }

    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
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
