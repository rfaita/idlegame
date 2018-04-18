package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_FIND_BY_ID;
import static com.idle.game.core.constant.IdleConstants.LOG;
import static com.idle.game.model.shop.LootRollType.HERO;
import java.util.List;
import java.util.logging.Level;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.helper.client.hero.HeroTypeClient;
import com.idle.game.helper.client.resource.UserResourceClient;
import com.idle.game.helper.client.shop.LootRollClient;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.repository.HeroRepository;
import java.util.Optional;

/**
 *
 * @author rafael
 */
@Service
public class HeroService {

    @Autowired
    private HeroTypeClient heroTypeClient;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private UserResourceClient userResourceClient;

    @Autowired
    private LootRollClient lootRollClient;

    @Cacheable(value = HERO_FIND_BY_ID, key = "'" + HERO_FIND_BY_ID + "' + #id")
    public Hero findById(String id) {

        Optional<Hero> ret = heroRepository.findById(id);

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    public List<Hero> findAllByUserId(String userId) {

        return heroRepository.findAllByUserId(userId);
    }

    public List<Hero> findAllByUserIdAndQuality(String userId, HeroQuality quality) {

        return heroRepository.findAllByUserIdAndQuality(userId, quality);
    }

    public List<Hero> findAllByUserIdAndHeroTypeIdAndQuality(String userId, String heroTypeId, HeroQuality quality) {

        return heroRepository.findAllByUserIdAndHeroTypeIdAndQuality(userId, heroTypeId, quality);
    }

    public List<Hero> findAllByUserIdAndHeroTypeId(String userId, String heroTypeId) {

        return heroRepository.findAllByUserIdAndHeroTypeId(userId, heroTypeId);
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
        heroRepository.delete(new Hero(id));
    }

    @Caching(put
            = @CachePut(value = HERO_FIND_BY_ID, key = "'" + HERO_FIND_BY_ID + "' + #id"),
            evict
            = @CacheEvict(value = BATTLE_HERO_FIND_BY_ID, key = "'" + BATTLE_HERO_FIND_BY_ID + "' + #id")
    )
    public Hero levelUp(String id, String userId) {

        Hero h = findById(id);

        validateLevelUp(h, userId);

        h.setLevel(h.getLevel() + 1);

        heroRepository.save(h);
        return h;
    }

    private void validateLevelUp(Hero h, String userId) {
        if (h == null) {
            throw new ValidationException("hero.not.found");
        }
        if (!h.getUserId().equalsIgnoreCase(userId)) {
            throw new ValidationException("user.is.not.owner.of.this.hero");
        }
        if (h.getLevel() + 1 > IdleConstants.HERO_MAX_LEVEL) {
            throw new ValidationException("hero.max.level.reached");
        }
    }

    public Hero rollHero(String userId, String lootRollId) {
        return rollHero(userId, null, null, lootRollId);

    }

    public Hero customRollHero(String userId, String customHeroType, String customHeroQuality) {
        return rollHero(userId, customHeroType, customHeroQuality, null);
    }

    private Hero rollHero(String userId, String customHeroType, String customHeroQuality, String lootRollId) {

        HeroType heroType;
        HeroQuality heroQuality;

        if (customHeroType == null && customHeroQuality == null) {

            LootRoll lootRoll = lootRollClient.findById(lootRollId).getData();

            if (lootRoll != null) {

                if (lootRoll.getType().equals(HERO)) {

                    userResourceClient.useResources(lootRoll.getCost());

                    HeroTypeFaction faction = HeroTypeFaction.valueOf(lootRoll.roll(HeroTypeFaction.class));
                    HeroTypeQuality quality = HeroTypeQuality.valueOf(lootRoll.roll(HeroTypeQuality.class));

                    List<HeroType> heroTypes = heroTypeClient.findAllByFactionAndQuality(faction.toString(), quality.toString()).getData();

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
            heroType = heroTypeClient.findById(customHeroType).getData();
            heroQuality = HeroQuality.valueOf(customHeroQuality);
        }

        if (heroType == null) {
            throw new ValidationException("hero.type.not.found");
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

        hero.setUserId(userId);
        hero.setQuality(heroQuality);
        hero.setLevel(1);
        hero.setHeroTypeId(heroType.getId());

        return heroRepository.save(hero);

    }

    public Hero rollHero(HeroQuality hq, HeroType ht) {

        assert !hq.equals(HeroQuality.SHINE) && !hq.equals(HeroQuality.UNIQUE) : "Hero quality must be poor, average or best here, BUG";

        Hero ret = new Hero();

        ret.setBaseCritChance(DiceUtil.randomAttribute(hq, ht.getMinBaseCritChance(), ht.getMaxBaseCritChance()));
        ret.setBaseCritDamage(DiceUtil.randomAttribute(hq, ht.getMinBaseCritDamage(), ht.getMaxBaseCritDamage()));
        ret.setBaseDmg(DiceUtil.randomAttribute(hq, ht.getMinBaseDmg(), ht.getMaxBaseDmg()));
        ret.setBaseAp(DiceUtil.randomAttribute(hq, ht.getMinBaseAp(), ht.getMaxBaseAp()));
        ret.setBaseDodgeChance(DiceUtil.randomAttribute(hq, ht.getMinBaseDodgeChance(), ht.getMaxBaseDodgeChance()));
        ret.setBaseHp(DiceUtil.randomAttribute(hq, ht.getMinBaseHp(), ht.getMaxBaseHp()));
        ret.setBaseSpeed(DiceUtil.randomAttribute(hq, ht.getMinBaseSpeed(), ht.getMaxBaseSpeed()));

        ht.getMinBaseDefenses().forEach((def) -> {
            ret.setBaseDefense(def.getType(),
                    DiceUtil.randomAttribute(hq,
                            ht.getMinBaseDefense(def.getType()).getValue(),
                            ht.getMaxBaseDefense(def.getType()).getValue()
                    )
            );
            ret.setMaxLevelDefense(def.getType(),
                    DiceUtil.randomAttribute(hq,
                            ht.getMinMaxLevelDefense(def.getType()).getValue(),
                            ht.getMaxMaxLevelDefense(def.getType()).getValue()
                    )
            );
        });

        ret.setMaxLevelCritChance(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelCritChance(), ht.getMaxMaxLevelCritChance()));
        ret.setMaxLevelCritDamage(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelCritDamage(), ht.getMaxMaxLevelCritDamage()));
        ret.setMaxLevelDmg(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelDmg(), ht.getMaxMaxLevelDmg()));
        ret.setMaxLevelAp(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelAp(), ht.getMaxMaxLevelAp()));
        ret.setMaxLevelDodgeChance(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelDodgeChance(), ht.getMaxMaxLevelDodgeChance()));
        ret.setMaxLevelHp(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelHp(), ht.getMaxMaxLevelHp()));
        ret.setMaxLevelSpeed(DiceUtil.randomAttribute(hq, ht.getMinMaxLevelSpeed(), ht.getMaxMaxLevelSpeed()));

        return ret;
    }

    public Hero rollShineHero(HeroType heroType) {

        Hero ret = new Hero();

        ret.setBaseDefenses(heroType.getMaxBaseDefenses());
        ret.setBaseCritChance((int) (heroType.getMaxBaseCritChance()));
        ret.setBaseCritDamage((int) (heroType.getMaxBaseCritDamage()));
        ret.setBaseDmg((int) (heroType.getMaxBaseDmg()));
        ret.setBaseAp((int) (heroType.getMaxBaseAp()));
        ret.setBaseDodgeChance((int) (heroType.getMaxBaseDodgeChance()));
        ret.setBaseHp((int) (heroType.getMaxBaseHp()));
        ret.setBaseSpeed((int) (heroType.getMaxBaseSpeed()));

        ret.setMaxLevelDefenses(heroType.getMaxMaxLevelDefenses());
        ret.setMaxLevelCritChance((int) (heroType.getMaxMaxLevelCritChance()));
        ret.setMaxLevelCritDamage((int) (heroType.getMaxMaxLevelCritDamage()));
        ret.setMaxLevelDmg((int) (heroType.getMaxMaxLevelDmg()));
        ret.setMaxLevelAp((int) (heroType.getMaxMaxLevelAp()));
        ret.setMaxLevelDodgeChance((int) (heroType.getMaxMaxLevelDodgeChance()));
        ret.setMaxLevelHp((int) (heroType.getMaxMaxLevelHp()));
        ret.setMaxLevelSpeed((int) (heroType.getMaxMaxLevelSpeed()));

        return ret;
    }

    public Hero rollUniqueHero(HeroType ht) {

        Hero ret = new Hero();

        ret.setBaseCritChance((int) (ht.getMaxBaseCritChance()));
        ret.setBaseCritDamage((int) (ht.getMaxBaseCritDamage() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseDmg((int) (ht.getMaxBaseDmg() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseAp((int) (ht.getMaxBaseAp() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseDodgeChance((int) (ht.getMaxBaseDodgeChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseHp((int) (ht.getMaxBaseHp() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setBaseSpeed((int) (ht.getMaxBaseSpeed() * (1d + (DiceUtil.random(10) / 100d))));

        ht.getMinBaseDefenses().forEach((def) -> {
            ret.setBaseDefense(def.getType(),
                    (int) (ht.getMaxBaseDefense(def.getType()).getValue() * (1d + (DiceUtil.random(10) / 100d)))
            );
            ret.setMaxLevelDefense(def.getType(),
                    (int) (ht.getMaxMaxLevelDefense(def.getType()).getValue() * (1d + (DiceUtil.random(10) / 100d)))
            );
        });

        ret.setMaxLevelCritChance((int) (ht.getMaxMaxLevelCritChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelCritDamage((int) (ht.getMaxMaxLevelCritDamage() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelDmg((int) (ht.getMaxMaxLevelDmg() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelAp((int) (ht.getMaxMaxLevelAp() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelDodgeChance((int) (ht.getMaxMaxLevelDodgeChance() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelHp((int) (ht.getMaxMaxLevelHp() * (1d + (DiceUtil.random(10) / 100d))));
        ret.setMaxLevelSpeed((int) (ht.getMaxMaxLevelSpeed() * (1d + (DiceUtil.random(10) / 100d))));

        return ret;
    }

}
