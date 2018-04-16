package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.BATTLE_FIELD;
import com.idle.game.model.battle.BattleField;
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
@RequestMapping(BATTLE_FIELD)
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

    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<BattleField> update(@RequestBody BattleField battleField) {
        Envelope<BattleField> ret = new Envelope<>();
        ret.setData(battleFieldService.update(battleField));

        return ret;

    }

}
