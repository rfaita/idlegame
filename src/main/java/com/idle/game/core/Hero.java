package com.idle.game.core;

import com.idle.game.core.exception.ValidationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Hero extends BaseObject {

    private static final Logger LOG = Logger.getLogger(Hero.class.getName());

    private HeroType heroType;
    private Integer level;
    private Integer baseMeleeDmg;
    private DamageType dmgTypeMelee;
    private Integer baseRangedDmg;
    private DamageType dmgTypeRanged;
    private Integer baseArmor;
    private Integer baseMagicResist;
    private Integer baseSpeed;
    private Integer baseLuck;
    private Integer baseCritChance;
    private Integer baseCritDamage;
    private Integer baseDodgeChance;
    private Integer baseBlockChance;
    private Integer baseHp;

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

    private Integer currentMeleeDmg;
    private Integer currentRangedDmg;
    private Integer currentArmor;
    private Integer currentMagicResist;
    private Integer currentSpeed;
    private Integer currentLuck;
    private Integer currentCritChance;
    private Integer currentCritDamage;
    private Integer currentDodgeChance;
    private Integer currentBlockChance;
    private Integer currentHp;
    private Boolean canDoAction;

    private List<Buff> currentBuffs;

    private Item chest;
    private Item weapon;
    private Item boot;
    private Item ring;
    private Item ammulet;
    private Item jewel;

    public Hero(UUID id, HeroType heroType, Integer level,
            Integer baseMeleeDmg, DamageType dmgTypeMelee,
            Integer baseRangedDmg, DamageType dmgTypeRanged,
            Integer baseArmor, Integer baseMagicResist,
            Integer baseSpeed, Integer baseLuck, Integer baseCritChance,
            Integer baseCritDamage, Integer baseDodgeChance,
            Integer baseBlockChance, Integer baseHp) {
        this.uuid = id;
        this.heroType = heroType;
        this.level = level;
        this.baseMeleeDmg = baseMeleeDmg;
        this.dmgTypeMelee = dmgTypeMelee;
        this.baseRangedDmg = baseRangedDmg;
        this.dmgTypeRanged = dmgTypeRanged;
        this.baseArmor = baseArmor;
        this.baseMagicResist = baseMagicResist;
        this.baseSpeed = baseSpeed;
        this.baseLuck = baseLuck;
        this.baseCritChance = baseCritChance;
        this.baseCritDamage = baseCritDamage;
        this.baseDodgeChance = baseDodgeChance;
        this.baseBlockChance = baseBlockChance;
        this.baseHp = baseHp;

        this.calcAtributtes();
    }

    public Item getChest() {
        return chest;
    }

    public Item getWeapon() {
        return weapon;
    }

    public Item getBoot() {
        return boot;
    }

    public Item getRing() {
        return ring;
    }

    public Item getAmmulet() {
        return ammulet;
    }

    public Item getJewel() {
        return jewel;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getBaseMeleeDmg() {
        return baseMeleeDmg;
    }

    public void setBaseMeleeDmg(Integer baseMeleeDmg) {
        this.baseMeleeDmg = baseMeleeDmg;
    }

    public DamageType getDmgTypeMelee() {
        return dmgTypeMelee;
    }

    public void setDmgTypeMelee(DamageType dmgTypeMelee) {
        this.dmgTypeMelee = dmgTypeMelee;
    }

    public Integer getBaseRangedDmg() {
        return baseRangedDmg;
    }

    public void setBaseRangedDmg(Integer baseRangedDmg) {
        this.baseRangedDmg = baseRangedDmg;
    }

    public DamageType getDmgTypeRanged() {
        return dmgTypeRanged;
    }

    public void setDmgTypeRanged(DamageType dmgTypeRanged) {
        this.dmgTypeRanged = dmgTypeRanged;
    }

    public Integer getBaseArmor() {
        return baseArmor;
    }

    public void setBaseArmor(Integer baseArmor) {
        this.baseArmor = baseArmor;
    }

    public Integer getBaseMagicResist() {
        return baseMagicResist;
    }

    public void setBaseMagicResist(Integer baseMagicResist) {
        this.baseMagicResist = baseMagicResist;
    }

    public Integer getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(Integer baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public Integer getBaseLuck() {
        return baseLuck;
    }

    public void setBaseLuck(Integer baseLuck) {
        this.baseLuck = baseLuck;
    }

    public Integer getBaseCritChance() {
        return baseCritChance;
    }

    public void setBaseCritChance(Integer baseCritChance) {
        this.baseCritChance = baseCritChance;
    }

    public Integer getBaseCritDamage() {
        return baseCritDamage;
    }

    public void setBaseCritDamage(Integer baseCritDamage) {
        this.baseCritDamage = baseCritDamage;
    }

    public Integer getBaseDodgeChance() {
        return baseDodgeChance;
    }

    public void setBaseDodgeChance(Integer baseDodgeChance) {
        this.baseDodgeChance = baseDodgeChance;
    }

    public Integer getBaseBlockChance() {
        return baseBlockChance;
    }

    public void setBaseBlockChance(Integer baseBlockChance) {
        this.baseBlockChance = baseBlockChance;
    }

    public Integer getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(Integer baseHp) {
        this.baseHp = baseHp;
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

    public void setMagicResist(Integer nagicResist) {
        this.magicResist = nagicResist;
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

    public void setDodgeChance(Integer dodgeChange) {
        this.dodgeChance = dodgeChange;
    }

    public Integer getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Integer blockChange) {
        this.blockChance = blockChange;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getCurrentMeleeDmg() {
        return currentMeleeDmg;
    }

    public void setCurrentMeleeDmg(Integer currentMeleeDmg) {
        this.currentMeleeDmg = currentMeleeDmg;
    }

    public Integer getCurrentRangedDmg() {
        return currentRangedDmg;
    }

    public void setCurrentRangedDmg(Integer currentRangedDmg) {
        this.currentRangedDmg = currentRangedDmg;
    }

    public Integer getCurrentArmor() {
        return currentArmor;
    }

    public void setCurrentArmor(Integer currentArmor) {
        this.currentArmor = currentArmor;
    }

    public Integer getCurrentMagicResist() {
        return currentMagicResist;
    }

    public void setCurrentMagicResist(Integer currentMagicResist) {
        this.currentMagicResist = currentMagicResist;
    }

    public Integer getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Integer currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Integer getCurrentLuck() {
        return currentLuck;
    }

    public Boolean getCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(Boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public void setCurrentLuck(Integer currentLuck) {
        this.currentLuck = currentLuck;
    }

    public Integer getCurrentCritChance() {
        return currentCritChance;
    }

    public void setCurrentCritChance(Integer currentCritChance) {
        this.currentCritChance = currentCritChance;
    }

    public Integer getCurrentCritDamage() {
        return currentCritDamage;
    }

    public void setCurrentCritDamage(Integer currentCritDamage) {
        this.currentCritDamage = currentCritDamage;
    }

    public Integer getCurrentDodgeChance() {
        return currentDodgeChance;
    }

    public void setCurrentDodgeChance(Integer currentDodgeChance) {
        this.currentDodgeChance = currentDodgeChance;
    }

    public Integer getCurrentBlockChance() {
        return currentBlockChance;
    }

    public void setCurrentBlockChance(Integer currentBlockChance) {
        this.currentBlockChance = currentBlockChance;
    }

    public Integer getCurrentHp() {
        return currentHp;
    }

    public List<Buff> getCurrentBuffs() {
        return currentBuffs;
    }

    public void setCurrentBuffs(List<Buff> currentBuffs) {
        this.currentBuffs = currentBuffs;
    }

    public void addBuff(Buff buff) {
        this.currentBuffs.add(buff);
    }

    public void setCurrentHp(Integer currentHp) {
        if (currentHp > getHp()) {
            currentHp = getHp();
        }
        if (currentHp < 0) {
            currentHp = 0;
        }
        this.currentHp = currentHp;
    }

    public DamageType getDmgType() {
        return this.getDmgTypeMelee() != null ? this.getDmgTypeMelee() : this.getDmgTypeRanged();
    }

    public Integer getCurrentDmg() {
        return this.getCurrentMeleeDmg() > 0 ? this.getCurrentMeleeDmg() : this.getCurrentRangedDmg();
    }

    public void setBoot(Item i) throws Exception {
        if (!i.getItemType().equals(ItemType.BOOT)) {
            throw new ValidationException("item.must.be.boot");
        }
        this.boot = i;
        calcAtributtes();
    }

    public void setChest(Item i) throws Exception {
        if (!i.getItemType().equals(ItemType.CHEST)) {
            throw new ValidationException("item.must.be.chest");
        }
        this.chest = i;
        calcAtributtes();
    }

    public void setWeapon(Item i) throws Exception {
        if (!i.getItemType().equals(ItemType.WEAPON)) {
            throw new ValidationException("item.must.be.weapon");
        }
        this.weapon = i;
        calcAtributtes();
    }

    public void setRing(Item i) throws Exception {
        if (!i.getItemType().equals(ItemType.RING)) {
            throw new ValidationException("item.must.be.ring");
        }
        this.ring = i;
        calcAtributtes();
    }

    public void setAmmulet(Item i) throws Exception {
        if (!i.getItemType().equals(ItemType.AMMULET)) {
            throw new ValidationException("item.must.be.ammulet");
        }
        this.ammulet = i;
        calcAtributtes();
    }

    public void setJewel(Item i) throws Exception {
        if (!i.getItemType().equals(ItemType.JEWEL)) {
            throw new ValidationException("item.must.be.jewel");
        }
        this.jewel = i;
        calcAtributtes();
    }

    public void prepareToBattle() {
        this.prepareToComputeBuffs();
        this.setCurrentHp(this.getHp());
        this.setCurrentBuffs(new ArrayList<>());
    }

    public void prepareToComputeBuffs() {
        this.setCurrentArmor(this.getArmor());
        this.setCurrentBlockChance(this.getBlockChance());
        this.setCurrentCritChance(this.getCritChance());
        this.setCurrentCritDamage(this.getCritDamage());
        this.setCurrentDodgeChance(this.getDodgeChance());
        this.setCurrentLuck(this.getLuck());
        this.setCurrentMagicResist(this.getMagicResist());
        this.setCurrentMeleeDmg(this.getMeleeDmg());
        this.setCurrentRangedDmg(this.getRangedDmg());
        this.setCurrentSpeed(this.getSpeed());
        this.setCanDoAction(Boolean.TRUE);
    }

    private void calcAtributtes() {
        this.setArmor(this.getBaseArmor());
        this.setBlockChance(this.getBaseBlockChance());
        this.setCritChance(this.getBaseCritChance());
        this.setCritDamage(this.getBaseCritDamage());
        this.setDodgeChance(this.getBaseDodgeChance());
        this.setHp(this.getBaseHp());
        this.setLuck(this.getBaseLuck());
        this.setMagicResist(this.getBaseMagicResist());
        this.setMeleeDmg(this.getBaseMeleeDmg());
        this.setRangedDmg(this.getBaseRangedDmg());
        this.setSpeed(this.getBaseSpeed());

        this.calcAtributtesItem(this.getAmmulet());
        this.calcAtributtesItem(this.getBoot());
        this.calcAtributtesItem(this.getChest());
        this.calcAtributtesItem(this.getJewel());
        this.calcAtributtesItem(this.getRing());
        this.calcAtributtesItem(this.getWeapon());

    }

    private void calcAtributtesItem(Item i) {
        if (i != null) {
            this.setArmor(this.getArmor() + i.getArmor());
            this.setBlockChance(this.getBlockChance() + i.getBlockChance());
            this.setCritChance(this.getCritChance() + i.getCritChance());
            this.setCritDamage(this.getCritDamage() + i.getCritDamage());
            this.setDodgeChance(this.getDodgeChance() + i.getDodgeChance());
            this.setHp(this.getHp() + i.getHp());
            this.setLuck(this.getLuck() + i.getLuck());
            this.setMagicResist(this.getMagicResist() + i.getMagicResist());
            this.setMeleeDmg(this.getMeleeDmg() + i.getMeleeDmg());
            this.setRangedDmg(this.getRangedDmg() + i.getRangedDmg());
            this.setSpeed(this.getSpeed() + i.getSpeed());
        }
    }

    public Hero duplicate() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Hero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "H{" + "id=" + this.uuid.toString().substring(0, 7) + ",currHp=" + this.currentHp + ",type=" + this.heroType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.uuid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

}
