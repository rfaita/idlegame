package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.BATTLE_HERO;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BATTLE_HERO)
public class BattleHeroRest {

    @Autowired
    private BattleHeroService heroService;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<BattleHero> getBattleHero(@PathVariable("id") String id) {

        Envelope<BattleHero> ret = new Envelope<>();
        ret.setData(heroService.getBattleHero(id));

        return ret;

    }

}
