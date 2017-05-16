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
    private PersistenceUnitHelper helper;
    @Inject
    private Validator validator;

    public Hero findById(Long id) {
        return helper.getEntityManager().find(Hero.class, id);
    }

}
