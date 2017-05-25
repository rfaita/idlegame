package com.idle.game.core;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.type.FormationType;
import com.idle.game.core.type.BattlePositionType;
import com.idle.game.core.exception.ValidationException;
import com.idle.game.core.type.FormationAllocation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author rafael
 */
public class Formation implements Serializable {

    private Long id;
    private FormationAllocation formationAllocation;
    private FormationType formationType;
    private Integer size = 0;
    private final List<PositionedHero> heroes = new ArrayList<>(IdleConstants.MAX_SIZE_BACK_LINE + IdleConstants.MAX_SIZE_FRONT_LINE);

    public Formation() {
    }

    public Formation(List<PositionedHero> heroes) {
        this.heroes.addAll(heroes);
        this.size = this.heroes.size();
    }

    public Formation(PositionedHero[] heroes) {
        this.heroes.addAll(Arrays.asList(heroes));
        this.size = this.heroes.size();
    }

    public Formation(FormationAllocation fa, List<PositionedHero> heroes) {
        this.formationAllocation = fa;
        this.heroes.addAll(heroes);
        this.size = this.heroes.size();
    }

    public Formation(FormationAllocation fa, PositionedHero[] heroes) {
        this.formationAllocation = fa;
        this.heroes.addAll(Arrays.asList(heroes));
        this.size = this.heroes.size();
    }

    public void addPositionedHero(PositionedHero hero) throws Exception {
        if (this.size + hero.getHero().getHeroType().getSize() > IdleConstants.MAX_SIZE_BACK_LINE + IdleConstants.MAX_SIZE_FRONT_LINE) {
            throw new ValidationException("can.not.add.hero.maximum.size.reached");
        }
        if (heroes.stream().filter((ph) -> (ph.getBattlePosition().equals(hero.getBattlePosition()))).count() > 0) {
            throw new ValidationException("can.not.add.hero.to.this.position");
        }
        this.size += hero.getHero().getHeroType().getSize();
        this.heroes.add(hero);
    }

    public void removedPositionedHero(BattlePositionType p) {
        Optional<PositionedHero> ret = heroes.stream().filter(
                (ph) -> (ph.getBattlePosition().equals(p))
        ).findFirst();
        if (ret.isPresent()) {
            this.size -= ret.get().getHero().getHeroType().getSize();
            this.heroes.remove(ret.get());
        }
    }

    public List<PositionedHero> returnBackLinePositionedHeroes() {
        return heroes.stream().filter((ph) -> {
            return ph.getBattlePosition().equals(BattlePositionType.FRONT_BOTTOM)
                    || ph.getBattlePosition().equals(BattlePositionType.FRONT_MIDDLE)
                    || ph.getBattlePosition().equals(BattlePositionType.FRONT_TOP);
        }).collect(Collectors.toList());

    }

    public List<PositionedHero> returnFrontLinePositionedHeroes() {
        return heroes.stream().filter((ph) -> {
            return (ph.getBattlePosition().equals(BattlePositionType.BACK_BOTTOM)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_MIDDLE)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_TOP));
        }).collect(Collectors.toList());
    }

    public List<PositionedHero> getPositionedHeroes() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
