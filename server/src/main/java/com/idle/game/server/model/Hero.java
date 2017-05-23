package com.idle.game.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idle.game.core.type.HeroType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rafael
 */
@Entity
public class Hero implements Serializable {

    @Id
    @SequenceGenerator(name = "seqhero", sequenceName = "seqhero", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqhero")
    private Long id;
    private String heroTypeId;
    private transient HeroType heroType;
    private Integer level;
    @ManyToOne
    @JoinColumn(name = "idplayer")
    @JsonIgnore
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeroTypeId() {
        return heroTypeId;
    }

    public void setHeroTypeId(String heroTypeId) {
        this.heroTypeId = heroTypeId;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public com.idle.game.core.Hero toHero() throws Exception {
        return new com.idle.game.core.Hero(heroType, level);
    }

}
