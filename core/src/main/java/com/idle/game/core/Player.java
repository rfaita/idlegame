package com.idle.game.core;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class Player extends BaseObject {

    private Integer level;
    private String name;
    private Formation attackFormation;
    private Formation defenseFormation;
    private List<Hero> heroes;

    public Player(UUID id, String name) {
        this.uuid = id;
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Formation getAttackFormation() {
        return attackFormation;
    }

    public void setAttackFormation(Formation attackFormation) {
        this.attackFormation = attackFormation;
    }

    public Formation getDefenseFormation() {
        return defenseFormation;
    }

    public void setDefenseFormation(Formation defenseFormation) {
        this.defenseFormation = defenseFormation;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public String toString() {
        return "Player{" + "level=" + level + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name) + uuid.hashCode();
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.uuid, other.uuid);
    }

}
