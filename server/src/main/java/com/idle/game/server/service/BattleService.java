package com.idle.game.server.service;

import com.idle.game.core.Battle;
import com.idle.game.core.type.HeroType;
import com.idle.game.server.model.Formation;
import java.util.Map;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

/**
 *
 * @author rafael
 */
@Stateless
public class BattleService {

    @Inject
    private FormationService formationService;
    @Inject
    private HeroTypeService heroTypeService;
    @Inject
    private Validator validator;

    public Battle doBattle(Long idAttackFormation, Long idDefenseFormation) throws Exception {

        Formation attFormation = formationService.findById(idAttackFormation);
        Formation defFormation = formationService.findById(idDefenseFormation);

        Map<UUID, HeroType> heroTypes = heroTypeService.getHeroTypes();
        
        Battle b = new Battle(attFormation.toFormation(heroTypes), defFormation.toFormation(heroTypes));
        b.doBattle();
        return b;
    }

}
