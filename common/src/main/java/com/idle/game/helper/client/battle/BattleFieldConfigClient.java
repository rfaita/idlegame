package com.idle.game.helper.client.battle;

import static com.idle.game.constant.URIConstants.BATTLEFIELDCONFIG_UPDATE_MAX_LAYER_SIZE;
import com.idle.game.model.battle.BattleFieldConfig;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "battle-field-config-client", url = "${idle.url.battleFieldConfig}", fallback = BattleFieldConfigClientImpl.class)
public interface BattleFieldConfigClient {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Envelope<BattleFieldConfig> findByUserId();

    @RequestMapping(path = "/{guildId}", method = RequestMethod.GET)
    Envelope<BattleFieldConfig> findByGuildId(@PathVariable("guildId") String guildId);

    @RequestMapping(path = "/{guildId}", method = RequestMethod.POST)
    public Envelope<BattleFieldConfig> createByGuildId(@PathVariable("guildId") String guildId);

    @RequestMapping(path = "/" + BATTLEFIELDCONFIG_UPDATE_MAX_LAYER_SIZE + "/{guildId}", method = RequestMethod.PUT)
    public Envelope<BattleFieldConfig> upgradeMaxLayerSize(@PathVariable("guildId") String guildId);

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Envelope<BattleFieldConfig> createByUserId();

    @RequestMapping(path = "/" + BATTLEFIELDCONFIG_UPDATE_MAX_LAYER_SIZE, method = RequestMethod.PUT)
    public Envelope<BattleFieldConfig> upgradeMaxLayerSizeByUserId();

}
