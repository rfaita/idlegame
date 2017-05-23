package com.idle.game.core.type;

/**
 *
 * @author rafael
 */
public enum AtittudeType {

    AGGRESSIVE(TargetType.LESS_PERC_LIFE),
    HUNTER(TargetType.LESS_LIFE),
    SUPPORT(TargetType.RANDOM),
    CONTROL(TargetType.RANDOM),
    HEALER(TargetType.RANDOM),
    DEFENSIVE(TargetType.RANDOM),
    STRATEGIST(TargetType.RANDOM),
    SPLITTER(TargetType.RANDOM);

    private final TargetType targetType;

    public TargetType getTargetType() {
        return targetType;
    }

    private AtittudeType(TargetType targetType) {
        this.targetType = targetType;
    }

}
