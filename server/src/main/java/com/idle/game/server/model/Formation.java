package com.idle.game.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idle.game.core.type.HeroType;
import com.idle.game.core.type.FormationAllocation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rafael
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Formation.findById", query = "SELECT f FROM Formation f "
            + "JOIN FETCH f.heroes h "
            + "JOIN FETCH h.hero "
            + "LEFT JOIN FETCH f.player "
            + "WHERE f.id = :id")
    ,
    @NamedQuery(name = "Formation.findByPlayer", query = "SELECT f FROM Formation f "
            + "LEFT JOIN FETCH f.player "
            + "WHERE f.player.id = :idPlayer ")
    ,
    @NamedQuery(name = "Formation.findByLinkedUserAndAllocation", query = "SELECT f FROM Formation f "
            + "LEFT JOIN FETCH f.player "
            + "WHERE f.player.linkedUser = :linkedUser "
            + "AND f.formationAllocation = :formationAllocation")
    ,
    @NamedQuery(name = "Formation.findByLinkedUser", query = "SELECT f FROM Formation f "
            + "LEFT JOIN FETCH f.player "
            + "WHERE f.player.linkedUser = :linkedUser ")
})
public class Formation implements Serializable {

    @Id
    @SequenceGenerator(name = "seqformation", sequenceName = "seqformation", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqformation")
    private Long id;
    @OneToMany(targetEntity = PositionedHero.class,
            mappedBy = "formation", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, max = 6, message = "formation.min.size.max.size")
    private List<PositionedHero> heroes;
    @ManyToOne
    @JoinColumn(name = "idplayer")
    @JsonIgnore
    private Player player;
    @NotNull(message = "formation.allocation.can.not.be.null")
    private FormationAllocation formationAllocation;
    @OneToOne
    @JoinColumn(name = "idNextLevelFormation")
    @JsonIgnore
    private Formation nextLevelFormation;

    public Formation getNextLevelFormation() {
        return nextLevelFormation;
    }

    public void setNextLevelFormation(Formation nextLevelFormation) {
        this.nextLevelFormation = nextLevelFormation;
    }

    public FormationAllocation getFormationAllocation() {
        return formationAllocation;
    }

    public void setFormationAllocation(FormationAllocation formationAllocation) {
        this.formationAllocation = formationAllocation;
    }

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

    public List<PositionedHero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<PositionedHero> heroes) {
        this.heroes = heroes;
    }

    public com.idle.game.core.Formation toFormation(Map<UUID, HeroType> heroTypes) throws Exception {
        List<com.idle.game.core.PositionedHero> hs = new ArrayList<>(this.heroes.size());
        for (PositionedHero h : this.heroes) {
            hs.add(h.toPositionedHero(heroTypes));
        }
        return new com.idle.game.core.Formation(this.formationAllocation, hs);
    }

}
