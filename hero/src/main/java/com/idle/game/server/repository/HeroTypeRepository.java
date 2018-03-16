package com.idle.game.server.repository;

import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.model.HeroType;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface HeroTypeRepository extends MongoRepository<HeroType, String> {

    HeroType findByName(String name);

    List<HeroType> findAllByQuality(HeroTypeQuality quality);

    List<HeroType> findAllByFaction(HeroTypeFaction faction);

    List<HeroType> findAllByFactionAndQuality(HeroTypeFaction faction, HeroTypeQuality quality);

}
