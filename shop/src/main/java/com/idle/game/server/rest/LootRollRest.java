package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.SHOP__BUY_LOOT_ROLL;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.service.LootRollService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael
 */
@RestController
@RequestMapping("/shop")
public class LootRollRest {

    @Autowired
    private LootRollService lootRollService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<LootRoll>> findAll() {

        Envelope<List<LootRoll>> ret = new Envelope<>();
        ret.setData(lootRollService.findAll());

        return ret;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<LootRoll> findById(@PathVariable("id") String id) {

        Envelope<LootRoll> ret = new Envelope<>();
        ret.setData(lootRollService.findById(id));

        return ret;

    }

    @RequestMapping(path = "/" + SHOP__BUY_LOOT_ROLL + "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<LootRoll> buyById(@PathVariable("id") String id) {

        Envelope<LootRoll> ret = new Envelope<>();
        ret.setData(lootRollService.buyById(id));

        return ret;

    }

}
