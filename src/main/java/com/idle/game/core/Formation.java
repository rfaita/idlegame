package com.idle.game.core;

import com.idle.game.core.exception.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author rafael
 */
public class Formation extends BaseObject {

    private final List<PositionedHero> heroes = new ArrayList<>(IdleConstants.MAX_SIZE_BACK_LINE + IdleConstants.MAX_SIZE_FRONT_LINE);

    public Formation() {
    }

    public Formation(PositionedHero[] heroes) {
        this.heroes.addAll(Arrays.asList(heroes));
    }

    public void addPositionedHero(PositionedHero hero) throws Exception {
        if (heroes.stream().filter((ph) -> (ph.getBattlePosition().equals(hero.getBattlePosition()))).count() > 0) {
            throw new ValidationException("can.not.add.hero.to.this.position");
        }
        this.heroes.add(hero);
    }

    public void removedPositionedHero(BattlePositionType p) {
        Optional<PositionedHero> ret = heroes.stream().filter(
                (ph) -> (ph.getBattlePosition().equals(p))
        ).findFirst();
        if (ret.isPresent()) {
            this.heroes.remove(ret.get());
        }
    }

    public List<PositionedHero> getBackLinePositionedHeroes() {
        return heroes.stream().filter((ph) -> {
            System.out.println(ph);
            return ph.getBattlePosition().equals(BattlePositionType.FRONT_BOTTOM)
                    || ph.getBattlePosition().equals(BattlePositionType.FRONT_TOP);
        }
        ).collect(Collectors.toList());

    }

    public List<PositionedHero> getFrontLinePositionedHeroes() {
        return heroes.stream().filter((ph) -> {
            return (ph.getBattlePosition().equals(BattlePositionType.BACK_0)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_1)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_2)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_3));
        }).collect(Collectors.toList());
    }

    public List<PositionedHero> getPositionedHeroes() {
        return heroes;
    }

}
