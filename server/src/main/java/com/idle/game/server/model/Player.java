package com.idle.game.server.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rafael
 */
@Entity
public class Player implements Serializable {

    @Id
    @SequenceGenerator(name = "seqplayer", sequenceName = "seqplayer", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqplayer")
    private Long id;
    private String name;
    private String user;
    @OneToMany(targetEntity = Hero.class, mappedBy = "player")
    private List<Hero> heroes;

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

}
