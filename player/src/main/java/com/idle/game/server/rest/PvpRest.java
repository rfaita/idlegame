package com.idle.game.server.rest;

import com.idle.game.core.battle.Battle;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.mongo.PvpRating;
import com.idle.game.server.service.PvpService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/pvp")
public class PvpRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private PvpService pvpRatingService;

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void removePvpRatings() {
        pvpRatingService.removePvpRatings(tokenHelper.getSubject());
    }

    @RequestMapping(path = "/roll", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<PvpRating>> roll() {

        Envelope<List<PvpRating>> ret = new Envelope<>();
        ret.setData(pvpRatingService.findPvpRatings(tokenHelper.getSubject()));

        return ret;

    }

    @RequestMapping(path = "/battle/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Battle> battlePvpRattings(@PathVariable("id") String id) {

        Envelope<Battle> ret = new Envelope<>();
        ret.setData(pvpRatingService.battlePvpRattings(tokenHelper.getSubject(), id));

        return ret;

    }
}
