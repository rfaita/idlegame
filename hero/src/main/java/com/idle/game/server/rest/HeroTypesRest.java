package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import com.idle.game.server.dto.HeroTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.idle.game.constant.URIConstants.HERO_TYPES;

/**
 *
 * @author rafael
 */
@RestController
@RequestMapping(HERO_TYPES)
public class HeroTypesRest {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<HeroTypes> getHeroTypes() {

        Envelope<HeroTypes> ret = new Envelope<>();
        ret.setData(new HeroTypes());

        return ret;

    }

}
