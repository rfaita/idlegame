package com.idle.game.core;

/**
 *
 * @author rafael
 */
public class HeroType extends BaseObject {

    private String name;
    private Integer stars;
    private AtittudeType atittudeType;
    private Skill specialSkill;

    public Skill getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(Skill specialSkill) {
        this.specialSkill = specialSkill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public AtittudeType getAtittudeType() {
        return atittudeType;
    }

    public void setAtittudeType(AtittudeType atittudeType) {
        this.atittudeType = atittudeType;
    }

    public HeroType() {
    }

    public HeroType(AtittudeType atittudeType) {
        this.atittudeType = atittudeType;
    }

    @Override
    public String toString() {
        return "HT{" + "type=" + atittudeType + '}';
    }

}
