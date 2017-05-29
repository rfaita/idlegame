package com.idle.game.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rafael
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Player.findByLinkedUser", query = "SELECT h FROM Player h "
            + "WHERE h.linkedUser = :linkedUser ")
})
public class Player implements Serializable {

    @Id
    @SequenceGenerator(name = "seqplayer", sequenceName = "seqplayer", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqplayer")
    private Long id;
    private String name;
    private String user;
    @OneToMany(targetEntity = Hero.class, mappedBy = "player")
    private List<Hero> heroes;
    @OneToMany(targetEntity = Formation.class, mappedBy = "player")
    private List<Formation> formations;
    private String linkedUser;
    private Long gem;
    private Long gold;
    private Long liquid;
    @OneToOne
    @JoinColumn(name = "idNextLevelFormationPve")
    private Formation nextLevelFormationPve;

    public Formation getNextLevelFormationPve() {
        return nextLevelFormationPve;
    }

    public void setNextLevelFormationPve(Formation nextLevelFormationPve) {
        this.nextLevelFormationPve = nextLevelFormationPve;
    }

    public Long getGem() {
        return gem;
    }

    public void setGem(Long gem) {
        this.gem = gem;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getLiquid() {
        return liquid;
    }

    public void setLiquid(Long liquid) {
        this.liquid = liquid;
    }

    public String getLinkedUser() {
        return linkedUser;
    }

    public void setLinkedUser(String linkedUser) {
        this.linkedUser = linkedUser;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Player other = (Player) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
