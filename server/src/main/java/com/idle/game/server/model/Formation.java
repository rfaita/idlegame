package com.idle.game.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idle.game.server.dto.enums.FormationAllocation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rafael
 */
@Entity
public class Formation implements Serializable {

    @Id
    @SequenceGenerator(name = "seqformation", sequenceName = "seqformation", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqformation")
    private Long id;
    @OneToMany(targetEntity = PositionedHero.class, mappedBy = "formation", fetch = FetchType.EAGER)
    private List<PositionedHero> heroes;
    @ManyToOne
    @JoinColumn(name = "idplayer")
    @JsonIgnore
    private Player player;
    private FormationAllocation formationAllocation;

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

    public com.idle.game.core.Formation toFormation() throws Exception {
        List<com.idle.game.core.PositionedHero> hs = new ArrayList<>(this.heroes.size());
        for (PositionedHero h : this.heroes) {
            hs.add(h.toPositionedHero());
        }
        return new com.idle.game.core.Formation(hs);
    }

}
