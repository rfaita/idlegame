package com.idle.game.core.item;

import com.idle.game.core.BaseObject;
import com.idle.game.core.type.DefenseType;
import java.util.Map;

/**
 *
 * @author rafael
 */
public class Item extends BaseObject {

    private String name;
    private ItemQuality itemQuality;
    private ItemType itemType;
    private Integer dmg;
    private Integer ap;
    private Integer speed;
    private Integer critChance;
    private Integer critDamage;
    private Integer dodgeChance;
    private Integer hp;
    private Map<DefenseType, Integer> defenses;

    public Item() {
    }

    public Item(String name, ItemQuality itemQuality, ItemType itemType,
            Integer dmg, Integer ap, Integer speed, Integer luck, Integer critChance,
            Integer critDamage, Integer dodgeChance, Integer hp,
            Map<DefenseType, Integer> defenses) {
        this.name = name;
        this.itemQuality = itemQuality;
        this.itemType = itemType;
        this.dmg = dmg;
        this.ap = ap;
        this.speed = speed;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.dodgeChance = dodgeChance;
        this.hp = hp;
        this.defenses = defenses;
    }

    public Integer getAp() {
        return ap;
    }

    public void setAp(Integer ap) {
        this.ap = ap;
    }

    public Map<DefenseType, Integer> getDefenses() {
        return defenses;
    }

    public void setDefenses(Map<DefenseType, Integer> defenses) {
        this.defenses = defenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemQuality getItemQuality() {
        return itemQuality;
    }

    public void setItemQuality(ItemQuality itemQuality) {
        this.itemQuality = itemQuality;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Integer getDmg() {
        return dmg;
    }

    public void setDmg(Integer dmg) {
        this.dmg = dmg;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getCritChance() {
        return critChance;
    }

    public void setCritChance(Integer critChance) {
        this.critChance = critChance;
    }

    public Integer getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(Integer critDamage) {
        this.critDamage = critDamage;
    }

    public Integer getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(Integer dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

}
