package com.idle.game.core;

/**
 *
 * @author rafael
 */
public class BattleEvent extends BaseObject {

    private BattleEventType type;
    private Integer value;
    private DamageType damageType;
    private BuffType buffType;

    public BattleEventType getType() {
        return type;
    }

    public void setType(BattleEventType type) {
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

    public BattleEvent(BattleEventType type) {
        this.type = type;
    }

    public BattleEvent(BattleEventType type, Integer value, DamageType damageType) {
        this.type = type;
        this.value = value;
        this.damageType = damageType;
    }

    public BattleEvent(BattleEventType type, Integer value, DamageType damageType, BuffType buffType) {
        this.type = type;
        this.value = value;
        this.damageType = damageType;
        this.buffType = buffType;
    }

    @Override
    public String toString() {
        return "BE{" + "type=" + type + ", value=" + value + ", dt=" + damageType + ", bt=" + buffType + '}';
    }

}
