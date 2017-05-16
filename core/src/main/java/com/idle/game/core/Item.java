package com.idle.game.core;

/**
 *
 * @author rafael
 */
public class Item extends BaseObject {

    private String name;
    private ItemQuality itemQuality;
    private ItemType itemType;
    private Integer meleeDmg;
    private Integer rangedDmg;
    private Integer armor;
    private Integer magicResist;
    private Integer speed;
    private Integer luck;
    private Integer critChance;
    private Integer critDamage;
    private Integer dodgeChance;
    private Integer blockChance;
    private Integer hp;

    public Item() {
    }

    public Item(String name, ItemQuality itemQuality, ItemType itemType, Integer meleeDmg, Integer rangedDmg, Integer armor, Integer magicResist, Integer speed, Integer luck, Integer critChance, Integer critDamage, Integer dodgeChance, Integer blockChance, Integer hp) {
        this.name = name;
        this.itemQuality = itemQuality;
        this.itemType = itemType;
        this.meleeDmg = meleeDmg;
        this.rangedDmg = rangedDmg;
        this.armor = armor;
        this.magicResist = magicResist;
        this.speed = speed;
        this.luck = luck;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.dodgeChance = dodgeChance;
        this.blockChance = blockChance;
        this.hp = hp;
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

    public Integer getMeleeDmg() {
        return meleeDmg;
    }

    public void setMeleeDmg(Integer meleeDmg) {
        this.meleeDmg = meleeDmg;
    }

    public Integer getRangedDmg() {
        return rangedDmg;
    }

    public void setRangedDmg(Integer rangedDmg) {
        this.rangedDmg = rangedDmg;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(Integer magicResist) {
        this.magicResist = magicResist;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getLuck() {
        return luck;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
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

    public Integer getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Integer blockChance) {
        this.blockChance = blockChance;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

}
