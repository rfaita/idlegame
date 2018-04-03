package com.idle.game.server.rest;

import com.idle.game.model.battle.BattleField;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battleField")
public class BattleFieldRest {

    @Autowired
    private BattleFieldService battleFieldService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<BattleField> findById(@PathVariable("id") String id) {

        Envelope<BattleField> ret = new Envelope<>();
        ret.setData(battleFieldService.findById(id));

        return ret;

    }

}
