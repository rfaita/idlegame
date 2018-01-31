package com.idle.game.core.util;

import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author rafael
 */
public class DiceUtil {

    public static Integer random(Integer maxValue) {
        return random(0, maxValue);
    }

    public static Integer random(Integer minValue, Integer maxValue) {
        return ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
    }

    public static Double randomPercentage() {
        return random(0.0, 100.0);
    }

    public static Double random(Double maxValue) {
        return random(0.0, maxValue);
    }

    public static Double random(Double minValue, Double maxValue) {
        return ThreadLocalRandom.current().nextDouble(minValue, maxValue + 0.0000001);
    }

    public static Integer randomAttribute(HeroQuality heroQuality, Integer min, Integer max) {
        switch (heroQuality) {
            case POOR:
                return randomPoorAttribute(min, max);
            case AVERAGE:
                return randomAverageAttribute(min, max);
            default:
            case BEST:
                return randomBestAttribute(min, max);
        }
    }

    public static Integer randomPoorAttribute(Integer min, Integer max) {
        return DiceUtil.random(min, (int) (max - ((max - min) * 2 / 3f)));
    }

    public static Integer randomAverageAttribute(Integer min, Integer max) {
        return DiceUtil.random((int) (min + ((max - min) / 3f)), (int) (max - ((max - min) / 3f)));
    }

    public static Integer randomBestAttribute(Integer min, Integer max) {
        return DiceUtil.random((int) (min + ((max - min) * 2 / 3f)), max);
    }

}
