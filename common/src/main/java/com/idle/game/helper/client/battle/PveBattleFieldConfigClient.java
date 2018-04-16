package com.idle.game.helper.client.battle;

import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static com.idle.game.constant.URIConstants.PVE_BATTLEFIELDCONFIG_UPGRADE;
import static com.idle.game.constant.URIConstants.PVE_BATTLE_FIELD_CONFIG;
import com.idle.game.model.battle.pve.PveBattleFieldConfig;

/**
 *
 * @author rafael
 */
@FeignClient(name = "battle-service", path = PVE_BATTLE_FIELD_CONFIG, fallback = PveBattleFieldConfigClientImpl.class)
public interface PveBattleFieldConfigClient {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Envelope<PveBattleFieldConfig> findByUserId();

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Envelope<PveBattleFieldConfig> create();

    @RequestMapping(path = "/" + PVE_BATTLEFIELDCONFIG_UPGRADE, method = RequestMethod.PUT)
    public Envelope<PveBattleFieldConfig> upgrade();

}
