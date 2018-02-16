package com.idle.game.server.rest;

import com.idle.game.constant.URIConstants;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.service.HeroTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.core.hero.type.HeroTypeFaction;
import static com.idle.game.constant.URIConstants.HEROTYPE__FIND_ALL_BY_QUALITY;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/heroType")
public class HeroTypeRest {

    @Autowired
    private HeroTypeService heroTypeService;

    @RequestMapping(path = "/findByName/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<HeroType> findByName(@PathVariable("name") String name) {

        Envelope<HeroType> ret = new Envelope<>();
        ret.setData(heroTypeService.findByName(name));

        return ret;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<HeroType> findById(@PathVariable("id") String id) {

        Envelope<HeroType> ret = new Envelope<>();
        ret.setData(heroTypeService.findById(id));

        return ret;

    }
    
    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<HeroType>> findAll() {

        Envelope<List<HeroType>> ret = new Envelope<>();
        ret.setData(heroTypeService.findAll());

        return ret;

    }
    
    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<HeroType> save(@RequestBody HeroType heroType) {

        Envelope<HeroType> ret = new Envelope<>();
        ret.setData(heroTypeService.save(heroType));

        return ret;

    }

    @RequestMapping(path = "/" + HEROTYPE__FIND_ALL_BY_QUALITY + "/{quality}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<HeroType>> findAllByQuality(@PathVariable("quality") String quality) {

        Envelope<List<HeroType>> ret = new Envelope<>();
        ret.setData(heroTypeService.findAllByQuality(HeroTypeQuality.valueOf(quality)));

        return ret;

    }

    @RequestMapping(path = "/" + URIConstants.HEROTYPE__FIND_ALL_BY_FACTION + "/{faction}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<HeroType>> findAllByFaction(@PathVariable("faction") String faction) {

        Envelope<List<HeroType>> ret = new Envelope<>();
        ret.setData(heroTypeService.findAllByFaction(HeroTypeFaction.valueOf(faction)));

        return ret;

    }

    @RequestMapping(path = "/" + URIConstants.HEROTYPE__FIND_ALL_BY_FACTION_AND_QUALITY + "/{faction}/{quality}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<HeroType>> findAllByFactionAndQuality(@PathVariable("faction") String faction, @PathVariable("quality") String quality) {

        Envelope<List<HeroType>> ret = new Envelope<>();
        ret.setData(heroTypeService.findAllByFactionAndQuality(HeroTypeFaction.valueOf(faction), HeroTypeQuality.valueOf(quality)));

        return ret;

    }

}
