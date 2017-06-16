package com.idle.game.server.util;

import com.idle.game.server.model.Player;

/**
 *
 * @author rafael
 */
public enum PriceEnum {

    PAID_PVP_ROLL(0l, 0l, 0l, 10l);

    private final Long gold;
    private final Long soul;
    private final Long ancientRune;
    //premium resource
    private final Long spiritCrystal;

    public Long getGold() {
        return gold;
    }

    public Long getSoul() {
        return soul;
    }

    public Long getAncientRune() {
        return ancientRune;
    }

    public Long getSpiritCrystal() {
        return spiritCrystal;
    }

    private PriceEnum(Long g, Long s, Long ar, Long sc) {
        this.gold = g;
        this.soul = s;
        this.ancientRune = ar;
        this.spiritCrystal = sc;
    }
    
    public Boolean playerCanPay(Player p) {
        return (p.getGold() >= this.getGold() 
                && p.getSoul() >= this.getSoul()
                && p.getAncientRune() >= this.getAncientRune()
                && p.getSpiritCrystal() >= this.getSpiritCrystal());
    }
    
    public Player pay(Player p) {
        p.setGold(p.getGold()-this.getGold());
        p.setSoul(p.getSoul()-this.getSoul());
        p.setAncientRune(p.getAncientRune()-this.getAncientRune());
        p.setSpiritCrystal(p.getSpiritCrystal()-this.getSpiritCrystal());
        return p;
    }

}
