package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.model.battle.pve.PveBattleFieldConfig;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.idle.game.server.repository.PveBattleFieldConfigRepository;

/**
 *
 * @author rafael
 */
@Service
public class PveBattleFieldConfigService {

    @Value("${idle.config.pveBattleFieldConfig.growthFactorMaxSitesSize}")
    private Integer growthFactorMaxSitesSize;
    @Value("${idle.config.pveBattleFieldConfig.maxLayerSize}")
    private Integer maxLayerSize;
    @Value("${idle.config.pveBattleFieldConfig.defaultMaxLayerSize}")
    private Integer defaultMaxLayerSize;

    @Autowired
    private PveBattleFieldConfigRepository pveBattleFieldConfigRepository;

    @Cacheable(value = PVE_BATTLE_FIELD_CONFIG_FIND_BY_USER_ID, key = "'" + PVE_BATTLE_FIELD_CONFIG_FIND_BY_USER_ID + "' + #userId")
    public PveBattleFieldConfig findByUserId(String userId) {
        PveBattleFieldConfig ret = pveBattleFieldConfigRepository.findByUserId(userId);

        for (int i = 0; i < ret.getMaxLayerSize(); i++) {
            ret.setMaxSiteSize(i, (i + 1) * growthFactorMaxSitesSize);
        }

        return ret;
    }

    public PveBattleFieldConfig create(String userId) {
        PveBattleFieldConfig pveBattleFieldConfig = new PveBattleFieldConfig();

        pveBattleFieldConfig.setUserId(userId);
        pveBattleFieldConfig.setLevel(1);
        pveBattleFieldConfig.setMaxLayerSize(defaultMaxLayerSize);

        return pveBattleFieldConfigRepository.save(pveBattleFieldConfig);
    }

    @CacheEvict(value = PVE_BATTLE_FIELD_CONFIG_FIND_BY_USER_ID, key = "'" + PVE_BATTLE_FIELD_CONFIG_FIND_BY_USER_ID + "' + #userId")
    public PveBattleFieldConfig upgrade(String userId) {

        PveBattleFieldConfig bfg = pveBattleFieldConfigRepository.findByUserId(userId);

        if (bfg == null) {
            throw new ValidationException("battle.field.config.not.found");
        }

        if (bfg.getMaxLayerSize() + 1 > maxLayerSize) {
            throw new ValidationException("battle.field.max.layer.size.reached");
        }

        bfg.setMaxLayerSize(bfg.getMaxLayerSize() + 1);
        bfg.setLevel(bfg.getLevel() + 1);

        return pveBattleFieldConfigRepository.save(bfg);
    }

}
