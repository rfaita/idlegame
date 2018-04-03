package com.idle.game.server.repository;

import com.idle.game.model.campaign.UserCampaignPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface UserCampaignPathRepository extends MongoRepository<UserCampaignPath, String> {

    UserCampaignPath findByUserId(String userId);

}
