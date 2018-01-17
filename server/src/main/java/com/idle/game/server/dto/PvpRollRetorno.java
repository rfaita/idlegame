package com.idle.game.server.dto;

import com.idle.game.core.formation.BattleFormation;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class PvpRollRetorno implements Serializable {

    private Date expireDate;
    private BattleFormation formationHigher;
    private BattleFormation formationLower;
    private BattleFormation formationRandom;
    private String namePlayerHigher;
    private String namePlayerLower;
    private String namePlayerRandom;
    private Long pvpScoreHigher;
    private Long pvpScoreLower;
    private Long pvpScoreRandom;

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public BattleFormation getFormationHigher() {
        return formationHigher;
    }

    public void setFormationHigher(BattleFormation formationHigher) {
        this.formationHigher = formationHigher;
    }

    public BattleFormation getFormationLower() {
        return formationLower;
    }

    public void setFormationLower(BattleFormation formationLower) {
        this.formationLower = formationLower;
    }

    public BattleFormation getFormationRandom() {
        return formationRandom;
    }

    public void setFormationRandom(BattleFormation formationRandom) {
        this.formationRandom = formationRandom;
    }

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
    
    

}
