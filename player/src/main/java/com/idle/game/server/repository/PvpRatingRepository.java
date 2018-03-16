package com.idle.game.server.repository;

import com.idle.game.model.PvpRating;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface PvpRatingRepository extends MongoRepository<PvpRating, String> {

    PvpRating findByPlayer(String player);

    PvpRating findByPlayerAndId(String player, String id);

    List<PvpRating> findAllByPlayerNotAndRatingBetween(String player, Integer startRating, Integer endRating);

    List<PvpRating> findAllByOrderByRatingDesc(Pageable pageable);

}
