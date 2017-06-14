package com.idle.game.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class PvpRoll implements Serializable {

    private Date expireDate;
    private Formation formationHigher;
    private Formation formationLower;
    private Formation formationRandom;
    private String namePlayerHigher;
    private String namePlayerLower;
    private String namePlayerRandom;
    private Long pvpScoreHigher;
    private Long pvpScoreLower;
    private Long pvpScoreRandom;

    public String getNamePlayerHigher() {
        return namePlayerHigher;
    }

    public void setNamePlayerHigher(String namePlayerHigher) {
        this.namePlayerHigher = namePlayerHigher;
    }

    public String getNamePlayerLower() {
        return namePlayerLower;
    }

    public void setNamePlayerLower(String namePlayerLower) {
        this.namePlayerLower = namePlayerLower;
    }

    public String getNamePlayerRandom() {
        return namePlayerRandom;
    }

    public void setNamePlayerRandom(String namePlayerRandom) {
        this.namePlayerRandom = namePlayerRandom;
    }

    public Long getPvpScoreHigher() {
        return pvpScoreHigher;
    }

    public void setPvpScoreHigher(Long pvpScoreHigher) {
        this.pvpScoreHigher = pvpScoreHigher;
    }

    public Long getPvpScoreLower() {
        return pvpScoreLower;
    }

    public void setPvpScoreLower(Long pvpScoreLower) {
        this.pvpScoreLower = pvpScoreLower;
    }

    public Long getPvpScoreRandom() {
        return pvpScoreRandom;
    }

    public void setPvpScoreRandom(Long pvpScoreRandom) {
        this.pvpScoreRandom = pvpScoreRandom;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Formation getFormationHigher() {
        return formationHigher;
    }

    public void setFormationHigher(Formation formationHigher) {
        this.formationHigher = formationHigher;
    }

    public Formation getFormationLower() {
        return formationLower;
    }

    public void setFormationLower(Formation formationLower) {
        this.formationLower = formationLower;
    }

    public Formation getFormationRandom() {
        return formationRandom;
    }

    public void setFormationRandom(Formation formationRandom) {
        this.formationRandom = formationRandom;
    }

}
