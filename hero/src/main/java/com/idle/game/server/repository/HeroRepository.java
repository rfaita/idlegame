package com.idle.game.server.repository;

import com.idle.game.model.mongo.Hero;
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

    List<Hero> findAllByPlayer(String player);

}
