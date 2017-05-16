package com.idle.game.server.service;

import com.idle.game.server.model.Formation;
import com.idle.game.server.util.PersistenceUnitHelper;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

/**
 *
 * @author rafael
 */
@Stateless
public class FormationService {

    @Inject
    private PersistenceUnitHelper helper;
    @Inject
    private Validator validator;

    public Formation findById(Long id) {
        return helper.getEntityManager().find(Formation.class, id);
    }

}
