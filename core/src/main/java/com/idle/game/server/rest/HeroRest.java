package com.idle.game.server.rest;

import com.idle.game.model.mongo.Hero;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hero")
public class HeroRest {

    @Autowired
    private HeroService heroService;

    @RequestMapping(path = "/roll", method = RequestMethod.GET)
    public @ResponseBody Envelope<Hero> doPost() {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.rollHero("asd"));

        return ret;

    }

}
