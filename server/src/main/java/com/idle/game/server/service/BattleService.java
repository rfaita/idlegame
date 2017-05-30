package com.idle.game.server.service;

import com.idle.game.core.Battle;
import com.idle.game.core.type.BattleTeamType;
import com.idle.game.core.type.FormationAllocation;
import com.idle.game.core.type.FormationType;
import com.idle.game.core.type.HeroType;
import com.idle.game.server.model.Formation;
import java.util.Map;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ValidationException;
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
    private PlayerService playerService;
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

    public Battle doBattle(String linkedUserDefense, FormationAllocation formationAllocation) throws Exception {

        Formation attFormation = formationService.findByLoggedLinkedUserAndAllocation(formationAllocation);
        Formation defFormation = formationService.findByLinkedUserAndAllocation(linkedUserDefense, formationAllocation.getCounter());

        Map<UUID, HeroType> heroTypes = heroTypeService.getHeroTypes();

        Battle b = new Battle(attFormation.toFormation(heroTypes), defFormation.toFormation(heroTypes));
        b.doBattle();
        return b;
    }

    public Battle doPveBattle(FormationAllocation formationAllocation) throws Exception {

        Formation attFormation = formationService.findByLoggedLinkedUserAndAllocation(formationAllocation);
        Formation defFormation = attFormation.getPlayer().getNextLevelFormationPve();

        if (defFormation.getPlayer() != null) {
            throw new ValidationException("this.formation.is.not.for.pve");
        }

        Map<UUID, HeroType> heroTypes = heroTypeService.getHeroTypes();

        Battle b = new Battle(attFormation.toFormation(heroTypes), defFormation.toFormation(heroTypes));
        b.doBattle();

        if (b.getWinner().getFormationType().equals(FormationType.ATTACK)) {
            playerService.updateToNextLevelFormationPve();
        }

        return b;
    }

}
