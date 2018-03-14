package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.helper.HeroHelper;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class BattleHeroService {

    @Autowired
    private HeroHelper heroHelper;

    @Autowired
    private HeroTypeHelper heroTypeHelper;

    @Cacheable(value = BATTLE_HERO_FIND_BY_ID, key = "'" + BATTLE_HERO_FIND_BY_ID + "' + #idHero")
    public BattleHero getBattleHero(String idHero) {

        Hero hero = heroHelper.getHeroById(idHero);

        if (hero != null) {

            HeroType heroType = heroTypeHelper.getHeroTypeById(hero.getHeroTypeId());

            if (heroType != null) {

                BattleHero ret = new BattleHero(hero.getId(), heroType.toBattleHeroType(), hero.getLevel());

                Double levelUpRatio = 1d * ((hero.getLevel() - 1d) / (heroType.getMaxLevel() - 1d));

                if (levelUpRatio > 0) {
                    ret.getDefenses().forEach((def) -> {
                        ret.setDefense(def.getType(), hero.getBaseDefense(def.getType()).getValue()
                                + (int) ((hero.getMaxLevelDefense(def.getType()).getValue() - hero.getBaseDefense(def.getType()).getValue()) * levelUpRatio));
                    });
                    ret.setCritChance(hero.getBaseCritChance() + (int) ((hero.getMaxLevelCritChance() - hero.getBaseCritChance()) * levelUpRatio));
                    ret.setCritDamage(hero.getBaseCritDamage() + (int) ((hero.getMaxLevelCritDamage() - hero.getBaseCritDamage()) * levelUpRatio));
                    ret.setDmg(hero.getBaseDmg() + (int) ((hero.getMaxLevelDmg() - hero.getBaseDmg()) * levelUpRatio));
                    ret.setAp(hero.getBaseAp() + (int) ((hero.getMaxLevelAp() - hero.getBaseAp()) * levelUpRatio));
                    ret.setDodgeChance(hero.getBaseDodgeChance() + (int) ((hero.getMaxLevelDodgeChance() - hero.getBaseDodgeChance()) * levelUpRatio));
                    ret.setHp(hero.getBaseHp() + (int) ((hero.getMaxLevelHp() - hero.getBaseHp()) * levelUpRatio));
                    ret.setSpeed(hero.getBaseSpeed() + (int) ((hero.getMaxLevelSpeed() - hero.getBaseSpeed()) * levelUpRatio));
                } else {
                    ret.getDefenses().forEach((def) -> {
                        ret.setDefense(def.getType(), hero.getBaseDefense(def.getType()).getValue());
                    });
                    ret.setCritChance(hero.getBaseCritChance());
                    ret.setCritDamage(hero.getBaseCritDamage());
                    ret.setDmg(hero.getBaseDmg());
                    ret.setAp(hero.getBaseAp());
                    ret.setDodgeChance(hero.getBaseDodgeChance());
                    ret.setHp(hero.getBaseHp());
                    ret.setSpeed(hero.getBaseSpeed());
                }
                ret.setLevel(hero.getLevel());

                return ret;

            } else {
                throw new ValidationException("hero.type.not.found");
            }

        } else {
            throw new ValidationException("hero.not.found");
        }
    }

}
