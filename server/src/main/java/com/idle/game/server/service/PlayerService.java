package com.idle.game.server.service;

import com.idle.game.server.model.Player;
import com.idle.game.server.util.PersistenceUnitHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.validation.ValidationException;
import javax.validation.Validator;

/**
 *
 * @author rafael
 */
@Stateless
public class PlayerService extends BaseService {

    @Inject
    private PersistenceUnitHelper helper;
    @Inject
    private Validator validator;

    public Player findById(Long id) {
        Player p = helper.getEntityManager().find(Player.class, id);
        p.getFormations().size();
        return p;
    }

    public Player findByLoggedLinkedUser() {
        return findByLinkedUser(getLoggedLinkedUser());
    }

    public Player findByLinkedUser(String linkedUser) {
        Query q = helper.getEntityManager().createNamedQuery("Player.findByLinkedUser");

        q.setParameter("linkedUser", linkedUser);

        List<Player> ret = q.getResultList();
        if (ret == null || ret.isEmpty()) {
            throw new ValidationException("player.not.found");
        }

        return ret.get(0);

    }

}
