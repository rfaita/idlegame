package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION;
import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.core.formation.type.FormationPosition;
import static com.idle.game.core.formation.type.FormationPosition.F_0;
import static com.idle.game.core.formation.type.FormationPosition.F_1;
import static com.idle.game.core.formation.type.FormationPosition.M_0;
import static com.idle.game.core.formation.type.FormationPosition.M_1;
import com.idle.game.core.hero.type.HeroTypeSize;
import static com.idle.game.core.hero.type.HeroTypeSize.LARGE;
import com.idle.game.helper.BattleHeroHelper;
import com.idle.game.helper.HeroHelper;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.Formation;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import com.idle.game.model.Player;
import com.idle.game.server.repository.FormationRepository;
import java.util.HashSet;
import java.util.Optional;
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
    private HeroTypeHelper heroTypeHelper;

    @Autowired
    private BattleHeroHelper battleHeroHelper;

    @Autowired
    private PlayerHelper playerHelper;

    @Cacheable(value = FORMATION_FIND_BY_ID, key = "'" + FORMATION_FIND_BY_ID + "' + #id")
    public Formation findById(String id) {

        Formation ret = formationRepository.findOne(id);

        if (ret != null) {
            ret.getHeroes().forEach((h) -> {
                h.setHero(battleHeroHelper.getBattleHeroById(h.getHero().getId()));
            });

            return ret;
        } else {
            throw new ValidationException("formation.not.found");
        }
    }

    public Formation findByUserAndFormationAllocation(String user, FormationAllocation fa) {

        Player player = playerHelper.getPlayerByLinkedUser(user);

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
            if (hero == null || !hero.getPlayerId().equals(f.getPlayer())) {
                throw new ValidationException("player.is.not.owner.of.this.hero");
            }

            HeroType ht = heroTypeHelper.getHeroTypeById(hero.getHeroTypeId());

            if (ht != null) {
                HeroTypeSize hts = ht.getSize();

                FormationPosition fp = ph.getPosition();

                if (hts.equals(HeroTypeSize.MEDIUM)) {
                    if (!fp.equals(F_0)
                            && !fp.equals(F_1)
                            && !fp.equals(M_0)
                            && !fp.equals(M_1)) {
                        throw new ValidationException("can.not.allocate.hero.here");
                    }
                } else if (hts.equals(LARGE)) {
                    if (!fp.equals(M_1)) {
                        throw new ValidationException("can.not.allocate.hero.here");
                    }
                }
            } else {
                throw new ValidationException("hero.type.not.found");
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
    public Formation save(Formation f, String user, Boolean admin) {

        Player player = playerHelper.getPlayerByLinkedUser(user);

        if (player != null) {

            f.setPlayer(player.getId());

            if (!admin) {
                validateSave(f);
            }

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
