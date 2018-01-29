package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_FIND_BY_ID;
import com.idle.game.core.constant.IdleConstants;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.model.mongo.Player;
import com.idle.game.server.repository.HeroRepository;
import java.util.List;
import java.util.logging.Level;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Autowired
    private PlayerHelper playerHelper;

    @Cacheable(value = HERO_FIND_BY_ID, key = "'" + HERO_FIND_BY_ID + "' + #id")
    public Hero findById(String id) {

        return heroRepository.findById(id);
    }

    public List<Hero> findAllByPlayer(String idPlayer) {

        return heroRepository.findAllByPlayer(idPlayer);
    }

    public List<Hero> findAllByPlayerAndQuality(String idPlayer, HeroQuality quality) {

        return heroRepository.findAllByPlayerAndQuality(idPlayer, quality);
    }

    @Caching(put
            = @CachePut(value = HERO_FIND_BY_ID, key = "'" + HERO_FIND_BY_ID + "' + #id"),
            evict
            = @CacheEvict(value = BATTLE_HERO_FIND_BY_ID, key = "'" + BATTLE_HERO_FIND_BY_ID + "' + #id")
    )

    public Hero levelUp(String id, String user) {

        Player player = playerHelper.getPlayerByLinkedUser(user);

        if (player != null) {
            Hero h = heroRepository.findById(id);

            validateLevelUp(h, player.getId());

            h.setLevel(h.getLevel() + 1);

            heroRepository.save(h);
            return h;
        } else {
            throw new ValidationException("player.not.found");
        }
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
        return rollHero(player, null);
    }

    public Hero rollHero(String player, String customHeroType) {

        HeroType heroType;

        if (customHeroType == null) {
            HeroTypeFaction faction = DiceUtil.randomHeroTypeFaction();
            HeroTypeQuality quality = DiceUtil.randomHeroTypeQuality();

            List<HeroType> heroTypes = heroTypeHelper.getHeroTypeByFactionAndQuality(
                    faction, quality
            );

            if (heroTypes == null || heroTypes.isEmpty()) {
                LOG.log(Level.SEVERE, "Hero Type not found for, FACTION: {0}, QUALITY: {1}", new Object[]{faction, quality});
                throw new ValidationException("hero.type.not.found");
            }

            heroType = heroTypes.get(DiceUtil.random(heroTypes.size() - 1));

        } else {
            heroType = heroTypeHelper.getHeroTypeById(customHeroType);
        }

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
        hero.setQuality(heroQuality);
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

        ret.setMaxLevelArmor(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelArmor(), ht.getMaxMaxLevelArmor()));
        ret.setMaxLevelCritChance(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelCritChance(), ht.getMaxMaxLevelCritChance()));
        ret.setMaxLevelCritDamage(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelCritDamage(), ht.getMaxMaxLevelCritDamage()));
        ret.setMaxLevelDmg(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelDmg(), ht.getMaxMaxLevelDmg()));
        ret.setMaxLevelDodgeChance(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelDodgeChance(), ht.getMaxMaxLevelDodgeChance()));
        ret.setMaxLevelHp(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelHp(), ht.getMaxMaxLevelHp()));
        ret.setMaxLevelLuck(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelLuck(), ht.getMaxMaxLevelLuck()));
        ret.setMaxLevelMagicResist(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelMagicResist(), ht.getMaxMaxLevelMagicResist()));
        ret.setMaxLevelSpeed(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelSpeed(), ht.getMaxMaxLevelSpeed()));

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

        ret.setMaxLevelArmor((int) (heroType.getMaxMaxLevelArmor()));
        ret.setMaxLevelCritChance((int) (heroType.getMaxMaxLevelCritChance()));
        ret.setMaxLevelCritDamage((int) (heroType.getMaxMaxLevelCritDamage()));
        ret.setMaxLevelDmg((int) (heroType.getMaxMaxLevelDmg()));
        ret.setMaxLevelDodgeChance((int) (heroType.getMaxMaxLevelDodgeChance()));
        ret.setMaxLevelHp((int) (heroType.getMaxMaxLevelHp()));
        ret.setMaxLevelLuck((int) (heroType.getMaxMaxLevelLuck()));
        ret.setMaxLevelMagicResist((int) (heroType.getMaxMaxLevelMagicResist()));
        ret.setMaxLevelSpeed((int) (heroType.getMaxMaxLevelSpeed()));

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

        ret.setMaxLevelArmor((int) (heroType.getMaxMaxLevelArmor() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelCritChance((int) (heroType.getMaxMaxLevelCritChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelCritDamage((int) (heroType.getMaxMaxLevelCritDamage() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelDmg((int) (heroType.getMaxMaxLevelDmg() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelDodgeChance((int) (heroType.getMaxMaxLevelDodgeChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelHp((int) (heroType.getMaxMaxLevelHp() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelLuck((int) (heroType.getMaxMaxLevelLuck() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelMagicResist((int) (heroType.getMaxMaxLevelMagicResist() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelSpeed((int) (heroType.getMaxMaxLevelSpeed() * (1d + (DiceUtil.random(10) / 100d))));

        return ret;
    }

    public Hero calcPassives(Hero hero, HeroType heroType) {
        LOG.log(Level.INFO, "[passive][heroi] {0}", hero);
        if (heroType.getPassives() != null) {
            for (Passive p : heroType.getPassives()) {
                LOG.log(Level.INFO, "[passive][type] {0}", p.getPassiveType());
                switch (p.getPassiveType()) {
                    case INCREASE_ATTRIBUTE:
                        return recalcAttribute(hero, p.getAttributeTypeGain(), p.getRatioPercentage(), 1);
                    case TRADE_ATTRIBUTE:
                        return calcTradeAttribute(hero, p.getAttributeTypeLost(), p.getAttributeTypeGain(), p.getRatioPercentage());
                }
                LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER);

            }
        }
        return hero;
    }

    private Hero recalcAttribute(Hero hero, AttributeType d, Integer ratioPercentage, Integer signal) {
        Double perc = ratioPercentage / 100d * signal;
        LOG.log(Level.INFO, "[recalcAttribute][perc] {0}", perc);
        if (perc > 0) {
            switch (d) {
                case LUCK:
                    hero.setBaseLuck(hero.getBaseLuck() + (int) (hero.getBaseLuck() * perc));
                    hero.setMaxLevelLuck(hero.getMaxLevelLuck() + (int) (hero.getMaxLevelLuck() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][luck][base] {0}", hero.getBaseLuck());
                    LOG.log(Level.INFO, "[recalcAttribute][luck][max] {0}", hero.getMaxLevelLuck());
                    break;
                case SPEED:
                    hero.setBaseSpeed(hero.getBaseSpeed() + (int) (hero.getBaseSpeed() * perc));
                    hero.setMaxLevelSpeed(hero.getMaxLevelSpeed() + (int) (hero.getMaxLevelSpeed() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][speed][base] {0}", hero.getBaseSpeed());
                    LOG.log(Level.INFO, "[recalcAttribute][speed][max] {0}", hero.getMaxLevelSpeed());
                    break;
                case DODGE:
                    hero.setBaseDodgeChance(hero.getBaseDodgeChance() + (int) (hero.getBaseDodgeChance() * perc));
                    hero.setMaxLevelDodgeChance(hero.getMaxLevelDodgeChance() + (int) (hero.getMaxLevelDodgeChance() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][dodgeChance][base] {0}", hero.getBaseDodgeChance());
                    LOG.log(Level.INFO, "[recalcAttribute][dodgeChance][max] {0}", hero.getMaxLevelDodgeChance());
                    break;
                case CRIT_DMG:
                    hero.setBaseCritDamage(hero.getBaseCritDamage() + (int) (hero.getBaseCritDamage() * perc));
                    hero.setMaxLevelCritDamage(hero.getMaxLevelCritDamage() + (int) (hero.getMaxLevelCritDamage() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][critDmg][base] {0}", hero.getBaseCritDamage());
                    LOG.log(Level.INFO, "[recalcAttribute][critDmg][max] {0}", hero.getMaxLevelCritDamage());
                    break;
                case CRIT_CHANCE:
                    hero.setBaseCritChance(hero.getBaseCritChance() + (int) (hero.getBaseCritChance() * perc));
                    hero.setMaxLevelCritChance(hero.getMaxLevelCritChance() + (int) (hero.getMaxLevelCritChance() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][critChance][base] {0}", hero.getBaseCritChance());
                    LOG.log(Level.INFO, "[recalcAttribute][critChance][max] {0}", hero.getMaxLevelCritChance());
                    break;
                case DMG:
                    hero.setBaseDmg(hero.getBaseDmg() + (int) (hero.getBaseDmg() * perc));
                    hero.setMaxLevelDmg(hero.getMaxLevelDmg() + (int) (hero.getMaxLevelDmg() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][dmg][base] {0}", hero.getBaseDmg());
                    LOG.log(Level.INFO, "[recalcAttribute][dmg][max] {0}", hero.getMaxLevelDmg());
                    break;
                case ARMOR:
                    hero.setBaseArmor(hero.getBaseArmor() + (int) (hero.getBaseArmor() * perc));
                    hero.setMaxLevelArmor(hero.getMaxLevelArmor() + (int) (hero.getMaxLevelArmor() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][armor][base] {0}", hero.getBaseArmor());
                    LOG.log(Level.INFO, "[recalcAttribute][armor][max] {0}", hero.getMaxLevelArmor());
                    break;
                case MAGIC_RESIST:
                    hero.setBaseMagicResist(hero.getBaseMagicResist() + (int) (hero.getBaseMagicResist() * perc));
                    hero.setMaxLevelMagicResist(hero.getMaxLevelMagicResist() + (int) (hero.getMaxLevelMagicResist() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][magicResist][base] {0}", hero.getBaseMagicResist());
                    LOG.log(Level.INFO, "[recalcAttribute][magicResist][max] {0}", hero.getMaxLevelMagicResist());
                    break;
                case DEFENSE:
                    hero.setBaseArmor(hero.getBaseArmor() + (int) (hero.getBaseArmor() * perc));
                    hero.setMaxLevelArmor(hero.getMaxLevelArmor() + (int) (hero.getMaxLevelArmor() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][armor][base] {0}", hero.getBaseArmor());
                    LOG.log(Level.INFO, "[recalcAttribute][armor][max] {0}", hero.getMaxLevelArmor());
                    hero.setBaseMagicResist(hero.getBaseMagicResist() + (int) (hero.getBaseMagicResist() * perc));
                    hero.setMaxLevelMagicResist(hero.getMaxLevelMagicResist() + (int) (hero.getMaxLevelMagicResist() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][magicResist][base] {0}", hero.getBaseMagicResist());
                    LOG.log(Level.INFO, "[recalcAttribute][magicResist][max] {0}", hero.getMaxLevelMagicResist());
                    break;

            }
        }

        return hero;

    }

    private Hero calcTradeAttribute(Hero hero, AttributeType o, AttributeType d, Integer ratioPercentage) {
//        Double perc = this.getMissingAttributePercentage(o) / 100d * ratioPercentage / 100d;
//        LOG.log(Level.INFO, "[passive][perc] {0}", perc);
////        if (perc > 0) {
//            switch (d) {
//                case LUCK:
//                    this.setCurrLuck(this.getCurrLuck() + (int) (this.getCurrLuck() * perc));
//                    LOG.log(Level.INFO, "[passive][luck] {0}", this.getCurrLuck());
//                    break;
//                case SPEED:
//                    this.setCurrSpeed(this.getCurrSpeed() + (int) (this.getCurrSpeed() * perc));
//                    LOG.log(Level.INFO, "[passive][speed] {0}", this.getCurrSpeed());
//                    break;
//                case DODGE:
//                    this.setCurrDodgeChance(this.getCurrDodgeChance() + (int) (this.getCurrDodgeChance() * perc));
//                    LOG.log(Level.INFO, "[passive][dodgeChance] {0}", this.getCurrDodgeChance());
//                    break;
//                case CRIT_DMG:
//                    this.setCurrCritDamage(this.getCurrCritDamage() + (int) (this.getCurrCritDamage() * perc));
//                    LOG.log(Level.INFO, "[passive][critDmg] {0}", this.getCurrCritDamage());
//                    break;
//                case CRIT_CHANCE:
//                    this.setCurrCritChance(this.getCurrCritChance() + (int) (this.getCurrCritChance() * perc));
//                    LOG.log(Level.INFO, "[passive][critChance] {0}", this.getCurrCritChance());
//                    break;
//                case DMG:
//                    this.setCurrDmg(this.getCurrDmg() + (int) (this.getCurrDmg() * perc));
//                    LOG.log(Level.INFO, "[passive][dmg] {0}", this.getCurrDmg());
//                    break;
//                case ARMOR:
//                    this.setCurrArmor(this.getCurrArmor() + (int) (this.getCurrArmor() * perc));
//                    LOG.log(Level.INFO, "[passive][armor] {0}", this.getCurrArmor());
//                    break;
//                case MAGIC_RESIST:
//                    this.setCurrMagicResist(this.getCurrMagicResist() + (int) (this.getCurrMagicResist() * perc));
//                    LOG.log(Level.INFO, "[passive][magicResist] {0}", this.getCurrMagicResist());
//                    break;
//                case DEFENSE:
//                    this.setCurrArmor(this.getCurrArmor() + (int) (this.getCurrArmor() * perc));
//                    this.setCurrMagicResist(this.getCurrMagicResist() + (int) (this.getCurrMagicResist() * perc));
//                    LOG.log(Level.INFO, "[passive][armor] {0}", this.getCurrArmor());
//                    LOG.log(Level.INFO, "[passive][magicResist] {0}", this.getCurrMagicResist());
//                    break;
//
//            }
//        }

        return hero;

    }

//    private Integer getMissingAttributePercentage(AttributeType at) {
//        switch (at) {
//            case HP:
//                return (int) ((this.getHp() - this.getCurrHp()) * 1d / this.getHp() * 100);
//            default:
//                return 0;
//        }
//
//    }
}
