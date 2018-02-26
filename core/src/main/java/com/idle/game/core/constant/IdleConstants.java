package com.idle.game.core.constant;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import static com.idle.game.core.action.type.ActionType.DMG;
import static com.idle.game.core.type.TargetType.FIRST_ONE;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public interface IdleConstants {

    final Logger LOG = Logger.getLogger(IdleConstants.LOG_NAME);

    final Integer MAX_ENERGY = 100;
    final Integer MIN_ENERGY = 0;
    final Integer ENERGY_GAIN_ON_BATTLE_START = MAX_ENERGY / 2;
    final Integer ENERGY_GAIN_DOING_ATTACK = MAX_ENERGY / 2;
    final Integer ENERGY_GAIN_ON_ATTACK = MAX_ENERGY / 10;

    final Integer HERO_MAX_LEVEL = 250;

    final Integer MAX_SIZE_FRONT_LINE = 2;
    final Integer MAX_SIZE_BACK_LINE = 4;
    final Integer MAX_SIZE_FORMATION = MAX_SIZE_FRONT_LINE + MAX_SIZE_BACK_LINE;
    final Integer TURN_LIMIT = 25;

    final String LOG_DELIMITER_BATTLE = "###############################################################";
    //final String LOG_DELIMITER = "---------------------------------------------------------------";
    final String LOG_NAME = "com.idle.game.core";
    final String LOG_NAME_SERVER = "com.idle.game.server";

    final Action DEFAULT_ACTION = new Action(new ActionEffect(DMG, FIRST_ONE, 100));

}
