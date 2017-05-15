package com.idle.game.core;

/**
 *
 * @author rafael
 */
public class HeroType extends BaseObject {

    private String name;
    private Integer stars;
    private Integer size = 1;
    private AtittudeType atittudeType;
    private Action defaultAction;

    public Action getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(Action a) {
        this.defaultAction = a;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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
