package com.idle.game.server.service;

import com.idle.game.server.model.Player;
import com.idle.game.server.util.PersistenceUnitHelper;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

/**
 *
 * @author rafael
 */
@Stateless
public class PlayerService {

    @Inject
    private PersistenceUnitHelper helper;
    @Inject
    private Validator validator;

    public Player findById(Long id) {
        Player p = helper.getEntityManager().find(Player.class, id);
        p.getFormations().size();
        return p;
    }

}
