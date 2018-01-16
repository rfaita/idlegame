package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Reward implements Serializable {

    private Long gold;
    private Long soul;
    private Long ancientRune;
    private Long spiritCrystal;
    private Long goldPerSecond;
    private Long soulPerSecond;
    private Long ancientRunePerSecond;
    private Long spiritCrystalPerSecond;

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
