package com.idle.game.server.repository;

import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.model.Hero;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {

    List<Hero> findAllByUserId(String userId);

    List<Hero> findAllByUserIdAndQuality(String userId, HeroQuality quality);

    List<Hero> findAllByUserIdAndHeroTypeIdAndQuality(String userId, String heroTypeId, HeroQuality quality);

    List<Hero> findAllByUserIdAndHeroTypeId(String userId, String heroTypeId);

}
