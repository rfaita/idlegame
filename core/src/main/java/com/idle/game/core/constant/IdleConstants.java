package com.idle.game.core.constant;

import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public interface IdleConstants {

    final Logger LOG = Logger.getLogger(IdleConstants.LOG_NAME);

    final Integer HERO_MAX_LEVEL = 50;

    final Integer MAX_SIZE_FRONT_LINE = 3;
    final Integer MAX_SIZE_BACK_LINE = 3;
    final Integer MAX_SIZE_FORMATION = MAX_SIZE_FRONT_LINE + MAX_SIZE_BACK_LINE;
    final Integer TURN_LIMIT = 100;

    final String LOG_DELIMITER = "---------------------------------------------------------------";
    final String LOG_NAME = "com.idle.game.core";
    final String LOG_NAME_SERVER = "com.idle.game.server";

}
