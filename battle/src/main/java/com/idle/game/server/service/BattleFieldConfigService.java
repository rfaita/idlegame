package com.idle.game.server.service;

import com.idle.game.model.battle.BattleFieldConfig;
import com.idle.game.server.repository.BattleFieldConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class BattleFieldConfigService {

    @Autowired
    private BattleFieldConfigRepository battleFieldConfigRepository;

    public BattleFieldConfig findById(String id) {
        return battleFieldConfigRepository.findOne(id);
    }

}
