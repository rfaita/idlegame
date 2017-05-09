package com.idle.game.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class BattlePositionedHero extends PositionedHero {

    private static final Logger LOG = Logger.getLogger(BattlePositionedHero.class.getName());

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

    public BattlePositionedHero(BattleTeamType battleTeamType, BattlePositionType position, Hero hero) {
        super(position, hero);
        this.battleTeamType = battleTeamType;
    }

    @Override
    public String toString() {
        return "BPH{" + "type=" + battleTeamType + ",bp=" + getBattlePosition() + ",hero=" + getHero() + '}';
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
