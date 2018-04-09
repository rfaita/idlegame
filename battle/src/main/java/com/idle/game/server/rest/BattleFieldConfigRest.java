package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.BATTLEFIELDCONFIG_UPDATE_MAX_LAYER_SIZE;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.battle.BattleFieldConfig;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battleFieldConfig")
public class BattleFieldConfigRest {

    @Autowired
    private BattleFieldConfigService battleFieldConfigService;

    @Autowired
    private TokenHelper tokenHelper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<BattleFieldConfig> findByUserId() {

        Envelope<BattleFieldConfig> ret = new Envelope<>();
        ret.setData(battleFieldConfigService.findByUserId(tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "/{guildId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<BattleFieldConfig> findByGuildId(@PathVariable("guildId") String guildId) {

        Envelope<BattleFieldConfig> ret = new Envelope<>();
        ret.setData(battleFieldConfigService.findByGuildId(guildId));

        return ret;

    }

    @RequestMapping(path = "/{guildId}", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<BattleFieldConfig> createByGuildId(@PathVariable("guildId") String guildId) {
        Envelope<BattleFieldConfig> ret = new Envelope<>();
        ret.setData(battleFieldConfigService.createByGuildId(guildId));

        return ret;
    }

    @RequestMapping(path = "/" + BATTLEFIELDCONFIG_UPDATE_MAX_LAYER_SIZE + "/{guildId}", method = RequestMethod.PUT)
    public @ResponseBody
    Envelope<BattleFieldConfig> upgradeMaxLayerSize(@PathVariable("guildId") String guildId) {
        Envelope<BattleFieldConfig> ret = new Envelope<>();
        ret.setData(battleFieldConfigService.upgradeMaxLayerSizeByGuildId(guildId));

        return ret;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<BattleFieldConfig> createByUserId() {
        Envelope<BattleFieldConfig> ret = new Envelope<>();
        ret.setData(battleFieldConfigService.createByUserId(tokenHelper.getUserId()));

        return ret;
    }

    @RequestMapping(path = "/" + BATTLEFIELDCONFIG_UPDATE_MAX_LAYER_SIZE, method = RequestMethod.PUT)
    public @ResponseBody
    Envelope<BattleFieldConfig> upgradeMaxLayerSizeByUserId() {
        Envelope<BattleFieldConfig> ret = new Envelope<>();
        ret.setData(battleFieldConfigService.upgradeMaxLayerSizeByUserId(tokenHelper.getUserId()));

        return ret;
    }

}
