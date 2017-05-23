package com.idle.game.core.constant;

import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public interface IdleConstants {

    Logger LOG = Logger.getLogger(IdleConstants.LOG_NAME);

    Integer MAX_SIZE_FRONT_LINE = 3;
    Integer MAX_SIZE_BACK_LINE = 3;
    Integer TURN_LIMIT = 100;

    String LOG_DELIMITER = "---------------------------------------------------------------";
    String LOG_NAME = "com.idle.game.core";

}
