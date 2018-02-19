package com.idle.game.server.service;

import com.idle.game.core.battle.Battle;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.formation.type.FormationType;
import com.idle.game.helper.FormationHelper;
import com.idle.game.model.mongo.BattleHistory;
import com.idle.game.model.mongo.Formation;
import com.idle.game.server.repository.BattleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class BattleService {
    
    @Autowired
    private FormationHelper formationHelper;
    
    @Autowired
    private BattleHistoryRepository battleHistoryRepository;
    
    public Battle doBattle(String idAttackFormation, String idDefenseFormation) {
        
        Formation attFormation = formationHelper.getFormationById(idAttackFormation);
        Formation defFormation = formationHelper.getFormationById(idDefenseFormation);
        
        return doBattle(attFormation, defFormation);
    }
    
     public Battle doBattle(Formation attFormation, Formation defFormation) {
        
        BattleFormation battleAttackFormation = new BattleFormation(attFormation.getHeroes());
        battleAttackFormation.setFormationType(FormationType.ATTACK);
        
        BattleFormation battleDefenseFormation = new BattleFormation(defFormation.getHeroes());
        battleDefenseFormation.setFormationType(FormationType.DEFENSE);
        
        Battle b = new Battle(battleAttackFormation, battleDefenseFormation);
        b.doBattle();
        
        battleHistoryRepository.save(BattleHistory.getBattleHistory(b));
        
        return b;
    }
}
