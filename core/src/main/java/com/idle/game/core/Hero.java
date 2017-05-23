package com.idle.game.core;

import com.idle.game.core.type.HeroType;
import com.idle.game.core.type.ItemType;
import com.idle.game.core.item.Item;
import com.idle.game.core.buff.Buff;
import com.idle.game.core.constant.IdleConstants;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.exception.ValidationException;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.AttributeType;
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

/**
 *
 * @author rafael
 */
public class Hero extends BaseObject {

    private HeroType heroType;
    private Integer level;

    private transient Integer baseDmg;
    private transient Integer baseArmor;
    private transient Integer baseMagicResist;
    private transient Integer baseSpeed;
    private transient Integer baseLuck;
    private transient Integer baseCritChance;
    private transient Integer baseCritDamage;
    private transient Integer baseDodgeChance;
    private transient Integer baseBlockChance;
    private transient Integer baseHp;

    private Integer dmg;
    private Integer armor;
    private Integer magicResist;
    private Integer speed;
    private Integer luck;
    private Integer critChance;
    private Integer critDamage;
    private Integer dodgeChance;
    private Integer blockChance;
    private Integer hp;

    private Integer currDmg;
    private Integer currArmor;
    private Integer currMagicResist;
    private Integer currSpeed;
    private Integer currLuck;
    private Integer currCritChance;
    private Integer currCritDamage;
    private Integer currDodgeChance;
    private Integer currBlockChance;
    private Integer currHp;
    private Boolean canDoAction;

    private List<Buff> currBuffs;

    private Item chest;
    private Item weapon;
    private Item boot;
    private Item ring;
    private Item ammulet;
    private Item jewel;

    public Hero(HeroType heroType, Integer level) throws Exception {
        this.uuid = UUID.randomUUID();
        this.heroType = heroType;
        this.levelUp(level);
        this.calcAtributtes();
    }

