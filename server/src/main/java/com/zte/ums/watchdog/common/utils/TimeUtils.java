package com.zte.ums.watchdog.common.utils;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 2016/9/22.
 */
public abstract class TimeUtils {
    public static String dateToString() {
        Date data = new Date(System.currentTimeMillis());
        String formatType = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(formatType).format(data);
    }
}
