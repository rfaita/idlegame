package com.idle.game.core;

import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.SubActionType;
import com.idle.game.core.type.BuffType;
import com.idle.game.core.type.ActionType;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class BattleEvent implements Serializable {

    private ActionType type;
    private SubActionType subType;
    private Integer value;
    private DamageType damageType;
    private BuffType buffType;

    public SubActionType getSubType() {
        return subType;
    }

    public void setSubType(SubActionType subType) {
        this.subType = subType;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public BuffType getBuffType() {
        return buffType;
    }

    public void setBuffType(BuffType buffType) {
        this.buffType = buffType;
    }

    public BattleEvent() {
    }

    public BattleEvent(ActionType type) {
        this.type = type;
    }

    public BattleEvent(ActionType type, Integer value, DamageType damageType) {
        this.type = type;
        this.value = value;
        this.damageType = damageType;
    }

    public BattleEvent(ActionType type, Integer value, DamageType damageType, BuffType buffType) {
        this.type = type;
        this.value = value;
        this.damageType = damageType;
        this.buffType = buffType;
    }

    @Override
    public String toString() {
        return "BE{" + "t=" + type + ", st=" + subType + ", value=" + value + ", dt=" + damageType + ", bt=" + buffType + '}';
    }

}
