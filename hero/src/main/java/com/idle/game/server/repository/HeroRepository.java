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

    Hero findById(String id);

    List<Hero> findAllByPlayerId(String player);

    List<Hero> findAllByPlayerIdAndQuality(String player, HeroQuality quality);

}
