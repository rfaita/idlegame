package com.idle.game.server.service;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.server.model.Hero;
import com.idle.game.server.model.Player;
import com.idle.game.server.util.PersistenceUnitHelper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
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
public class HeroService extends BaseService {

    @Inject
    private PersistenceUnitHelper helper;
    @Inject
    private Validator validator;
    @Inject
    private HeroTypeService heroTypeService;
    @Inject
    private PlayerService playerService;

    public Hero generateHero() {

        Hero h = new Hero();
        h.setLevel(1);

        Set<UUID> heroTypes = heroTypeService.getHeroTypes().keySet();

        if (!heroTypes.isEmpty()) {
            Optional<UUID> ret = heroTypes.stream().skip(DiceUtil.random(heroTypes.size())).findFirst();
            try {
                h.setHeroTypeId(ret.get().toString());
            } catch (NoSuchElementException e) {
                return null;
            }
        }

        h.setPlayer(playerService.findByLoggedLinkedUser());

        helper.getEntityManager().persist(h);
        helper.getEntityManager().flush();

        return findById(h.getId());

    }

    public Hero findById(Long id) {

        Query q = helper.getEntityManager().createNamedQuery("Hero.findById");

        q.setParameter("id", id);

        List<Hero> ret = q.getResultList();
        if (ret == null || ret.isEmpty()) {
            throw new ValidationException("hero.not.found");
        }

        return ret.get(0);

    }

    public List<Hero> findByPlayer(Long idPlayer) {
        Query q = helper.getEntityManager().createNamedQuery("Hero.findByPlayer");

        q.setParameter("idPlayer", idPlayer);

        return q.getResultList();

    }

    public List<Hero> findByLoggedLinkedUser() {
        return findByLinkedUser(getIDToken().getSubject());
    }

    public List<Hero> findByLinkedUser(String linkedUser) {
        Query q = helper.getEntityManager().createNamedQuery("Hero.findByLinkedUser");

        q.setParameter("linkedUser", linkedUser);

        return q.getResultList();

    }

    public Hero levelUp(Long id) {

        Hero h = findById(id);

        validateLevelUp(h);

        h.setLevel(h.getLevel() + 1);

        helper.getEntityManager().persist(h);
        return h;
    }

    private void validateLevelUp(Hero h) {
        if (h == null) {
            throw new ValidationException("hero.not.found");
        }
        if (!findByLoggedLinkedUser().contains(h)) {
            throw new ValidationException("player.is.not.owner.of.this.hero");
        }
        if (h.getLevel() + 1 > IdleConstants.HERO_MAX_LEVEL) {
            throw new ValidationException("hero.max.level.reached");
        }
    }

}
