package com.idle.game.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idle.game.core.type.HeroType;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "hero")
@NamedQueries({
    @NamedQuery(name = "Hero.findById", query = "SELECT h FROM Hero h "
            + "JOIN FETCH h.player "
            + "WHERE h.id = :id ")
    ,
    @NamedQuery(name = "Hero.findByPlayer", query = "SELECT h FROM Hero h "
            + "JOIN FETCH h.player "
            + "WHERE h.player.id = :idPlayer ")
    ,
    @NamedQuery(name = "Hero.findByLinkedUser", query = "SELECT h FROM Hero h "
            + "JOIN FETCH h.player "
            + "WHERE h.player.linkedUser = :linkedUser ")
})
public class Hero implements Serializable {

    @Id
    @SequenceGenerator(name = "seqhero", sequenceName = "seqhero", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqhero")
    private Long id;
    private String heroTypeId;
    private Integer level;
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public com.idle.game.core.Hero toHero(Map<UUID, HeroType> heroTypes) throws Exception {
        return new com.idle.game.core.Hero(this.id, heroTypes.get(UUID.fromString(this.heroTypeId)), level);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
