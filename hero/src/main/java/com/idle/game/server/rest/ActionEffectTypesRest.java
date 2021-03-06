package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.ACTION_EFFECT_TYPES;
import com.idle.game.server.dto.ActionEffectTypes;
import com.idle.game.server.dto.Envelope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael
 */

@RestController
@RequestMapping(ACTION_EFFECT_TYPES)
public class ActionEffectTypesRest {
    
     @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<ActionEffectTypes> getActionEffectTypes() {

        Envelope<ActionEffectTypes> ret = new Envelope<>();
        ret.setData(new ActionEffectTypes());

        return ret;

    }
    
}
