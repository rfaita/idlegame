package com.idle.game.server.repository;

import com.idle.game.model.campaign.CampaignPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface CampaignPathRepository extends MongoRepository<CampaignPath, String> {

    CampaignPath findByInitialPath(Boolean initialPath);

}
