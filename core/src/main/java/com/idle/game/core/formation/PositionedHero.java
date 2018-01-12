package com.idle.game.core.formation;

import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.hero.BattleHero;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class PositionedHero implements Serializable {

    private FormationPosition battlePosition;
    private BattleHero hero;

    public PositionedHero() {
    }

    public PositionedHero(FormationPosition position, BattleHero hero) {
        this.battlePosition = position;
        this.hero = hero;
    }

    public FormationPosition getBattlePosition() {
        return battlePosition;
    }

    public void setBattlePosition(FormationPosition position) {
        this.battlePosition = position;
    }

    public BattleHero getHero() {
        return hero;
    }

    public void setHero(BattleHero hero) {
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
