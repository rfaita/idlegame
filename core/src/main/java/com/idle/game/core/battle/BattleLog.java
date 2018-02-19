package com.idle.game.core.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class BattleLog implements Serializable {

    private Integer turn;
    private BattlePositionedHero heroOrigin;
    private BattleEvent battleEvent;
    private List<BattlePositionedHero> heroesTarget;

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public BattlePositionedHero getHeroOrigin() {
        return heroOrigin;
    }

    public void setHeroOrigin(BattlePositionedHero heroOrigin) {
        this.heroOrigin = heroOrigin;
    }

    public BattleEvent getBattleEvent() {
        return battleEvent;
    }

    public void setBattleEvent(BattleEvent battleEvent) {
        this.battleEvent = battleEvent;
    }

    public List<BattlePositionedHero> getHeroesTarget() {
        return heroesTarget;
    }

    public void setHeroesTarget(List<BattlePositionedHero> heroesTarget) {
        this.heroesTarget = heroesTarget;
    }

    public BattleLog() {
    }

    public BattleLog(Integer turn, BattleEvent battleEvent) {
        this.turn = turn;
        this.battleEvent = battleEvent;
    }

    public BattleLog(Integer turn, BattlePositionedHero heroOrigin, BattleEvent battleEvent, BattlePositionedHero heroTarget) {
        this.turn = turn;
        this.heroOrigin = heroOrigin;
        this.battleEvent = battleEvent;
        this.heroesTarget = new ArrayList<>();
        this.heroesTarget.add(heroTarget);
    }

    public BattleLog(Integer turn, BattlePositionedHero heroOrigin, BattleEvent battleEvent, List<BattlePositionedHero> heroesTarget) {
        this.turn = turn;
        this.heroOrigin = heroOrigin;
        this.battleEvent = battleEvent;
        this.heroesTarget = heroesTarget;
    }

    @Override
    public String toString() {
        return "BL{turn=" + turn + ", ho=" + heroOrigin + ", be=" + battleEvent + ", t=" + (heroesTarget != null ? heroesTarget : "") + '}';
    }

}
