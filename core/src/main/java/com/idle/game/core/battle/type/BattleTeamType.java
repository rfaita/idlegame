package com.idle.game.core.battle.type;

public enum BattleTeamType {

    ATTACK_TEAM, DEFENSE_TEAM;

    public BattleTeamType getOpposite() {
        return (this.equals(ATTACK_TEAM) ? DEFENSE_TEAM : ATTACK_TEAM);
    }

}
