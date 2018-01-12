package com.idle.game.server.service;

import com.idle.game.core.hero.BattleHero;
import com.idle.game.core.type.HeroQuality;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.model.mongo.Hero;
import com.idle.game.server.repository.HeroRepository;
import com.idle.game.server.repository.HeroTypeRepository;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private HeroTypeRepository heroTypeRepository;

    public BattleHero getBattleHero(String idHero) {

        Hero hero = heroRepository.findById(idHero);

        if (hero != null) {

            HeroType heroType = heroTypeRepository.findById(hero.getHeroType());

            BattleHero ret = new BattleHero(hero.getId(), heroType, hero.getLevel());

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

            return ret;

        } else {
            throw new ValidationException("hero.not.found");
        }
    }

    public Hero rollHero(String player) {

        List<HeroType> heroTypes = heroTypeRepository.findAllByHeroTypeQuality(DiceUtil.randomHeroTypeQuality());

        if (heroTypes == null || heroTypes.isEmpty()) {
            throw new ValidationException("hero.type.not.found");
        }

        HeroType heroType = heroTypes.get(0);

        HeroQuality heroQuality = DiceUtil.randomHeroQuality();

        Hero hero;

        switch (heroQuality) {

            case SHINE:
                hero = rollShineHero(heroType);
                break;
            case UNIQUE:
                hero = rollUniqueHero(heroType);
                break;
            case POOR:
            case AVERAGE:
            case BEST:
            default:
                hero = rollHero(heroQuality, heroType);
        }

        hero.setPlayer(player);
        hero.setHeroQuality(heroQuality);
        hero.setLevel(1);
        hero.setHeroType(heroType.getId());

        return heroRepository.save(hero);

    }

    public Hero rollHero(HeroQuality hq, HeroType ht) {

        assert !hq.equals(HeroQuality.SHINE) && !hq.equals(HeroQuality.UNIQUE) : "Hero quality must be poor, average or best here, BUG";

        Hero ret = new Hero();

        ret.setBaseArmor(DiceUtil.randomAttribute(hq, ht.getMinBaseArmor(), ht.getMaxBaseArmor()));
        ret.setBaseCritChance(DiceUtil.randomAttribute(hq, ht.getMinBaseCritChance(), ht.getMaxBaseCritChance()));
        ret.setBaseCritDamage(DiceUtil.randomAttribute(hq, ht.getMinBaseCritDamage(), ht.getMaxBaseCritDamage()));
        ret.setBaseDmg(DiceUtil.randomAttribute(hq, ht.getMinBaseDmg(), ht.getMaxBaseDmg()));
        ret.setBaseDodgeChance(DiceUtil.randomAttribute(hq, ht.getMinBaseDodgeChance(), ht.getMaxBaseDodgeChance()));
        ret.setBaseHp(DiceUtil.randomAttribute(hq, ht.getMinBaseHp(), ht.getMaxBaseHp()));
        ret.setBaseLuck(DiceUtil.randomAttribute(hq, ht.getMinBaseLuck(), ht.getMaxBaseLuck()));
        ret.setBaseMagicResist(DiceUtil.randomAttribute(hq, ht.getMinBaseMagicResist(), ht.getMaxBaseMagicResist()));
        ret.setBaseSpeed(DiceUtil.randomAttribute(hq, ht.getMinBaseSpeed(), ht.getMaxBaseSpeed()));

        ret.setMaxLevelUpIncArmor(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncArmor(), ht.getMaxMaxLevelUpIncArmor()));
        ret.setMaxLevelUpIncCritChance(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncCritChance(), ht.getMaxMaxLevelUpIncCritChance()));
        ret.setMaxLevelUpIncCritDamage(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncCritDamage(), ht.getMaxMaxLevelUpIncCritDamage()));
        ret.setMaxLevelUpIncDmg(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncDmg(), ht.getMaxMaxLevelUpIncDmg()));
        ret.setMaxLevelUpIncDodgeChance(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncDodgeChance(), ht.getMaxMaxLevelUpIncDodgeChance()));
        ret.setMaxLevelUpIncHp(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncHp(), ht.getMaxMaxLevelUpIncHp()));
        ret.setMaxLevelUpIncLuck(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncLuck(), ht.getMaxMaxLevelUpIncLuck()));
        ret.setMaxLevelUpIncMagicResist(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncMagicResist(), ht.getMaxMaxLevelUpIncMagicResist()));
        ret.setMaxLevelUpIncSpeed(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelUpIncSpeed(), ht.getMaxMaxLevelUpIncSpeed()));

        return ret;
    }

    public Hero rollShineHero(HeroType heroType) {

        Hero ret = new Hero();

        ret.setBaseArmor((int) (heroType.getMaxBaseArmor()));
        ret.setBaseCritChance((int) (heroType.getMaxBaseCritChance()));
        ret.setBaseCritDamage((int) (heroType.getMaxBaseCritDamage()));
        ret.setBaseDmg((int) (heroType.getMaxBaseDmg()));
        ret.setBaseDodgeChance((int) (heroType.getMaxBaseDodgeChance()));
        ret.setBaseHp((int) (heroType.getMaxBaseHp()));
        ret.setBaseLuck((int) (heroType.getMaxBaseLuck()));
        ret.setBaseMagicResist((int) (heroType.getMaxBaseMagicResist()));
        ret.setBaseSpeed((int) (heroType.getMaxBaseSpeed()));

        ret.setMaxLevelUpIncArmor((int) (heroType.getMaxMaxLevelUpIncArmor()));
        ret.setMaxLevelUpIncCritChance((int) (heroType.getMaxMaxLevelUpIncCritChance()));
        ret.setMaxLevelUpIncCritDamage((int) (heroType.getMaxMaxLevelUpIncCritDamage()));
        ret.setMaxLevelUpIncDmg((int) (heroType.getMaxMaxLevelUpIncDmg()));
        ret.setMaxLevelUpIncDodgeChance((int) (heroType.getMaxMaxLevelUpIncDodgeChance()));
        ret.setMaxLevelUpIncHp((int) (heroType.getMaxMaxLevelUpIncHp()));
        ret.setMaxLevelUpIncLuck((int) (heroType.getMaxMaxLevelUpIncLuck()));
        ret.setMaxLevelUpIncMagicResist((int) (heroType.getMaxMaxLevelUpIncMagicResist()));
        ret.setMaxLevelUpIncSpeed((int) (heroType.getMaxMaxLevelUpIncSpeed()));

        return ret;
    }

    public Hero rollUniqueHero(HeroType heroType) {

        Hero ret = new Hero();

        ret.setBaseArmor((int) (heroType.getMaxBaseArmor() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseCritChance((int) (heroType.getMaxBaseCritChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseCritDamage((int) (heroType.getMaxBaseCritDamage() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseDmg((int) (heroType.getMaxBaseDmg() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseDodgeChance((int) (heroType.getMaxBaseDodgeChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseHp((int) (heroType.getMaxBaseHp() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseLuck((int) (heroType.getMaxBaseLuck() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseMagicResist((int) (heroType.getMaxBaseMagicResist() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseSpeed((int) (heroType.getMaxBaseSpeed() * (1d + (DiceUtil.random(10) / 100d))));

        ret.setMaxLevelUpIncArmor((int) (heroType.getMaxMaxLevelUpIncArmor() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncCritChance((int) (heroType.getMaxMaxLevelUpIncCritChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncCritDamage((int) (heroType.getMaxMaxLevelUpIncCritDamage() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncDmg((int) (heroType.getMaxMaxLevelUpIncDmg() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncDodgeChance((int) (heroType.getMaxMaxLevelUpIncDodgeChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncHp((int) (heroType.getMaxMaxLevelUpIncHp() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncLuck((int) (heroType.getMaxMaxLevelUpIncLuck() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncMagicResist((int) (heroType.getMaxMaxLevelUpIncMagicResist() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelUpIncSpeed((int) (heroType.getMaxMaxLevelUpIncSpeed() * (1d + (DiceUtil.random(10) / 100d))));

        return ret;
    }

}
