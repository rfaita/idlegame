package com.idle.game.server.service;

import com.idle.game.core.Battle;
import com.idle.game.server.model.Formation;
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
        attFormation.getHeroes().forEach(ph -> {
            ph.getHero().setHeroType(heroTypeService.getHeroType(ph.getHero().getHeroTypeId()));
        });

        Formation defFormation = formationService.findById(idDefenseFormation);
        defFormation.getHeroes().forEach(ph -> {
            ph.getHero().setHeroType(heroTypeService.getHeroType(ph.getHero().getHeroTypeId()));
        });

        Battle b = new Battle(attFormation.toFormation(), defFormation.toFormation());
        b.doBattle();
        return b;
    }

}
