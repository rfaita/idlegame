package com.idle.game.server.util;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rafael
 */
@ApplicationScoped
public class PersistenceUnitHelper {

    @PersistenceContext(name = "PU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }
}
