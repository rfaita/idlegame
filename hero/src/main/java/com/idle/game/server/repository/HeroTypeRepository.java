package com.idle.game.server.repository;

import com.idle.game.core.type.HeroTypeQuality;
import com.idle.game.model.mongo.HeroType;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface HeroTypeRepository extends MongoRepository<HeroType, String> {

    HeroType findById(String id);

    HeroType findByName(String name);

    List<HeroType> findAllByHeroTypeQuality(HeroTypeQuality heroTypeQuality);

}
