package com.idle.game.model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 *
 * @author rafael
 */
public enum GuildMemberType {

    MEMBER(0, FALSE, FALSE, FALSE, FALSE), OFFICIAL(5000, TRUE, TRUE, FALSE, FALSE), ADMIN(9999, TRUE, TRUE, TRUE, TRUE);

    private final Integer level;
    private final Boolean canAccept;
    private final Boolean canKick;
    private final Boolean canInvite;
    private final Boolean canPromote;

    public Boolean canAccept() {
        return canAccept;
    }

    public Boolean canKick() {
        return canKick;
    }

    public Boolean canInvite() {
        return canInvite;
    }

    public Integer getLevel() {
        return level;
    }

    public Boolean canPromote() {
        return canPromote;
    }

    private GuildMemberType(Integer level, Boolean canAccept, Boolean canKick, Boolean canInvite, Boolean canPromote) {
        this.level = level;
        this.canAccept = canAccept;
        this.canKick = canKick;
        this.canInvite = canInvite;
        this.canPromote = canPromote;
    }

}
