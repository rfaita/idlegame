package com.idle.game.helper.client.campaign;

import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "player-service", path = "/campaignPath", fallback = CampaignPathClientImpl.class)
public interface CampaignPathClient {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Envelope<CampaignPath> getInitialPath();

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<CampaignPath> findById(@PathVariable("id") String id);

}
