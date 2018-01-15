package com.idle.game.server.service;

import com.idle.game.core.formation.PositionedHero;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.model.mongo.Formation;
import com.idle.game.model.mongo.Hero;
import com.idle.game.server.repository.FormationRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
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
    private HeroTypeHelper heroTypeHelper;
    

    private void validateSave(Formation f) {

        Set<ConstraintViolation<Formation>> violations = validator.validate(f);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(violations));
        }

        List<Hero> heroes = heroTypeHelper.getHeroByPlayer(f.getPlayer());

        for (PositionedHero ph : f.getHeroes()) {
            if (!heroes.contains(ph.getHero())) {
                throw new ValidationException("player.is.not.owner.of.this.hero");
            }
        }

    }

    public Formation save(Formation f) {

        validateSave(f);

        Formation fFind = formationRepository.findByPlayerAndFormationAllocation(f.getPlayer(), f.getFormationAllocation());

        if (fFind != null) {
            f.setId(fFind.getId());
        }

        return formationRepository.save(f);

    }

}
