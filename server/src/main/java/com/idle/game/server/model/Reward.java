package com.idle.game.server.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "reward")
public class Reward implements Serializable {

    @Id
    @SequenceGenerator(name = "seqReward", sequenceName = "seqReward", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqReward")
    private Long id;
    private Long gold;
    private Long soul;
    private Long ancientRune;
    private Long spiritCrystal;
    private Long goldPerSecond;
    private Long soulPerSecond;
    private Long ancientRunePerSecond;
    private Long spiritCrystalPerSecond;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
