package com.idle.game.server.service;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.type.HeroQuality;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.server.repository.HeroRepository;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class HeroService {

    @Autowired
    private HeroTypeHelper heroTypeHelper;
    
    @Autowired
    private HeroRepository heroRepository;

    @Cacheable(cacheNames = {"heroFindById"}, key = "#id")
    public Hero findById(String id) {

        return heroRepository.findById(id);
    }

    public List<Hero> findByAllByPlayer(String idPlayer) {

        return heroRepository.findAllByPlayer(idPlayer);
    }

    @CachePut(cacheNames = {"heroFindById"}, key = "#id")
    public Hero levelUp(String id, String player) {

        Hero h = heroRepository.findById(id);
        
        validateLevelUp(h, player);

        h.setLevel(h.getLevel() + 1);

        heroRepository.save(h);
        return h;
    }

    private void validateLevelUp(Hero h, String player) {
        if (h == null) {
            throw new ValidationException("hero.not.found");
        }
        if (!h.getPlayer().equalsIgnoreCase(player)) {
            throw new ValidationException("player.is.not.owner.of.this.hero");
        }
        if (h.getLevel() + 1 > IdleConstants.HERO_MAX_LEVEL) {
            throw new ValidationException("hero.max.level.reached");
        }
    }

    public Hero rollHero(String player) {

        List<HeroType> heroTypes = heroTypeHelper.getHeroTypeByHeroTypeQuality(DiceUtil.randomHeroTypeQuality());

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
