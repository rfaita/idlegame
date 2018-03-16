package com.idle.game.helper;

import com.idle.game.core.hero.BattleHero;
import com.idle.game.helper.cb.BattleHeroCircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class BattleHeroHelper {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private BattleHeroCircuitBreakerService battleHeroCircuitBreakerService;

    public BattleHero getBattleHeroById(String id) {
        return battleHeroCircuitBreakerService.getBattleHeroById(id, tokenHelper.getToken());
    }

}
