package com.idle.game.core;

import com.idle.game.core.type.BattlePositionType;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class PositionedHero implements Serializable {

    private BattlePositionType battlePosition;
    private Hero hero;

    public PositionedHero() {
    }

    public PositionedHero(BattlePositionType position, Hero hero) {
        this.battlePosition = position;
        this.hero = hero;
    }

    public BattlePositionType getBattlePosition() {
        return battlePosition;
    }

    public void setBattlePosition(BattlePositionType position) {
        this.battlePosition = position;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.battlePosition);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PositionedHero other = (PositionedHero) obj;
        if (this.battlePosition != other.battlePosition) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PositionedHero{" + "battlePosition=" + battlePosition + ", hero=" + hero + '}';
    }

}
