package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION;
import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.helper.BattleHeroHelper;
import com.idle.game.helper.HeroHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.Formation;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.Player;
import com.idle.game.server.repository.FormationRepository;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private HeroHelper heroHelper;

    @Autowired
    private BattleHeroHelper battleHeroHelper;

    @Autowired
    private PlayerHelper playerHelper;

    @Cacheable(value = FORMATION_FIND_BY_ID, key = "'" + FORMATION_FIND_BY_ID + "' + #id")
    public Formation findById(String id) {

        Formation ret = formationRepository.findById(id);

        ret.getHeroes().forEach((h) -> {
            h.setHero(battleHeroHelper.getBattleHeroById(h.getHero().getId()));
        });

        return ret;
    }

    public Formation findByUserAndFormationAllocation(String user, FormationAllocation fa) {

        Player player = playerHelper.getPlayerById(user);

        if (player != null) {
            return findByPlayerAndFormationAllocation(player.getId(), fa);
        } else {
            throw new ValidationException("player.not.found");
        }
    }

    @Cacheable(value = FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION, key = "'" + FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION + "' + #player + #fa")
    public Formation findByPlayerAndFormationAllocation(String player, FormationAllocation fa) {

        Formation ret = formationRepository.findByPlayerAndFormationAllocation(player, fa);

        ret.getHeroes().forEach((h) -> {
            h.setHero(battleHeroHelper.getBattleHeroById(h.getHero().getId()));
        });

        return ret;
    }

    private void validateSave(Formation f) {

        Set<ConstraintViolation<Formation>> violations = validator.validate(f);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(violations));
        }

        for (BattlePositionedHero ph : f.getHeroes()) {

            Hero hero = heroHelper.getHeroById(ph.getHero().getId());
            if (hero == null || !hero.getPlayer().equals(f.getPlayer())) {
                throw new ValidationException("player.is.not.owner.of.this.hero");
            }
        }

    }

    @Caching(put = {
        @CachePut(value = FORMATION_FIND_BY_ID,
                key = "'" + FORMATION_FIND_BY_ID + "' + #result.id")
        ,
        @CachePut(value = FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION,
                key = "'" + FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION + "' + #result.player + #result.formationAllocation")
    })
    public Formation save(Formation f, String user) {

        Player player = playerHelper.getPlayerByLinkedUser(user);

        if (player != null) {

            f.setPlayer(player.getId());

            validateSave(f);

            Formation fFind = formationRepository.findByPlayerAndFormationAllocation(f.getPlayer(), f.getFormationAllocation());

            if (fFind != null) {
                f.setId(fFind.getId());
            }

            f = formationRepository.save(f);

            return findById(f.getId());
        } else {
            throw new ValidationException("player.not.found");
        }

    }

}
