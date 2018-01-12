package com.idle.game.server.rest;

import com.idle.game.model.mongo.HeroType;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.repository.HeroTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroType")
public class HeroTypeRest {

    @Autowired
    private HeroTypeRepository heroTypeRepository;

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<HeroType> doGet(@PathVariable("name") String name) {

        Envelope<HeroType> ret = new Envelope<>();
        ret.setData(heroTypeRepository.findByName(name));

        return ret;

    }

}
