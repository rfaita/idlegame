package com.idle.game.server.service;

import com.idle.game.server.model.Hero;
import com.idle.game.server.util.PersistenceUnitHelper;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

/**
 *
 * @author rafael
 */
@Stateless
public class HeroService {

    @Inject
    private HeroTypeService heroTypeService;
    @Inject
    private PersistenceUnitHelper helper;
    @Inject
    private Validator validator;

    public Hero findById(Long id) {
        Hero h = helper.getEntityManager().find(Hero.class, id);
        h.setHeroType(heroTypeService.getHeroType(h.getHeroTypeId()));
        return h;
    }

    public Hero levelUp(Long id) {
        Hero h = findById(id);
        h.setLevel(h.getLevel() + 1);

        helper.getEntityManager().persist(h);
        return h;
    }

}
