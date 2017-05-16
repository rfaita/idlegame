package com.idle.game.core.util;

import java.util.Random;

/**
 *
 * @author rafael
 */
public class DiceUtil {

    public static Integer random(Integer maxValue) {
        return new Random().nextInt(maxValue);
    }

}
