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
    private Validator validator;

    public Battle doBattle(Formation attFormation, Formation defFormation) throws Exception {

        Battle b = new Battle(attFormation.toFormation(), defFormation.toFormation());
        b.doBattle();
        return b;
    }

}
