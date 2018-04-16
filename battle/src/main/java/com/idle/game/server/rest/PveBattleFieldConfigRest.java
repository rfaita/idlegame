package com.idle.game.server.rest;

import com.idle.game.helper.TokenHelper;
import com.idle.game.server.service.*;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.idle.game.constant.URIConstants.PVE_BATTLEFIELDCONFIG_UPGRADE;
import com.idle.game.model.battle.pve.PveBattleFieldConfig;

@RestController
@RequestMapping("/pveBattleFieldConfig")
public class PveBattleFieldConfigRest {

    @Autowired
    private PveBattleFieldConfigService pveBattleFieldConfigService;

    @Autowired
    private TokenHelper tokenHelper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<PveBattleFieldConfig> findByUserId() {

        Envelope<PveBattleFieldConfig> ret = new Envelope<>();
        ret.setData(pveBattleFieldConfigService.findByUserId(tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<PveBattleFieldConfig> createByUserId() {
        Envelope<PveBattleFieldConfig> ret = new Envelope<>();
        ret.setData(pveBattleFieldConfigService.create(tokenHelper.getUserId()));

        return ret;
    }

    @RequestMapping(path = "/" + PVE_BATTLEFIELDCONFIG_UPGRADE, method = RequestMethod.PUT)
    public @ResponseBody
    Envelope<PveBattleFieldConfig> upgrade() {
        Envelope<PveBattleFieldConfig> ret = new Envelope<>();
        ret.setData(pveBattleFieldConfigService.upgrade(tokenHelper.getUserId()));

        return ret;
    }

}
