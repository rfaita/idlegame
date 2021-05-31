package com.idle.game.core.formation;

import com.idle.game.core.battle.BattleUnit;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.type.FormationAllocation;

import static com.idle.game.core.formation.type.FormationPosition.F_0;
import static com.idle.game.core.formation.type.FormationPosition.F_1;
import static com.idle.game.core.formation.type.FormationPosition.M_0;
import static com.idle.game.core.formation.type.FormationPosition.M_1;

import com.idle.game.core.formation.type.FormationPositionSize;
import com.idle.game.core.formation.type.FormationType;
import com.idle.game.core.hero.type.HeroSize;

import static com.idle.game.core.hero.type.HeroSize.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;

public class BattleFormation {

    private String id;
    private FormationAllocation formationAllocation;
    private FormationType formationType;
    private Integer size = 0;
    private final List<BattleUnit> heroes = new ArrayList<>(IdleConstants.MAX_SIZE_FORMATION);

    public BattleFormation() {
    }

    public BattleFormation(List<BattleUnit> heroes) {
        heroes.forEach(h -> this.addBattlePositionedHero(h));
    }

    public BattleFormation(BattleUnit[] heroes) {
        this(Arrays.asList(heroes));
    }

    public BattleFormation(FormationAllocation fa, List<BattleUnit> heroes) {
        this(heroes);
        this.formationAllocation = fa;
    }

    public BattleFormation(FormationAllocation fa, BattleUnit[] heroes) {
        this(heroes);
        this.formationAllocation = fa;
    }

    public void addBattlePositionedHero(BattleUnit bph) {

        HeroSize heroSize = bph.getUnit().getUnitType().getSize();
        FormationPosition fp = bph.getPosition();

        assert this.size + heroSize.size() <= IdleConstants.MAX_SIZE_FORMATION : "Formation max size reached, BUG";

        if (heroSize.equals(HeroSize.MEDIUM)) {
            if (!fp.equals(F_0)
                    && !fp.equals(F_1)
                    && !fp.equals(M_0)
                    && !fp.equals(M_1)) {
                throw new ValidationException("can.not.allocate.hero.here");
            }
        } else if (heroSize.equals(LARGE)) {
            if (!fp.equals(M_1)) {
                throw new ValidationException("can.not.allocate.hero.here");
            }
        }

        this.size += heroSize.size();
        if (heroSize.equals(SMALL)) {
            this.heroes.add(bph);
        } else {
            this.heroes.add(bph);
            FormationPositionSize.DATA.get(fp).get(heroSize).forEach((fpClone) -> {
                BattleUnit bphClone = bph.duplicate();
                bphClone.setClone(Boolean.TRUE);
                bphClone.setPosition(fpClone);
                this.heroes.add(bphClone);
            });
        }
    }

    public void removedBattlePositionedHero(FormationPosition p) {
        Optional<BattleUnit> ret = heroes.stream().filter(
                (ph) -> (ph.getPosition().equals(p))
        ).findFirst();
        if (ret.isPresent()) {
            this.size -= ret.get().getUnit().getUnitType().getSize().size();
            this.heroes.remove(ret.get());
        }
    }

    public List<BattleUnit> getHeroes() {
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
