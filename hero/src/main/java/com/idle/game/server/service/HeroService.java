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
import com.idle.game.helper.LootRollHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.shop.LootRoll;
import static com.idle.game.model.mongo.shop.LootRollType.HERO;
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

    @Autowired
    private LootRollHelper lootRollHelper;

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
            = @CachePut(value = HERO_FIND_BY_ID, key = "'" + HERO_FIND_BY_ID + "' + #hero.id"),
            evict
            = @CacheEvict(value = BATTLE_HERO_FIND_BY_ID, key = "'" + BATTLE_HERO_FIND_BY_ID + "' + #hero.id")
    )
    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }

    @Caching(
            evict
            = {
                @CacheEvict(value = BATTLE_HERO_FIND_BY_ID, key = "'" + BATTLE_HERO_FIND_BY_ID + "' + #id")
                ,
                @CacheEvict(value = HERO_FIND_BY_ID, key = "'" + HERO_FIND_BY_ID + "' + #id")
            }
    )
    public void delete(String id) {
        heroRepository.delete(id);
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

    public Hero rollHero(String user, String lootRollId) {

        Player player = playerHelper.getPlayerByLinkedUser(user);

        if (player != null) {
            return rollHero(player.getId(), null, null, lootRollId);
        } else {
            throw new ValidationException("player.not.found");
        }

    }

    public Hero customRollHero(String player, String customHeroType, String customHeroQuality) {
        return rollHero(player, customHeroType, customHeroQuality, null);
    }

    private Hero rollHero(String player, String customHeroType, String customHeroQuality, String lootRollId) {

        HeroType heroType;
        HeroQuality heroQuality;

        if (customHeroType == null && customHeroQuality == null) {

            LootRoll lootRoll = lootRollHelper.buyLootRoll(lootRollId);

            if (lootRoll != null) {

                if (lootRoll.getType().equals(HERO)) {

                    HeroTypeFaction faction = HeroTypeFaction.valueOf(lootRoll.roll(HeroTypeFaction.class));
                    HeroTypeQuality quality = HeroTypeQuality.valueOf(lootRoll.roll(HeroTypeQuality.class));

                    List<HeroType> heroTypes = heroTypeHelper.getHeroTypeByFactionAndQuality(
                            faction, quality
                    );

                    if (heroTypes == null || heroTypes.isEmpty()) {
                        LOG.log(Level.SEVERE, "Hero Type not found for, FACTION: {0}, QUALITY: {1}", new Object[]{faction, quality});
                        throw new ValidationException("hero.type.not.found");
                    }

                    heroType = heroTypes.get(DiceUtil.random(heroTypes.size() - 1));
                    heroQuality = HeroQuality.valueOf(lootRoll.roll(HeroQuality.class));

                } else {
                    throw new ValidationException("loot.roll.type.must.be.hero");
                }
            } else {
                throw new ValidationException("loot.roll.not.found");
            }

        } else {
            heroType = heroTypeHelper.getHeroTypeById(customHeroType);
            heroQuality = HeroQuality.valueOf(customHeroQuality);
        }

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

}
