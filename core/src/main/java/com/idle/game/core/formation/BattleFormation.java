package com.idle.game.core.formation;

import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.core.formation.type.FormationType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        this.heroes.addAll(heroes);
        this.size = this.heroes.size();
    }

    public BattleFormation(BattlePositionedHero[] heroes) {
        this.heroes.addAll(Arrays.asList(heroes));
        this.size = this.heroes.size();
    }

    public BattleFormation(FormationAllocation fa, List<BattlePositionedHero> heroes) {
        this.formationAllocation = fa;
        this.heroes.addAll(heroes);
        this.size = this.heroes.size();
    }

    public BattleFormation(FormationAllocation fa, BattlePositionedHero[] heroes) {
        this.formationAllocation = fa;
        this.heroes.addAll(Arrays.asList(heroes));
        this.size = this.heroes.size();
    }

    public void addBattlePositionedHero(BattlePositionedHero hero) {

        assert this.size + 1 <= IdleConstants.MAX_SIZE_FORMATION : "Formation max size reached, BUG";
        this.size++;
        this.heroes.add(hero);
    }

    public void removedBattlePositionedHero(FormationPosition p) {
        Optional<BattlePositionedHero> ret = heroes.stream().filter(
                (ph) -> (ph.getPosition().equals(p))
        ).findFirst();
        if (ret.isPresent()) {
            this.size--;
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

    public void setSize(Integer size) {
        this.size = size;
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
