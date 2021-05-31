package com.idle.game.core.buff.type;

import static com.idle.game.core.buff.type.BuffType.BUFF;
import static com.idle.game.core.buff.type.BuffType.DEBUFF;

public enum BuffEffectType {

    DMG(DEBUFF), HEAL(BUFF), FROZEN(DEBUFF), STUN(DEBUFF), SILENCE(DEBUFF), DECREASE_ATTRIBUTE(DEBUFF), INCREASE_ATTRIBUTE(BUFF);

    private final BuffType buffType;

    public BuffType getBuffType() {
        return buffType;
    }

    BuffEffectType(BuffType buffType) {
        this.buffType = buffType;
    }

}
