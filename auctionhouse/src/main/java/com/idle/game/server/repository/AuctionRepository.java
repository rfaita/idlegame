package com.idle.game.server.repository;

import com.idle.game.model.actionhouse.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface AuctionRepository extends MongoRepository<Auction, String> {

    Auction findByUserId(String userId);

}
