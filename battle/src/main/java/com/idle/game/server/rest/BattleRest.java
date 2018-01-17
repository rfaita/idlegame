package com.idle.game.server.rest;

import com.idle.game.core.battle.Battle;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battle")
public class BattleRest {

    @Autowired
    private BattleService battleService;
    
    @RequestMapping(path = "/{att}/{def}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Battle> doBattle(@PathVariable("att") String att,@PathVariable("def") String def) {

        Envelope<Battle> ret = new Envelope<>();
        ret.setData(battleService.doBattle(att, def));

        return ret;

    }

}
