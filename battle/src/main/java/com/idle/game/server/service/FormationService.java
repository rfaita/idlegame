package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_ID;
import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.core.formation.type.FormationPosition;
import static com.idle.game.core.formation.type.FormationPosition.F_0;
import static com.idle.game.core.formation.type.FormationPosition.F_1;
import static com.idle.game.core.formation.type.FormationPosition.M_0;
import static com.idle.game.core.formation.type.FormationPosition.M_1;
import com.idle.game.core.hero.type.HeroTypeSize;
import static com.idle.game.core.hero.type.HeroTypeSize.LARGE;
import com.idle.game.model.Formation;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
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
import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION;
import com.idle.game.helper.client.battle.BattleHeroClient;
import com.idle.game.helper.client.hero.HeroClient;
import com.idle.game.helper.client.hero.HeroTypeClient;
import java.util.Optional;

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
    private HeroClient heroClient;

    @Autowired
    private HeroTypeClient heroTypeClient;

    @Autowired
    private BattleHeroClient battleHeroClient;

    @Cacheable(value = FORMATION_FIND_BY_ID, key = "'" + FORMATION_FIND_BY_ID + "' + #id")
    public Formation findById(String id) {

        Optional<Formation> ret = formationRepository.findById(id);

        if (ret.isPresent()) {
            ret.get().getHeroes().forEach((h) -> {
                h.setHero(battleHeroClient.getBattleHero(h.getHero().getId()).getData());
            });

            return ret.get();
        } else {
            throw new ValidationException("formation.not.found");
        }
    }

    @Cacheable(value = FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION, key = "'" + FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION + "' + #userId + #fa")
    public Formation findByUserIdAndFormationAllocation(String userId, FormationAllocation fa) {

        Formation ret = formationRepository.findByUserIdAndFormationAllocation(userId, fa);

        ret.getHeroes().forEach((h) -> {
            h.setHero(battleHeroClient.getBattleHero(h.getHero().getId()).getData());
        });

        return ret;
    }

    private void validateSave(Formation f) {

        Set<ConstraintViolation<Formation>> violations = validator.validate(f);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(violations));
        }

        for (BattlePositionedHero ph : f.getHeroes()) {

            Hero hero = heroClient.findById(ph.getHero().getId()).getData();
            if (hero == null || !hero.getUserId().equals(f.getUserId())) {
                throw new ValidationException("user.is.not.owner.of.this.hero");
            }

            HeroType ht = heroTypeClient.findById(hero.getHeroTypeId()).getData();

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
        @CachePut(value = FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION,
                key = "'" + FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION + "' + #result.userId + #result.formationAllocation")
    })
    public Formation save(Formation f, String userId, Boolean admin) {

        f.setUserId(userId);

        if (!admin) {
            validateSave(f);
        }

        Formation fFind = formationRepository.findByUserIdAndFormationAllocation(f.getUserId(), f.getFormationAllocation());

        if (fFind != null) {
            f.setId(fFind.getId());
        }

        f = formationRepository.save(f);

        return findById(f.getId());

    }

}
