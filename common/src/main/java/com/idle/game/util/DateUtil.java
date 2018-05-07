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

    public static Long secondsFrom(Date date) {
        LocalDateTime localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return localDate.until(LocalDateTime.now(), ChronoUnit.SECONDS);
    }

}
