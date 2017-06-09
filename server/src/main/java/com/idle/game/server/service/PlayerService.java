package com.idle.game.server.service;

import com.idle.game.server.model.Formation;
import com.idle.game.server.model.Player;
import com.idle.game.server.model.PvpRoll;
import com.idle.game.server.model.Reward;
import com.idle.game.server.util.PersistenceUnitHelper;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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
    
    public Player refreshLastPvpRoll(Player player, Formation fHigher, Formation fLower, Formation fRandom) {
        
        if (player.getLastPvpRoll() != null) {
            helper.getEntityManager().remove(player.getLastPvpRoll());
        }
        
        PvpRoll lastPvpRoll = new PvpRoll();
        lastPvpRoll.setFormationHigher(fHigher);
        lastPvpRoll.setFormationLower(fLower);
        lastPvpRoll.setFormationRandom(fRandom);
        
        lastPvpRoll.setExpireDate(Date.from(Instant.now().plus(Duration.ofMinutes(10L))));
        
        helper.getEntityManager().persist(lastPvpRoll);
        player.setLastPvpRoll(lastPvpRoll);
        helper.getEntityManager().merge(player);
        
        return player;
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
    
    public void updateToNextLevelFormationPve() {
        
        Player p = this.findByLoggedLinkedUser();
        Formation f = p.getNextLevelFormationPve();
        p.setNextLevelFormationPve(f.getNextLevelFormation());
        if (f.getReward() != null) {
            computeResources();
            Reward r = f.getReward();
            p.setGoldPerSecond(p.getGoldPerSecond() + r.getGoldPerSecond());
            p.setAncientRune(p.getAncientRune() + r.getAncientRune());
            p.setAncientRunePerSecond(p.getAncientRunePerSecond() + r.getAncientRunePerSecond());
            p.setGold(p.getGold() + r.getGold());
            p.setGoldPerSecond(p.getGoldPerSecond() + r.getGoldPerSecond());
            p.setSoul(p.getSoul() + r.getSoul());
            p.setSoulPerSecond(p.getSoulPerSecond() + r.getSoulPerSecond());
            p.setSpiritCrystal(p.getSpiritCrystal() + r.getSpiritCrystal());
            p.setSpiritCrystalPerSecond(p.getSpiritCrystalPerSecond() + r.getSpiritCrystalPerSecond());
        }
        
        helper.getEntityManager().merge(p);
    }
    
    public Player computeResources() {
        
        long seconds = 0l;
        
        Player p = this.findByLoggedLinkedUser();
        
        if (p.getLastTimeResourcesCollected() != null) {
            LocalDateTime lastTimeResourcesCollected = LocalDateTime.ofInstant(p.getLastTimeResourcesCollected().toInstant(), ZoneId.systemDefault());
            
            seconds = lastTimeResourcesCollected.until(LocalDateTime.now(), ChronoUnit.SECONDS);
            
            p.setAncientRune(p.getAncientRune() + (seconds * p.getAncientRunePerSecond()));
            p.setGold(p.getGold() + (seconds * p.getGoldPerSecond()));
            p.setSoul(p.getSoul() + (seconds * p.getSoulPerSecond()));
            p.setSpiritCrystal(p.getSpiritCrystal() + (seconds * p.getSpiritCrystalPerSecond()));
            
        }
        
        if (p.getLastTimeResourcesCollected() == null || seconds > 0) {
            p.setLastTimeResourcesCollected(new Date());
            helper.getEntityManager().merge(p);
        }
        
        return p;
        
    }
    
    public Player findRandomScorePvpByLoggedLinked(List<Long> ids) {
        
        Query q = helper.getEntityManager().createNativeQuery(
                "  SELECT * FROM player WHERE linkedUser <> :linkedUser AND id NOT IN (:ids) ORDER BY rand() LIMIT 0,1", Player.class);
        
        q.setParameter("linkedUser", getLoggedLinkedUser());
        q.setParameter("ids", ids);
        
        List<Player> ret = q.getResultList();
        
        if (ret != null & !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }
    }
    
    public Player findHigherScorePvpByLoggedLinked() {
        
        Query q = helper.getEntityManager().createNativeQuery(
                "SELECT * FROM ( "
                + "\n   SELECT * FROM player opponent WHERE pvpScore >= ( "
                + "\n       SELECT pvpScore FROM player p WHERE p.linkedUser = :linkedUser "
                + "\n   )  "
                + "\n   AND opponent.linkedUser <> :linkedUser "
                + "\n   ORDER BY opponent.pvpScore DESC "
                + "\n   LIMIT 0, 5 "
                + "\n ) result "
                + "\n ORDER BY rand() "
                + "\n LIMIT 0,1", Player.class);
        
        q.setParameter("linkedUser", getLoggedLinkedUser());
        
        List<Player> ret = q.getResultList();
        
        if (ret != null & !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }
    }
    
    public Player findLowerScorePvpByLoggedLinked() {
        
        Query q = helper.getEntityManager().createNativeQuery(
                "SELECT * FROM ( "
                + "\n   SELECT * FROM player opponent WHERE pvpScore <= ( "
                + "\n       SELECT pvpScore FROM player p WHERE p.linkedUser = :linkedUser "
                + "\n   )  "
                + "\n   AND opponent.linkedUser <> :linkedUser "
                + "\n   ORDER BY opponent.pvpScore "
                + "\n   LIMIT 0, 5 "
                + "\n ) result "
                + "\n ORDER BY rand() "
                + "\n LIMIT 0,1", Player.class);
        
        q.setParameter("linkedUser", getLoggedLinkedUser());
        
        List<Player> ret = q.getResultList();
        
        if (ret != null & !ret.isEmpty()) {
            return ret.get(0);
        } else {
            return null;
        }
    }
    
}