    public Hero(HeroType heroType, Integer level,
            Integer baseDmg, Integer baseArmor, Integer baseMagicResist,
            Integer baseSpeed, Integer baseLuck, Integer baseCritChance,
            Integer baseCritDamage, Integer baseDodgeChance,
            Integer baseBlockChance, Integer baseHp) {
        this.uuid = UUID.randomUUID();
        this.heroType = heroType;
        this.level = level;
        this.baseDmg = baseDmg;
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

    public Integer getBaseDmg() {
        return baseDmg;
    }

    public void setBaseDmg(Integer baseDmg) {
        this.baseDmg = baseDmg;
    }

    public Integer getDmg() {
        return dmg;
    }

    public void setDmg(Integer dmg) {
        this.dmg = dmg;
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

    public Integer getCurrArmor() {
        return currArmor;
    }

    public void setCurrArmor(Integer currArmor) {
        this.currArmor = currArmor;
    }

    public Integer getCurrMagicResist() {
        return currMagicResist;
    }

    public void setCurrMagicResist(Integer currMagicResist) {
        this.currMagicResist = currMagicResist;
    }

    public Integer getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrSpeed(Integer currSpeed) {
        this.currSpeed = currSpeed;
    }

    public Integer getCurrLuck() {
        return currLuck;
    }

    public Boolean getCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(Boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public void setCurrLuck(Integer currLuck) {
        this.currLuck = currLuck;
    }

    public Integer getCurrCritChance() {
        return currCritChance;
    }

    public void setCurrCritChance(Integer currCritChance) {
        this.currCritChance = currCritChance;
    }

    public Integer getCurrCritDamage() {
        return currCritDamage;
    }

    public void setCurrCritDamage(Integer currCritDamage) {
        this.currCritDamage = currCritDamage;
    }

    public Integer getCurrDodgeChance() {
        return currDodgeChance;
    }

    public void setCurrDodgeChance(Integer currDodgeChance) {
        this.currDodgeChance = currDodgeChance;
    }

    public Integer getCurrBlockChance() {
        return currBlockChance;
    }

    public void setCurrBlockChance(Integer currBlockChance) {
        this.currBlockChance = currBlockChance;
    }

    public Integer getCurrHp() {
        return currHp;
    }

    public List<Buff> getCurrBuffs() {
        return currBuffs;
    }

    public void setCurrBuffs(List<Buff> currBuffs) {
        this.currBuffs = currBuffs;
    }

    public void addBuff(Buff buff) {
        this.currBuffs.add(buff);
    }

    public Integer getCurrDmg() {
        return currDmg;
    }

    public void setCurrDmg(Integer currDmg) {
        this.currDmg = currDmg;
    }

    public void setCurrHp(Integer currHp) {
        if (currHp > getHp()) {
            currHp = getHp();
        }
        if (currHp < 0) {
            currHp = 0;
        }
        this.currHp = currHp;
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
        LOG.log(Level.FINEST, "[prepareToBattle]");
        this.prepareToTurn();
        this.setCurrHp(this.getHp());
        this.setCurrBuffs(new ArrayList<>());
    }

    public void prepareToTurn() {
        LOG.log(Level.FINEST, "[prepareToTurn][hero] {0}", this);
        this.setCurrArmor(this.getArmor());
        LOG.log(Level.FINEST, "[prepareToTurn][armor] {0}", this.getCurrArmor());
        this.setCurrBlockChance(this.getBlockChance());
        LOG.log(Level.FINEST, "[prepareToTurn][blockChance] {0}", this.getCurrBlockChance());
        this.setCurrCritChance(this.getCritChance());
        LOG.log(Level.FINEST, "[prepareToTurn][critChance] {0}", this.getCurrCritChance());
        this.setCurrCritDamage(this.getCritDamage());
        LOG.log(Level.FINEST, "[prepareToTurn][critDmg] {0}", this.getCurrCritDamage());
        this.setCurrDodgeChance(this.getDodgeChance());
        LOG.log(Level.FINEST, "[prepareToTurn][dodgeChange] {0}", this.getCurrDodgeChance());
        this.setCurrLuck(this.getLuck());
        LOG.log(Level.FINEST, "[prepareToTurn][luck] {0}", this.getCurrLuck());
        this.setCurrMagicResist(this.getMagicResist());
        LOG.log(Level.FINEST, "[prepareToTurn][magicResist] {0}", this.getCurrMagicResist());
        this.setCurrDmg(this.getDmg());
        LOG.log(Level.FINEST, "[prepareToTurn][dmg] {0}", this.getCurrDmg());
        this.setCurrSpeed(this.getSpeed());
        LOG.log(Level.FINEST, "[prepareToTurn][speed] {0}", this.getCurrSpeed());
        this.setCanDoAction(Boolean.TRUE);
        LOG.log(Level.FINEST, "[prepareToTurn][canDoAction] {0}", this.getCanDoAction());
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
    }

    private Integer getMissingAttributePercentage(AttributeType at) throws Exception {
        switch (at) {
            case HP:
                return (int) ((this.getHp() - this.getCurrHp()) * 1d / this.getHp() * 100);
        }
        throw new ValidationException("attribute.can.not.be.trade");

    }

    private void calcTradeAttribute(AttributeType o, AttributeType d, Integer ratioPercentage) throws Exception {
        Double perc = this.getMissingAttributePercentage(o) / 100d * ratioPercentage / 100d;
        LOG.log(Level.FINEST, "[passive][perc] {0}", perc);
        if (perc > 0) {
            switch (d) {
                case LUCK:
                    this.setCurrLuck(this.getCurrLuck() + (int) (this.getCurrLuck() * perc));
                    LOG.log(Level.FINEST, "[passive][luck] {0}", this.getCurrLuck());
                    break;
                case SPEED:
                    this.setCurrSpeed(this.getCurrSpeed() + (int) (this.getCurrSpeed() * perc));
                    LOG.log(Level.FINEST, "[passive][speed] {0}", this.getCurrSpeed());
                    break;
                case DODGE:
                    this.setCurrDodgeChance(this.getCurrDodgeChance() + (int) (this.getCurrDodgeChance() * perc));
                    LOG.log(Level.FINEST, "[passive][dodgeChance] {0}", this.getCurrDodgeChance());
                    break;
                case CRIT_DMG:
                    this.setCurrCritDamage(this.getCurrCritDamage() + (int) (this.getCurrCritDamage() * perc));
                    LOG.log(Level.FINEST, "[passive][critDmg] {0}", this.getCurrCritDamage());
                    break;
                case CRIT_CHANCE:
                    this.setCurrCritChance(this.getCurrCritChance() + (int) (this.getCurrCritChance() * perc));
                    LOG.log(Level.FINEST, "[passive][critChance] {0}", this.getCurrCritChance());
                    break;
                case DMG:
                    this.setCurrDmg(this.getCurrDmg() + (int) (this.getCurrDmg() * perc));
                    LOG.log(Level.FINEST, "[passive][dmg] {0}", this.getCurrDmg());
                    break;
                case ARMOR:
                    this.setCurrArmor(this.getCurrArmor() + (int) (this.getCurrArmor() * perc));
                    LOG.log(Level.FINEST, "[passive][armor] {0}", this.getCurrArmor());
                    break;
                case MAGIC_RESIST:
                    this.setCurrMagicResist(this.getCurrMagicResist() + (int) (this.getCurrMagicResist() * perc));
                    LOG.log(Level.FINEST, "[passive][magicResist] {0}", this.getCurrMagicResist());
                    break;
                case DEFENSE:
                    this.setCurrArmor(this.getCurrArmor() + (int) (this.getCurrArmor() * perc));
                    this.setCurrMagicResist(this.getCurrMagicResist() + (int) (this.getCurrMagicResist() * perc));
                    LOG.log(Level.FINEST, "[passive][armor] {0}", this.getCurrArmor());
                    LOG.log(Level.FINEST, "[passive][magicResist] {0}", this.getCurrMagicResist());
                    break;

            }
        }

    }

    public void calcPassives() throws Exception {
        LOG.log(Level.FINEST, "[passive][heroi] {0}", this);
        if (this.getHeroType().getPassives() != null) {
            for (Passive p : this.getHeroType().getPassives()) {
                LOG.log(Level.FINEST, "[passive][type] {0}", p.getPassiveType());
                switch (p.getPassiveType()) {
                    case TRADE_ATTRIBUTE:
                        calcTradeAttribute(p.getAttributeTypeLost(), p.getAttributeTypeGain(), p.getRatioPercentage());
                        break;
                }
                LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

            }
        }
    }

    private void calcAtributtes() {
        LOG.log(Level.FINEST, "[calcAtributtes]");
        this.setArmor(this.getBaseArmor());
        this.setBlockChance(this.getBaseBlockChance());
        this.setCritChance(this.getBaseCritChance());
        this.setCritDamage(this.getBaseCritDamage());
        this.setDodgeChance(this.getBaseDodgeChance());
        this.setHp(this.getBaseHp());
        this.setLuck(this.getBaseLuck());
        this.setMagicResist(this.getBaseMagicResist());
        this.setDmg(this.getBaseDmg());
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
            this.setDmg(this.getDmg() + i.getDmg());
            this.setSpeed(this.getSpeed() + i.getSpeed());
        }
    }

    public void levelUp() throws Exception {
        levelUp(this.getLevel() + 1);
    }

    public void levelUp(Integer level) throws Exception {
        HeroType ht = this.getHeroType();
        if (level <= ht.getMaxLevel()) {

            Double levelUpRatio = 1d * (level - 1) / (ht.getMaxLevel() - 1);

            if (levelUpRatio > 0) {
                this.setBaseArmor(ht.getBaseArmor() + (int) (ht.getMaxLevelUpIncArmor() * levelUpRatio));
                this.setBaseBlockChance(ht.getBaseBlockChance() + (int) (ht.getMaxLevelUpIncBlockChance() * levelUpRatio));
                this.setBaseCritChance(ht.getBaseCritChance() + (int) (ht.getMaxLevelUpIncCritChance() * levelUpRatio));
                this.setBaseCritDamage(ht.getBaseCritDamage() + (int) (ht.getMaxLevelUpIncCritDamage() * levelUpRatio));
                this.setBaseDmg(ht.getBaseDmg() + (int) (ht.getMaxLevelUpIncDmg() * levelUpRatio));
                this.setBaseDodgeChance(ht.getBaseDodgeChance() + (int) (ht.getMaxLevelUpIncDodgeChance() * levelUpRatio));
                this.setBaseHp(ht.getBaseHp() + (int) (ht.getMaxLevelUpIncHp() * levelUpRatio));
                this.setBaseLuck(ht.getBaseLuck() + (int) (ht.getMaxLevelUpIncLuck() * levelUpRatio));
                this.setBaseMagicResist(ht.getBaseMagicResist() + (int) (ht.getMaxLevelUpIncMagicResist() * levelUpRatio));
                this.setBaseSpeed(ht.getBaseSpeed() + (int) (ht.getMaxLevelUpIncSpeed() * levelUpRatio));
            } else {
                this.setBaseArmor(ht.getBaseArmor());
                this.setBaseBlockChance(ht.getBaseBlockChance());
                this.setBaseCritChance(ht.getBaseCritChance());
                this.setBaseCritDamage(ht.getBaseCritDamage());
                this.setBaseDmg(ht.getBaseDmg());
                this.setBaseDodgeChance(ht.getBaseDodgeChance());
                this.setBaseHp(ht.getBaseHp());
                this.setBaseLuck(ht.getBaseLuck());
                this.setBaseMagicResist(ht.getBaseMagicResist());
                this.setBaseSpeed(ht.getBaseSpeed());
            }

            this.setLevel(level);
        } else {
            throw new ValidationException("hero.max.level.reached");
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
        return "H{" + "id=" + this.uuid.toString().substring(0, 8) + ",currHp=" + this.currHp + ",type=" + this.heroType + '}';
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
