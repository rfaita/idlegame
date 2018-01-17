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

    public static HeroTypeFaction randomHeroTypeFaction() {

        Integer heroTypeFaction = DiceUtil.random(100);

        if (heroTypeFaction <= 20) {
            return HeroTypeFaction.CHAOS;
        } else if (heroTypeFaction <= 40) {
            return HeroTypeFaction.FORTIFIED;
        } else if (heroTypeFaction <= 60) {
            return HeroTypeFaction.SAVAGE;
        } else if (heroTypeFaction <= 80) {
            return HeroTypeFaction.SHADOW;
        } else if (heroTypeFaction <= 90) {
            return HeroTypeFaction.DARK;
        } else {
            return HeroTypeFaction.LIGHT;
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
