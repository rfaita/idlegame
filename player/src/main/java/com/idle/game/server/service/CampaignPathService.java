package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.server.repository.CampaignPathRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class CampaignPathService {

    @Autowired
    private CampaignPathRepository campaignPathRepository;

    @Cacheable(value = CAMPAIGN_PATH_INITAL_PATH, key = "'" + CAMPAIGN_PATH_INITAL_PATH + "'")
    public CampaignPath getInitialPath() {
        return campaignPathRepository.findByInitialPath(Boolean.TRUE);
    }

    @Cacheable(value = CAMPAIGN_PATH_FIND_BY_ID, key = "'" + CAMPAIGN_PATH_FIND_BY_ID + "' + #id")
    public CampaignPath findById(String id) {
        Optional<CampaignPath> ret = campaignPathRepository.findById(id);

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

}
