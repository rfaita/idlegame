package com.idle.game.core.battle;

import com.idle.game.core.hero.BattleHero;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.type.FormationPosition;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;

/**
 *
 * @author rafael
 */
public class BattlePositionedHero implements Serializable {

    private Integer energy = 0;
    private BattleTeamType battleTeamType;
    private FormationPosition position;
    private BattleHero hero;

    public FormationPosition getPosition() {
        return position;
    }

    public void setPosition(FormationPosition position) {
        this.position = position;
    }

    public BattleHero getHero() {
        return hero;
    }

    public void setHero(BattleHero hero) {
        this.hero = hero;
    }

    public BattleTeamType getBattleTeamType() {
        return battleTeamType;
    }

    public void setBattleTeamType(BattleTeamType battleTeamType) {
        this.battleTeamType = battleTeamType;
    }

    public BattlePositionedHero(BattleTeamType battleTeamType) {
        this.battleTeamType = battleTeamType;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        if (energy > IdleConstants.MAX_ENERGY) {
            this.energy = IdleConstants.MAX_ENERGY;
        } else if (energy < IdleConstants.MIN_ENERGY) {
            this.energy = IdleConstants.MIN_ENERGY;
        } else {
            this.energy = energy;
        }
    }

    public BattlePositionedHero() {
    }

    public BattlePositionedHero(FormationPosition position, BattleHero hero) {
        this(null, position, hero);
    }

    public BattlePositionedHero(BattleTeamType battleTeamType, FormationPosition position, BattleHero hero) {
        this.position = position;
        this.hero = hero;
        this.battleTeamType = battleTeamType;
    }

    @Override
    public String toString() {
        return "BPH{" + "type=" + battleTeamType + ",p=" + position + ",hero=" + hero + ",energy=" + energy + '}';
    }

    public BattlePositionedHero duplicate() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (BattlePositionedHero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

}
