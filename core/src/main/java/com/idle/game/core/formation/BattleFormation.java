package com.idle.game.core.formation;

import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.type.FormationAllocation;
import static com.idle.game.core.formation.type.FormationPosition.F_0;
import static com.idle.game.core.formation.type.FormationPosition.F_1;
import static com.idle.game.core.formation.type.FormationPosition.M_0;
import static com.idle.game.core.formation.type.FormationPosition.M_1;
import com.idle.game.core.formation.type.FormationPositionRelation;
import com.idle.game.core.formation.type.FormationType;
import com.idle.game.core.hero.type.HeroTypeSize;
import static com.idle.game.core.hero.type.HeroTypeSize.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;

/**
 *
 * @author rafael
 */
public class BattleFormation implements Serializable {

    private String id;
    private FormationAllocation formationAllocation;
    private FormationType formationType;
    private Integer size = 0;
    private final List<BattlePositionedHero> heroes = new ArrayList<>(IdleConstants.MAX_SIZE_FORMATION);

    public BattleFormation() {
    }

    public BattleFormation(List<BattlePositionedHero> heroes) {
        heroes.forEach((h) -> {
            this.addBattlePositionedHero(h);
        });
    }

    public BattleFormation(BattlePositionedHero[] heroes) {
        Arrays.asList(heroes).forEach((h) -> {
            this.addBattlePositionedHero(h);
        });
    }

    public BattleFormation(FormationAllocation fa, List<BattlePositionedHero> heroes) {
        this.formationAllocation = fa;
        heroes.forEach((h) -> {
            this.addBattlePositionedHero(h);
        });
    }

    public BattleFormation(FormationAllocation fa, BattlePositionedHero[] heroes) {
        this.formationAllocation = fa;
        Arrays.asList(heroes).forEach((h) -> {
            this.addBattlePositionedHero(h);
        });
    }

    public void addBattlePositionedHero(BattlePositionedHero bph) {

        HeroTypeSize hts = bph.getHero().getHeroType().getSize();
        FormationPosition fp = bph.getPosition();

        assert this.size + hts.size() <= IdleConstants.MAX_SIZE_FORMATION : "Formation max size reached, BUG";

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

        this.size += hts.size();
        if (hts.equals(SMALL)) {
            this.heroes.add(bph);

        } else {
            this.heroes.add(bph);
            FormationPositionRelation.DATA.get(fp).get(hts).forEach((fpClone) -> {
                BattlePositionedHero bphClone = bph.duplicate();
                bphClone.setPosition(fpClone);
                this.heroes.add(bphClone);
            });
        }
    }

    public void removedBattlePositionedHero(FormationPosition p) {
        Optional<BattlePositionedHero> ret = heroes.stream().filter(
                (ph) -> (ph.getPosition().equals(p))
        ).findFirst();
        if (ret.isPresent()) {
            this.size -= ret.get().getHero().getHeroType().getSize().size();
            this.heroes.remove(ret.get());
        }
    }

    public List<BattlePositionedHero> getHeroes() {
        return heroes;
    }

    public FormationType getFormationType() {
        return formationType;
    }

    public void setFormationType(FormationType formationType) {
        this.formationType = formationType;
    }

    public Integer getSize() {
        return size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FormationAllocation getFormationAllocation() {
        return formationAllocation;
    }

    public void setFormationAllocation(FormationAllocation formationAllocation) {
        this.formationAllocation = formationAllocation;
    }

    @Override
    public String toString() {
        return "F{" + "ft=" + formationType + ", size=" + size + ", fa=" + formationAllocation + '}';
    }

}
