package com.idle.game.server.service;

import com.idle.game.type.FormationAllocation;
import com.idle.game.server.model.Formation;
import com.idle.game.server.model.Hero;
import com.idle.game.server.model.Player;
import com.idle.game.server.model.PositionedHero;
import com.idle.game.server.model.PvpRoll;
import com.idle.game.server.util.PersistenceUnitHelper;
import com.idle.game.server.util.PriceEnum;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
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
import org.infinispan.Cache;

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
    @Inject
    private Cache<String, PvpRoll> cachePvpRoll;
    
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
        
        Formation fFind = findByLoggedLinkedUserAndAllocation(f.getFormationAllocation());
        
        if (fFind != null) {
            f.setId(fFind.getId());
        }
        
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
        return pvpRoll(Boolean.FALSE);
    }
    
    public PvpRoll pvpRoll(Boolean paid) {
        
        PvpRoll pvpRoll = cachePvpRoll.get(getLoggedLinkedUser());
        
        if (paid) {
            Player p = playerService.findByLoggedLinkedUser();
            
            if (!PriceEnum.PAID_PVP_ROLL.playerCanPay(p)) {
                throw new ValidationException("not.enough.spirit.crystal");
            }
            
            helper.getEntityManager().merge(PriceEnum.PAID_PVP_ROLL.pay(p));
        }
        
        if (pvpRoll == null || paid || Instant.now().isAfter(pvpRoll.getExpireDate().toInstant())) {
            Player pHigher = playerService.findHigherScorePvpByLoggedLinked();
            Player pLower = playerService.findLowerScorePvpByLoggedLinked();
            Player pRandom = playerService.findRandomScorePvpByLoggedLinked(Arrays.asList(pHigher.getId(), pLower.getId()));
            
            Formation fHigher = findByPlayerAndAllocation(pHigher.getId(), FormationAllocation.PVP_DEFENSE);
            Formation fLower = findByPlayerAndAllocation(pLower.getId(), FormationAllocation.PVP_DEFENSE);
            Formation fRandom = findByPlayerAndAllocation(pRandom.getId(), FormationAllocation.PVP_DEFENSE);
            
            pvpRoll = new PvpRoll();
            pvpRoll.setFormationHigher(fHigher);
            pvpRoll.setFormationLower(fLower);
            pvpRoll.setFormationRandom(fRandom);
            
            pvpRoll.setNamePlayerHigher(pHigher.getName());
            pvpRoll.setNamePlayerLower(pLower.getName());
            pvpRoll.setNamePlayerRandom(pRandom.getName());
            
            pvpRoll.setPvpScoreHigher(pHigher.getPvpScore());
            pvpRoll.setPvpScoreLower(pLower.getPvpScore());
            pvpRoll.setPvpScoreRandom(pRandom.getPvpScore());
            
            pvpRoll.setExpireDate(Date.from(Instant.now().plus(Duration.ofMinutes(10L))));
            
            cachePvpRoll.put(getLoggedLinkedUser(), pvpRoll);
            
        }
        
        return cachePvpRoll.get(getLoggedLinkedUser());
        
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
