package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.FORMATION;
import static com.idle.game.constant.URIConstants.FORMATION__FIND_BY_FORMATION_ALLOCATION;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.Formation;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FORMATION)
public class FormationRest {

    @Autowired
    private FormationService formationService;

    @Autowired
    private TokenHelper tokenHelper;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Formation> findById(@PathVariable("id") String id) {

        Envelope<Formation> ret = new Envelope<>();
        ret.setData(formationService.findById(id));

        return ret;

    }

    @RequestMapping(path = "/" + FORMATION__FIND_BY_FORMATION_ALLOCATION + "/{formationAllocation}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Formation> findByUserIdAndFormationAllocation(@PathVariable("formationAllocation") String fa) {

        Envelope<Formation> ret = new Envelope<>();
        ret.setData(formationService.findByUserIdAndFormationAllocation(tokenHelper.getUserId(), FormationAllocation.valueOf(fa)));

        return ret;

    }

    @RequestMapping(path = "/{userId}/{formationAllocation}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Formation> findByUserIdAndFormationAllocation(@PathVariable("userId") String userId, @PathVariable("formationAllocation") String fa) {

        Envelope<Formation> ret = new Envelope<>();
        ret.setData(formationService.findByUserIdAndFormationAllocation(userId, FormationAllocation.valueOf(fa)));

        return ret;

    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Envelope<Formation> save(@RequestBody Formation f) throws Exception {

        Envelope<Formation> ret = new Envelope<>(formationService.save(f, tokenHelper.getUserId(), tokenHelper.isAdmin()));

        return ret;
    }
}
