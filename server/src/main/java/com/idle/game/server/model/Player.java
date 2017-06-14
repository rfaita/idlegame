package com.idle.game.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "player")
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
    private Integer level;
    private String user;
    @OneToMany(targetEntity = Hero.class, mappedBy = "player")
    @JsonIgnore
    private List<Hero> heroes;
    @OneToMany(targetEntity = Formation.class, mappedBy = "player")
    @JsonIgnore
    private List<Formation> formations;
    @JsonIgnore
    private String linkedUser;
    private Long gold = 0l;
    private Long soul = 0l;
    private Long ancientRune = 0l;
    private Long spiritCrystal = 0l;
    private Long goldPerSecond = 0l;
    private Long soulPerSecond = 0l;
    private Long ancientRunePerSecond = 0l;
    private Long spiritCrystalPerSecond = 0l;
    private Long pvpScore = 0l;
    @ManyToOne
    @JoinColumn(name = "idNextLevelFormationPve")
    private Formation nextLevelFormationPve;
    @ManyToOne
    @JoinColumn(name = "idNextLevelFormationDungeon")
    private Formation nextLevelFormationDungeon;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date lastTimeResourcesCollected;

    public Long getPvpScore() {
        return pvpScore;
    }

    public void setPvpScore(Long pvpScore) {
        this.pvpScore = pvpScore;
    }

    public Formation getNextLevelFormationDungeon() {
        return nextLevelFormationDungeon;
    }

    public void setNextLevelFormationDungeon(Formation nextLevelFormationDungeon) {
        this.nextLevelFormationDungeon = nextLevelFormationDungeon;
    }

    public Date getLastTimeResourcesCollected() {
        return lastTimeResourcesCollected;
    }

    public void setLastTimeResourcesCollected(Date lastTimeResourcesCollected) {
        this.lastTimeResourcesCollected = lastTimeResourcesCollected;
    }

    public Long getGoldPerSecond() {
        return goldPerSecond;
    }

    public void setGoldPerSecond(Long goldPerSecond) {
        this.goldPerSecond = goldPerSecond;
    }

    public Long getSoulPerSecond() {
        return soulPerSecond;
    }

    public void setSoulPerSecond(Long soulPerSecond) {
        this.soulPerSecond = soulPerSecond;
    }

    public Long getAncientRunePerSecond() {
        return ancientRunePerSecond;
    }

    public void setAncientRunePerSecond(Long ancientRunePerSecond) {
        this.ancientRunePerSecond = ancientRunePerSecond;
    }

    public Long getSpiritCrystalPerSecond() {
        return spiritCrystalPerSecond;
    }

    public void setSpiritCrystalPerSecond(Long spiritCrystalPerSecond) {
        this.spiritCrystalPerSecond = spiritCrystalPerSecond;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Formation getNextLevelFormationPve() {
        return nextLevelFormationPve;
    }

    public void setNextLevelFormationPve(Formation nextLevelFormationPve) {
        this.nextLevelFormationPve = nextLevelFormationPve;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getSoul() {
        return soul;
    }

    public void setSoul(Long soul) {
        this.soul = soul;
    }

    public Long getAncientRune() {
        return ancientRune;
    }

    public void setAncientRune(Long ancientRune) {
        this.ancientRune = ancientRune;
    }

    public Long getSpiritCrystal() {
        return spiritCrystal;
    }

    public void setSpiritCrystal(Long spiritCrystal) {
        this.spiritCrystal = spiritCrystal;
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
