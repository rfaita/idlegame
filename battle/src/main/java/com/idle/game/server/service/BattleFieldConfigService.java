package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.model.battle.BattleFieldConfig;
import com.idle.game.server.repository.BattleFieldConfigRepository;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class BattleFieldConfigService {

    @Value("${idle.config.battleFieldConfig.guildMaxLayerSize}")
    private Integer guildMaxLayerSize;
    @Value("${idle.config.battleFieldConfig.defaultGuildMaxLayerSize}")
    private Integer defaultGuildMaxLayerSize;
    @Value("${idle.config.battleFieldConfig.growthFactorGuildMaxSitesSize}")
    private Integer growthFactorGuildMaxSitesSize;
    @Value("${idle.config.battleFieldConfig.userMaxLayerSize}")
    private Integer userMaxLayerSize;
    @Value("${idle.config.battleFieldConfig.defaultUserMaxLayerSize}")
    private Integer defaultUserMaxLayerSize;
    @Value("${idle.config.battleFieldConfig.growthFactorUserMaxSitesSize}")
    private Integer growthFactorUserMaxSitesSize;

    @Autowired
    private BattleFieldConfigRepository battleFieldConfigRepository;

    @Cacheable(value = BATTLE_FIELD_CONFIG_FIND_BY_USER_ID, key = "'" + BATTLE_FIELD_CONFIG_FIND_BY_USER_ID + "' + #userId")
    public BattleFieldConfig findByUserId(String userId) {
        BattleFieldConfig ret = battleFieldConfigRepository.findByUserId(userId);

        for (int i = 0; i < ret.getMaxLayerSize(); i++) {
            ret.setMaxSiteSize(i, (i + 1) * growthFactorGuildMaxSitesSize);
        }

        return ret;
    }

    @Cacheable(value = BATTLE_FIELD_CONFIG_FIND_BY_GUILD_ID, key = "'" + BATTLE_FIELD_CONFIG_FIND_BY_GUILD_ID + "' + #guildId")
    public BattleFieldConfig findByGuildId(String guildId) {
        BattleFieldConfig ret = battleFieldConfigRepository.findByUserId(guildId);

        for (int i = 0; i < ret.getMaxLayerSize(); i++) {
            ret.setMaxSiteSize(i, (i + 1) * growthFactorUserMaxSitesSize);
        }

        return ret;
    }

    public BattleFieldConfig createByUserId(String userId) {
        BattleFieldConfig battleFieldConfig = new BattleFieldConfig();

        battleFieldConfig.setUserId(userId);
        battleFieldConfig.setMaxLayerSize(defaultUserMaxLayerSize);

        return battleFieldConfigRepository.save(battleFieldConfig);
    }

    public BattleFieldConfig createByGuildId(String guildId) {
        BattleFieldConfig battleFieldConfig = new BattleFieldConfig();

        battleFieldConfig.setGuildId(guildId);
        battleFieldConfig.setMaxLayerSize(defaultGuildMaxLayerSize);

        return battleFieldConfigRepository.save(battleFieldConfig);
    }

    @CacheEvict(value = BATTLE_FIELD_CONFIG_FIND_BY_USER_ID, key = "'" + BATTLE_FIELD_CONFIG_FIND_BY_USER_ID + "' + #userId")
    public BattleFieldConfig upgradeMaxLayerSizeByUserId(String userId) {

        BattleFieldConfig bfg = battleFieldConfigRepository.findByUserId(userId);

        if (bfg == null) {
            throw new ValidationException("battle.field.config.not.found");
        }

        if (bfg.getMaxLayerSize() + 1 > userMaxLayerSize) {
            throw new ValidationException("battle.field.max.layer.size.reached");
        }

        bfg.setMaxLayerSize(bfg.getMaxLayerSize() + 1);

        return battleFieldConfigRepository.save(bfg);
    }

    @CacheEvict(value = BATTLE_FIELD_CONFIG_FIND_BY_GUILD_ID, key = "'" + BATTLE_FIELD_CONFIG_FIND_BY_GUILD_ID + "' + #guildId")
    public BattleFieldConfig upgradeMaxLayerSizeByGuildId(String guildId) {

        BattleFieldConfig bfg = battleFieldConfigRepository.findByGuildId(guildId);

        if (bfg == null) {
            throw new ValidationException("battle.field.config.not.found");
        }

        if (bfg.getMaxLayerSize() + 1 > guildMaxLayerSize) {
            throw new ValidationException("battle.field.max.layer.size.reached");
        }

        bfg.setMaxLayerSize(bfg.getMaxLayerSize() + 1);

        return battleFieldConfigRepository.save(bfg);
    }

}
