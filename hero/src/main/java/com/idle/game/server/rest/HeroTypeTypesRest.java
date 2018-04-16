package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import com.idle.game.server.dto.HeroTypeTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.idle.game.constant.URIConstants.HERO_TYPE_TYPES;

/**
 *
 * @author rafael
 */
@RestController
@RequestMapping(HERO_TYPE_TYPES)
public class HeroTypeTypesRest {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<HeroTypeTypes> getHeroTypeTypes() {

        Envelope<HeroTypeTypes> ret = new Envelope<>();
        ret.setData(new HeroTypeTypes());

        return ret;

    }

}
