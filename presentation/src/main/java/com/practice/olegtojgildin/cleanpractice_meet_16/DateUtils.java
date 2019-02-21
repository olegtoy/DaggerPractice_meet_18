package com.practice.olegtojgildin.cleanpractice_meet_16;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by olegtojgildin on 16/02/2019.
 */

public class DateUtils {

    public static String covertDate(long dates) {
        Date time = new Date((dates * 1000));
        return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(time);
    }
}
