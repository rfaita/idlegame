package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.HERO;
import static com.idle.game.constant.URIConstants.HERO__ROLL;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.Hero;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.idle.game.constant.URIConstants.HERO__FIND_ALL_BY_USER_ID;

@RestController
@RequestMapping(HERO)
public class HeroRest {

    @Autowired
    private HeroService heroService;

    @Autowired
    private TokenHelper tokenHelper;

    @RequestMapping(path = "/customRoll/{userId}/{heroType}/{heroQuality}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Hero> customRoll(@PathVariable("userId") String userId,
            @PathVariable("heroType") String heroType, @PathVariable("heroQuality") String heroQuality) {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.customRollHero(userId, heroType, heroQuality));

        return ret;

    }

    @RequestMapping(path = "/" + HERO__ROLL + "/{lootRollId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Hero> rollHero(@PathVariable("lootRollId") String lootRollId) {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.rollHero(tokenHelper.getUserId(), lootRollId));

        return ret;

    }

    @RequestMapping(path = "/" + HERO__FIND_ALL_BY_USER_ID + "/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<Hero>> findAllByUserId(@PathVariable("userId") String userId) {

        Envelope<List<Hero>> ret = new Envelope<>();
        ret.setData(heroService.findAllByUserId(userId));

        return ret;

    }

    @RequestMapping(path = "/" + HERO__FIND_ALL_BY_USER_ID + "/{userId}/{heroTypeId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<Hero>> findAllByUserIdAndHeroTypeId(@PathVariable("userId") String userId, @PathVariable("heroTypeId") String heroTypeId) {

        Envelope<List<Hero>> ret = new Envelope<>();
        ret.setData(heroService.findAllByUserIdAndHeroTypeId(userId, heroTypeId));

        return ret;

    }

    @RequestMapping(path = "/" + HERO__FIND_ALL_BY_USER_ID + "/{userId}/{heroTypeId}/{quality}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<Hero>> findAllByUserIdAndHeroTypeIdAndQuality(@PathVariable("userId") String userId,
            @PathVariable("quality") String quality,
            @PathVariable("heroTypeId") String heroTypeId) {

        Envelope<List<Hero>> ret = new Envelope<>();
        ret.setData(heroService.findAllByUserIdAndHeroTypeIdAndQuality(userId, heroTypeId, HeroQuality.valueOf(quality)));

        return ret;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Envelope<Void> delete(@PathVariable("id") String id) {

        heroService.delete(id);
        return new Envelope<>();

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<Hero> save(@RequestBody Hero hero) {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.save(hero));

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
        ret.setData(heroService.levelUp(id, tokenHelper.getUserId()));

        return ret;

    }

}
