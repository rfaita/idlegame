package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.model.battle.BattleField;
import com.idle.game.model.battle.BattleSite;
import com.idle.game.server.repository.BattleFieldRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class BattleFieldService {

    @Autowired
    private BattleFieldRepository battleFieldRepository;

    public BattleField create(BattleField battleField) {

        //battleField.validate(battleFieldConfig);
        return battleFieldRepository.save(battleField);

    }

    @Cacheable(value = BATTLE_FIELD_FIND_BY_ID, key = "'" + BATTLE_FIELD_FIND_BY_ID + "' + #id")
    public BattleField findById(String id) {
        return battleFieldRepository.findOne(id);
    }

    public List<BattleSite> showPath(String id, String formationId) {
        List<BattleSite> ret = new ArrayList<>();

        BattleField bf = findById(id);

        BattleSite site = bf.getSite(formationId);

        while (site != null) {
            ret.add(site);
            site = bf.getSite(site.getNextFormationId());

        }
        return ret;
    }

}
