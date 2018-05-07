package com.idle.game.server.repository;

import com.idle.game.model.actionhouse.AuctionBid;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface AuctionBidRepository extends MongoRepository<AuctionBid, String> {

    List<AuctionBid> findAllByAuctionId(String id);

}
