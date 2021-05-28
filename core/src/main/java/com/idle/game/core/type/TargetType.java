package com.idle.game.core.type;

import com.idle.game.core.target.*;

public enum TargetType {


    SELF(new SelfTarget()),
    FRONT_LINE(new RandomFrontLineTarget()),
    FRONT_LINE_NO_SELF(new RandomFrontLineNoSelfTarget()),
    MIDDLE_LINE(new RandomMiddleLineTarget()),
    MIDDLE_LINE_NO_SELF(new RandomMiddleLineNoSelfTarget()),
    BACK_LINE(new RandomBackLineTarget()),
    BACK_LINE_NO_SELF(new RandomBackLineNoSelfTarget()),
    RANDOM(new RandomTarget()),
    TWO_RANDOM(new TwoRandomTarget()),
    THREE_RANDOM(new ThreeRandomTarget()),
    FOUR_RANDOM(new FourRandomTarget()),
    RANDOM_NO_SELF(new RandomNoSelfTarget()),
    NO_FRONT_LINE(new NoFrontLineTarget()),
    IN_FRONT(new InFrontTarget()),
    IN_FRONT_PILLAR(new InFrontPillarTarget()),
    LOWER_LIFE(new LowerLifeTarget()),
    BIGGER_LIFE(new HighLifeTarget()),
    ALL_FRONT_LINE(new FrontLineTarget()),
    ALL_MIDDLE_LINE(new MiddleLineTarget()),
    ALL_BACK_LINE(new BackLineTarget()),
    RANDOM_ADJACENT(new AdjacentTarget());

    private Target target;

    TargetType(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }
}
