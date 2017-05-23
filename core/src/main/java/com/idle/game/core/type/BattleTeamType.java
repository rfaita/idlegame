package com.idle.game.core.type;

/**
 *
 * @author rafael
 */
public enum BattleTeamType {

    ATTACK, DEFENSE;

    public BattleTeamType getOpposite() {
        return (this.equals(ATTACK) ? DEFENSE : ATTACK);
    }

}
