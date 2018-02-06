package com.idle.game.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class DateUtil {

    public static Long secondsFrom(Date d) {
        LocalDateTime lastTime = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());

        return lastTime.until(LocalDateTime.now(), ChronoUnit.SECONDS);
    }

}
