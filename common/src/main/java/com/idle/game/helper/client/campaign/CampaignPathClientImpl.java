package com.idle.game.helper.client.campaign;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.model.Guild;
import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class CampaignPathClientImpl implements CampaignPathClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<CampaignPath> getInitialPath() {
        CampaignPath ret = (CampaignPath) redisTemplate.boundValueOps(CAMPAIGN_PATH_INITAL_PATH).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((Guild) null);
        }
    }

    @Override
    public Envelope<CampaignPath> findById(String id) {
        CampaignPath ret = (CampaignPath) redisTemplate.boundValueOps(CAMPAIGN_PATH_INITAL_PATH + id).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((CampaignPath) null);
        }

    }
}
