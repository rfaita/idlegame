package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "guild")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Guild implements Serializable {

    @Id
    private String id;
    private final Date date = new Date();
    @Indexed
    private String ownerUserId;
    private String initialMessage;
    private Integer level;
    private String name;

    @Transient
    private transient List<GuildMember> members;
    @Transient
    private transient List<GuildMember> requests;

    public List<GuildMember> getRequests() {
        return requests;
    }

    public void setRequests(List<GuildMember> requests) {
        this.requests = requests;
    }

    public List<GuildMember> getMembers() {
        return members;
    }

    public void setMembers(List<GuildMember> members) {
        this.members = members;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getInitialMessage() {
        return initialMessage;
    }

    public void setInitialMessage(String initialMessage) {
        this.initialMessage = initialMessage;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
