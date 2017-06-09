package com.idle.game.server.service;

import com.idle.game.core.type.FormationAllocation;
import com.idle.game.server.model.Formation;
import com.idle.game.server.model.Hero;
import com.idle.game.server.model.Player;
import com.idle.game.server.model.PositionedHero;
import com.idle.game.server.model.PvpRoll;
import com.idle.game.server.util.PersistenceUnitHelper;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

/**
 *
 * @author rafael
 */
@Stateless
public class FormationService extends BaseService {

    @Inject
    private PersistenceUnitHelper helper;
    @Inject
    private HeroService heroService;
    @Inject
    private PlayerService playerService;
    @Inject
    private Validator validator;

    public Formation findById(Long id) {
        Query q = helper.getEntityManager().createNamedQuery("Formation.findById");

        q.setParameter("id", id);

        List<Formation> ret = q.getResultList();
        if (ret == null || ret.isEmpty()) {
            throw new ValidationException("formation.not.found");
        }

        return ret.get(0);
    }

    public Formation save(Formation f) {

        validateSave(f);

        f.getHeroes().forEach(h -> {
            h.setFormation(f);
        });

        f.setPlayer(playerService.findByLoggedLinkedUser());

        if (f.getId() != null) {
            helper.getEntityManager().merge(f);
        } else {
            helper.getEntityManager().persist(f);
        }

        helper.getEntityManager().flush();

        return f;

    }

    public Formation findByLoggedLinkedUserAndAllocation(FormationAllocation formationAllocation) {
        return findByLinkedUserAndAllocation(getLoggedLinkedUser(), formationAllocation);
    }

    public Formation findByLinkedUserAndAllocation(String linkedUser, FormationAllocation formationAllocation) {
        Query q = helper.getEntityManager().createNamedQuery("Formation.findByLinkedUserAndAllocation");

        q.setParameter("linkedUser", linkedUser);
        q.setParameter("formationAllocation", formationAllocation);

        List<Formation> ret = q.getResultList();
        if (ret == null || ret.isEmpty()) {
            throw new ValidationException("formation.not.found");
        }
        if (ret.size() > 1) {
            throw new ValidationException("more.then.one.formation.found");
        }
        return ret.get(0);
    }

    public List<Formation> findByLoggedLinkedUser() {
        return findByLinkedUser(getLoggedLinkedUser());
    }

    public List<Formation> findByLinkedUser(String linkedUser) {
        Query q = helper.getEntityManager().createNamedQuery("Formation.findByLinkedUser");

        q.setParameter("linkedUser", linkedUser);

        return q.getResultList();

    }

    public Formation findByPlayerAndAllocation(Long idPlayer, FormationAllocation formationAllocation) {
        Query q = helper.getEntityManager().createNamedQuery("Formation.findByPlayerAndAllocation");

        q.setParameter("idPlayer", idPlayer);
        q.setParameter("formationAllocation", formationAllocation);

        List<Formation> ret = q.getResultList();

        if (ret != null & !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }

    }

    public PvpRoll pvpRoll() {

        Player player = playerService.findByLoggedLinkedUser();

        if (player.getLastPvpRoll() == null || Instant.now().isAfter(player.getLastPvpRoll().getExpireDate().toInstant())) {
            Player pHigher = playerService.findHigherScorePvpByLoggedLinked();
            Player pLower = playerService.findLowerScorePvpByLoggedLinked();
            Player pRandom = playerService.findRandomScorePvpByLoggedLinked(Arrays.asList(pHigher.getId(), pLower.getId()));

            Formation fHigher = findByPlayerAndAllocation(pHigher.getId(), FormationAllocation.PVP_DEFENSE);
            Formation fLower = findByPlayerAndAllocation(pLower.getId(), FormationAllocation.PVP_DEFENSE);
            Formation fRandom = findByPlayerAndAllocation(pRandom.getId(), FormationAllocation.PVP_DEFENSE);

            player = playerService.refreshLastPvpRoll(player, fHigher, fLower, fRandom);

        }
        return player.getLastPvpRoll();

    }

    private void validateSave(Formation f) {

        Set<ConstraintViolation<Formation>> violations = validator.validate(f);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(violations));
        }

        List<Hero> heroes = heroService.findByLoggedLinkedUser();

        for (PositionedHero ph : f.getHeroes()) {
            if (!heroes.contains(ph.getHero())) {
                throw new ValidationException("player.is.not.owner.of.this.hero");
            }
        }

    }

}
