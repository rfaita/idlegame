package com.idle.game.core.battle;

import com.idle.game.core.formation.PositionedHero;
import com.idle.game.core.hero.BattleHero;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.formation.type.FormationPosition;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

/**
 *
 * @author rafael
 */
public class BattlePositionedHero extends PositionedHero {

    private Integer energy = 0;
    private BattleTeamType battleTeamType;

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
        this.energy = energy;
    }

    public BattlePositionedHero(BattleTeamType battleTeamType, FormationPosition position, BattleHero hero) {
        super(position, hero);
        this.battleTeamType = battleTeamType;
    }

    @Override
    public String toString() {
        return "BPH{" + "type=" + battleTeamType + ",bp=" + getBattlePosition() + ",hero=" + getHero() + ",energy=" + getEnergy() + '}';
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
