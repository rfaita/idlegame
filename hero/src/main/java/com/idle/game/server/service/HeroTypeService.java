package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_ALL;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_NAME;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.type.DefenseType;
import com.idle.game.model.HeroType;
import com.idle.game.server.repository.HeroTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION;

/**
 *
 * @author rafael
 */
@Service
public class HeroTypeService {

    @Autowired
    private HeroTypeRepository heroTypeRepository;

    @Cacheable(value = HERO_TYPE_FIND_BY_NAME, key = "'" + HERO_TYPE_FIND_BY_NAME + "' + #n")
    public HeroType findByName(String n) {
        return heroTypeRepository.findByName(n);
    }

    @Cacheable(value = HERO_TYPE_FIND_BY_ID, key = "'" + HERO_TYPE_FIND_BY_ID + "' + #id")
    public HeroType findById(String id) {
        return heroTypeRepository.findOne(id);
    }

    @Caching(put
            = @CachePut(value = HERO_TYPE_FIND_BY_NAME, key = "'" + HERO_TYPE_FIND_BY_NAME + "' + #ht.name"),
            evict
            = {
                @CacheEvict(value = HERO_TYPE_FIND_BY_ID, key = "'" + HERO_TYPE_FIND_BY_ID + "' + #ht.id")
                ,
                @CacheEvict(value = HERO_TYPE_FIND_ALL, allEntries = true)
                ,
                @CacheEvict(value = BATTLE_HERO_FIND_BY_ID, allEntries = true)
                ,
                @CacheEvict(value = FORMATION_FIND_BY_ID, allEntries = true)
                ,
                @CacheEvict(value = FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION, allEntries = true)

            }
    )
    public HeroType save(HeroType ht) {
        //Set all missing defesens to zero
        for (DefenseType dt : DefenseType.values()) {
            ht.getMinBaseDefense(dt);
            ht.getMaxBaseDefense(dt);
            ht.getMinMaxLevelDefense(dt);
            ht.getMaxMaxLevelDefense(dt);
        }
        return heroTypeRepository.save(ht);
    }

    @Cacheable(value = HERO_TYPE_FIND_ALL, key = "'" + HERO_TYPE_FIND_ALL + "'")
    public List<HeroType> findAll() {

        return heroTypeRepository.findAll();
    }

    public List<HeroType> findAllByQuality(HeroTypeQuality quality) {

        return heroTypeRepository.findAllByQuality(quality);
    }

    public List<HeroType> findAllByFaction(HeroTypeFaction faction) {

        return heroTypeRepository.findAllByFaction(faction);
    }

    public List<HeroType> findAllByFactionAndQuality(HeroTypeFaction faction, HeroTypeQuality quality) {

        return heroTypeRepository.findAllByFactionAndQuality(faction, quality);
    }

}
