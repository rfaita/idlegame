package com.idle.game.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idle.game.core.type.BattlePositionType;
import com.idle.game.core.type.HeroType;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "positionedhero")
public class PositionedHero implements Serializable {

    @Id
    @SequenceGenerator(name = "seqPositionedHero", sequenceName = "seqPositionedHero", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqPositionedHero")
    private Long id;
    private BattlePositionType battlePosition;
    @OneToOne
    @JoinColumn(name = "idhero")
    private Hero hero;
    @ManyToOne
    @JoinColumn(name = "idformation")
    @JsonIgnore
    private Formation formation;

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public BattlePositionType getBattlePosition() {
        return battlePosition;
    }

    public void setBattlePosition(BattlePositionType battlePosition) {
        this.battlePosition = battlePosition;
    }

    public com.idle.game.core.PositionedHero toPositionedHero(Map<UUID, HeroType> heroTypes) throws Exception {
        return new com.idle.game.core.PositionedHero(battlePosition, hero.toHero(heroTypes));
    }

}
