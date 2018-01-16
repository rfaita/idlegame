package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.helper.HeroHelper;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.HeroType;
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

            HeroType heroType = heroTypeHelper.getHeroTypeById(hero.getHeroType());

            if (heroType != null) {

                BattleHero ret = new BattleHero(hero.getId(), heroType.toBattleHeroType(), hero.getLevel());

                Double levelUpRatio = 1d * (hero.getLevel() - 1) / (heroType.getMaxLevel() - 1);

                if (levelUpRatio > 0) {
                    ret.setArmor(hero.getBaseArmor() + (int) (hero.getMaxLevelUpIncArmor() * levelUpRatio));
                    ret.setCritChance(hero.getBaseCritChance() + (int) (hero.getMaxLevelUpIncCritChance() * levelUpRatio));
                    ret.setCritDamage(hero.getBaseCritDamage() + (int) (hero.getMaxLevelUpIncCritDamage() * levelUpRatio));
                    ret.setDmg(hero.getBaseDmg() + (int) (hero.getMaxLevelUpIncDmg() * levelUpRatio));
                    ret.setDodgeChance(hero.getBaseDodgeChance() + (int) (hero.getMaxLevelUpIncDodgeChance() * levelUpRatio));
                    ret.setHp(hero.getBaseHp() + (int) (hero.getMaxLevelUpIncHp() * levelUpRatio));
                    ret.setLuck(hero.getBaseLuck() + (int) (hero.getMaxLevelUpIncLuck() * levelUpRatio));
                    ret.setMagicResist(hero.getBaseMagicResist() + (int) (hero.getMaxLevelUpIncMagicResist() * levelUpRatio));
                    ret.setSpeed(hero.getBaseSpeed() + (int) (hero.getMaxLevelUpIncSpeed() * levelUpRatio));
                } else {
                    ret.setArmor(hero.getBaseArmor());
                    ret.setCritChance(hero.getBaseCritChance());
                    ret.setCritDamage(hero.getBaseCritDamage());
                    ret.setDmg(hero.getBaseDmg());
                    ret.setDodgeChance(hero.getBaseDodgeChance());
                    ret.setHp(hero.getBaseHp());
                    ret.setLuck(hero.getBaseLuck());
                    ret.setMagicResist(hero.getBaseMagicResist());
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
