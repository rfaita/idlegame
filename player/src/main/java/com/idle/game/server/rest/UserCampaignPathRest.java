package com.idle.game.server.rest;

import com.idle.game.core.battle.Battle;
import com.idle.game.helper.TokenHelper;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.model.campaign.UserCampaignPath;
import com.idle.game.server.service.UserCampaignPathService;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/userCampaighPath")
public class UserCampaignPathRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private UserCampaignPathService userCampaignPathService;

    @RequestMapping(path = "/battle/{formationId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Battle> battle(@PathVariable("formationId") String formationId) {

        Envelope<Battle> ret = new Envelope<>();
        ret.setData(userCampaignPathService.battle(formationId, tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "/battle", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Battle> battle() {

        Envelope<Battle> ret = new Envelope<>();
        ret.setData(userCampaignPathService.battle(tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<UserCampaignPath> findByUserId() {

        Envelope<UserCampaignPath> ret = new Envelope<>();
        ret.setData(userCampaignPathService.findByUserId(tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "/resetPath", method = RequestMethod.PUT)
    public @ResponseBody
    Envelope<Void> resetPath() {
        userCampaignPathService.resetPath(tokenHelper.getUserId());
        return new Envelope<>();

    }

}
