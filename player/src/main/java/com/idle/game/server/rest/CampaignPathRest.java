package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.CAMPAIGN_PATH;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.server.service.CampaignPathService;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(CAMPAIGN_PATH)
public class CampaignPathRest {

    @Autowired
    private CampaignPathService campaignPathService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<CampaignPath> getInitialPath() {

        Envelope<CampaignPath> ret = new Envelope<>();
        ret.setData(campaignPathService.getInitialPath());

        return ret;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<CampaignPath> findById(@PathVariable("id") String id) {

        Envelope<CampaignPath> ret = new Envelope<>();
        ret.setData(campaignPathService.findById(id));

        return ret;

    }

}
