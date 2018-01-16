package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "player")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Player implements Serializable {

    @Id
    private String id;
    private String name;
    private Integer level;
    private String user;
    private Long gold = 0l;
    private Long soul = 0l;
    private Long ancientRune = 0l;
    //premium resource
    private Long spiritCrystal = 0l;
    private Long goldPerSecond = 0l;
    private Long soulPerSecond = 0l;
    private Long ancientRunePerSecond = 0l;
    private Long spiritCrystalPerSecond = 0l;
    private Date lastTimeResourcesCollected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getSoul() {
        return soul;
    }

    public void setSoul(Long soul) {
        this.soul = soul;
    }

    public Long getAncientRune() {
        return ancientRune;
    }

    public void setAncientRune(Long ancientRune) {
        this.ancientRune = ancientRune;
    }

    public Long getSpiritCrystal() {
        return spiritCrystal;
    }

    public void setSpiritCrystal(Long spiritCrystal) {
        this.spiritCrystal = spiritCrystal;
    }

    public Long getGoldPerSecond() {
        return goldPerSecond;
    }

    public void setGoldPerSecond(Long goldPerSecond) {
        this.goldPerSecond = goldPerSecond;
    }

    public Long getSoulPerSecond() {
        return soulPerSecond;
    }

    public void setSoulPerSecond(Long soulPerSecond) {
        this.soulPerSecond = soulPerSecond;
    }

    public Long getAncientRunePerSecond() {
        return ancientRunePerSecond;
    }

    public void setAncientRunePerSecond(Long ancientRunePerSecond) {
        this.ancientRunePerSecond = ancientRunePerSecond;
    }

    public Long getSpiritCrystalPerSecond() {
        return spiritCrystalPerSecond;
    }

    public void setSpiritCrystalPerSecond(Long spiritCrystalPerSecond) {
        this.spiritCrystalPerSecond = spiritCrystalPerSecond;
    }

    public Date getLastTimeResourcesCollected() {
        return lastTimeResourcesCollected;
    }

    public void setLastTimeResourcesCollected(Date lastTimeResourcesCollected) {
        this.lastTimeResourcesCollected = lastTimeResourcesCollected;
    }

}
