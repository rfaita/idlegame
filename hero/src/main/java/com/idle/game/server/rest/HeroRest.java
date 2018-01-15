package com.idle.game.server.rest;

import com.idle.game.model.mongo.Hero;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public @ResponseBody
    Envelope<Hero> rollHero() {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.rollHero("asd"));

        return ret;

    }

    @RequestMapping(path = "/all/{player}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<Hero>> findByAllByPlayer(@PathVariable("player") String player) {

        Envelope<List<Hero>> ret = new Envelope<>();
        ret.setData(heroService.findByAllByPlayer(player));

        return ret;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Hero> findById(@PathVariable("id") String id) {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.findById(id));

        return ret;

    }

    @RequestMapping(path = "/levelUp/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Hero> levelUp(@PathVariable("id") String id) {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.levelUp(id));

        return ret;

    }

}
