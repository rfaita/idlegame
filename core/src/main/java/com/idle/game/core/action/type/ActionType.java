package com.idle.game.core.action.type;

/**
 *
 * @author rafael
 */
public enum ActionType {

    BATTLE_START, BATTLE_END,
    TURN_START, TURN_END,
    PREPARE_TO_BATTLE_START, PREPARE_TO_BATTLE_END,
    PREPARE_TO_TURN_START, PREPARE_TO_TURN_END,
    COMPUTE_BUFF_START, COMPUTE_BUFF_END,
    ACTION_START, ACTION_END,
    NONE, DMG, HEAL,
    REVIVE,
    BUFF_START, BUFF_REFRESH, BUFF_COMPUTE, BUFF_DONE

}
