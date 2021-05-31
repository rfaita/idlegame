package com.idle.game.core.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Log implements Serializable {

    private Integer turn;
    private BattleUnit heroOrigin;
    private Event event;
    private List<BattleUnit> heroesTarget;

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public BattleUnit getHeroOrigin() {
        return heroOrigin;
    }

    public void setHeroOrigin(BattleUnit heroOrigin) {
        this.heroOrigin = heroOrigin;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<BattleUnit> getHeroesTarget() {
        return heroesTarget;
    }

    public void setHeroesTarget(List<BattleUnit> heroesTarget) {
        this.heroesTarget = heroesTarget;
    }

    public Log() {
    }

    public Log(Integer turn, Event event) {
        this.turn = turn;
        this.event = event;
    }

    public Log(Integer turn, BattleUnit heroOrigin, Event event) {
        this.turn = turn;
        this.heroOrigin = heroOrigin;
        this.event = event;
    }

    public Log(Integer turn, BattleUnit heroOrigin, Event event, BattleUnit heroTarget) {
        this.turn = turn;
        this.heroOrigin = heroOrigin;
        this.event = event;
        this.heroesTarget = new ArrayList<>();
        this.heroesTarget.add(heroTarget);
    }

    public Log(Integer turn, BattleUnit heroOrigin, Event event, List<BattleUnit> heroesTarget) {
        this.turn = turn;
        this.heroOrigin = heroOrigin;
        this.event = event;
        this.heroesTarget = heroesTarget;
    }

    @Override
    public String toString() {
        return "BL{turn=" + turn + ", ho=" + heroOrigin + ", be=" + event + ", t=" + (heroesTarget != null ? heroesTarget : "") + '}';
    }

}
