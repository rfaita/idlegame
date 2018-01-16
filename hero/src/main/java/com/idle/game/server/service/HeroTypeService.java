package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_NAME;
import com.idle.game.core.type.HeroTypeQuality;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.server.repository.HeroTypeRepository;
import java.util.List;
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
public class HeroTypeService {

    @Autowired
    private HeroTypeRepository heroTypeRepository;

    @Cacheable(value = HERO_TYPE_FIND_BY_NAME, key = "'" + HERO_TYPE_FIND_BY_NAME + "' + #n")
    public HeroType findByName(String n) {
        return heroTypeRepository.findByName(n);
    }

    @Cacheable(value = HERO_TYPE_FIND_BY_ID, key = "'" + HERO_TYPE_FIND_BY_ID + "' + #id")
    public HeroType findById(String id) {
        return heroTypeRepository.findById(id);
    }

    @Caching(put
            = @CachePut(value = HERO_TYPE_FIND_BY_NAME, key = "'" + HERO_TYPE_FIND_BY_NAME + "' + #ht.name"),
            evict
            = @CacheEvict(value = HERO_TYPE_FIND_BY_ID, key = "'" + HERO_TYPE_FIND_BY_ID + "' + #ht.id")
    )
    public HeroType save(HeroType ht) {
        return heroTypeRepository.save(ht);
    }

    public List<HeroType> findAllByHeroTypeQuality(HeroTypeQuality quality) {

        return heroTypeRepository.findAllByHeroTypeQuality(quality);
    }

}
