package com.idle.game.server.rest;

import com.idle.game.core.type.HeroTypeQuality;
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
import static com.idle.game.constant.URIConstants.HERO__FIND_ALL_BY_HERO_TYPE_QUALITY;

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

    @RequestMapping(path = "/" + HERO__FIND_ALL_BY_HERO_TYPE_QUALITY + "/{quality}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<HeroType>> findAllByHeroTypeQuality(@PathVariable("quality") String quality) {

        Envelope<List<HeroType>> ret = new Envelope<>();
        ret.setData(heroTypeService.findAllByHeroTypeQuality(HeroTypeQuality.valueOf(quality)));

        return ret;

    }

}
