package com.idle.game.core.buff;

import com.idle.game.core.BaseObject;
import com.idle.game.core.buff.type.BuffEffectType;
import com.idle.game.core.type.AttributeType;

public class BuffEffect extends BaseObject {

    //Percentual do dano que sera utilizado no efeito caso o mesmo necessite
    private Integer value;
    //Percentual do efeito ocorrer;
    private Integer chance;
    //Numero de turnos que o efeito persiste
    private Integer duration;
    private BuffEffectType effectType;
    private AttributeType attributeType;

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public BuffEffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(BuffEffectType effectType) {
        this.effectType = effectType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BuffEffect(BuffEffectType effectType, Integer percentage, Integer chance, Integer duration, AttributeType attributeType) {
        this.value = percentage;
        this.chance = chance;
        this.duration = duration;
        this.effectType = effectType;
        this.attributeType = attributeType;
    }

    public BuffEffect(BuffEffectType effectType, Integer percentage, Integer chance, Integer duration) {
        this(effectType, percentage, chance, duration, null);
    }

    public BuffEffect() {
    }

    @Override
    public String toString() {
        return "BE{v=" + value + ", c=" + chance + ", d=" + duration + ", et=" + effectType + '}';
    }

}
