package com.idle.game.core.hero.type;

public enum HeroSize {

    SMALL(1, 1), MEDIUM(2, 2), LARGE(3, 3);

    private final Integer x;
    private final Integer y;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer size() {
        return x * y;
    }

    private HeroSize(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

}
