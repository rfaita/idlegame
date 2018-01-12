package com.idle.game.core.util;

import com.idle.game.core.type.HeroQuality;
import com.idle.game.core.type.HeroTypeQuality;
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

    public static HeroQuality randomHeroQuality() {

        Integer heroTypeQuality = DiceUtil.random(1000);

        if (heroTypeQuality <= 500) {
            return HeroQuality.POOR;
        } else if (heroTypeQuality <= 800) {
            return HeroQuality.AVERAGE;
        } else if (heroTypeQuality <= 990) {
            return HeroQuality.BEST;
        } else if (heroTypeQuality <= 999) {
            return HeroQuality.SHINE;
        } else {
            return HeroQuality.UNIQUE;
        }

    }

    public static HeroTypeQuality randomHeroTypeQuality() {

        Integer heroTypeQuality = DiceUtil.random(100);

        if (heroTypeQuality <= 60) {
            return HeroTypeQuality.POOR;
        } else if (heroTypeQuality <= 90) {
            return HeroTypeQuality.AVERAGE;
        } else {
            return HeroTypeQuality.BEST;
        }

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
