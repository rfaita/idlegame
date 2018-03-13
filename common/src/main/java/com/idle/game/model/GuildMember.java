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
    private String guild;
    private String userMember;
    private String nameMember;
    private Date since;
    private Boolean accepted = Boolean.FALSE;
    private GuildMemberType type = GuildMemberType.MEMBER;

    public GuildMember() {
    }

    public GuildMember(String guild, String userMember, String nameMember) {
        this.guild = guild;
        this.userMember = userMember;
        this.nameMember = nameMember;
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

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public String getUserMember() {
        return userMember;
    }

    public void setUserMember(String userMember) {
        this.userMember = userMember;
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
