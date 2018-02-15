package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import com.idle.game.server.dto.HeroTypeTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael
 */

@RestController
@RequestMapping("/heroTypeTypes")
public class HeroTypeTypesRest {
    
     @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<HeroTypeTypes> getHeroTypeTypes() {

        Envelope<HeroTypeTypes> ret = new Envelope<>();
        ret.setData(new HeroTypeTypes());

        return ret;

    }
    
}
