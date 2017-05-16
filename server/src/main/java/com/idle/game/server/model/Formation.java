package com.idle.game.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    public com.idle.game.core.Formation toFormation() {
        List<com.idle.game.core.PositionedHero> hs = new ArrayList<>(this.heroes.size());
        this.heroes.forEach(h -> {
            hs.add(h.toPositionedHero());
        });
        return new com.idle.game.core.Formation(hs);
    }

}
