package com.idle.game.server.service;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.type.HeroTypeQuality;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.server.repository.HeroTypeRepository;
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
public class HeroTypeService {

    @Autowired
    private HeroTypeRepository heroTypeRepository;

    @Cacheable(cacheNames = {"heroTypeFindByName"}, key = "#name")
    public HeroType findByName(String name) {

        return heroTypeRepository.findByName(name);
    }

    @Cacheable(cacheNames = {"heroTypeFindById"}, key = "#id")
    public HeroType findById(String id) {

        return heroTypeRepository.findById(id);
    }

    public List<HeroType> findAllByHeroTypeQuality(HeroTypeQuality quality) {

        return heroTypeRepository.findAllByHeroTypeQuality(quality);
    }

}
