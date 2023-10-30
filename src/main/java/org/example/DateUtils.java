package org.example;

import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtils {
    public static final int ONE_DAY_TIME = 86400000;

    public static Date getZeroDay(Date time) {
        return getZeroDay(time, 0);
    }

    public static ThreadLocal<DateFormat> COMPACT_DAY_FILE_PATTERN_LOCAL = ThreadLocal.withInitial(() -> {
        return new SimpleDateFormat("yyyyMMddHHmmss");
    });

    public static ThreadLocal<DateFormat> COMPACT_DAY_PATTERN_LOCAL = ThreadLocal.withInitial(() -> {
        return new SimpleDateFormat("yyyyMMdd");
    });


    public static String format(ThreadLocal<DateFormat> threadLocal, Date date) {
        return ((DateFormat)threadLocal.get()).format(date);
    }

    public static Date getZeroDay(Date time, int day) {
        Calendar cal = getLocalCalendar();
        cal.setTime(time);
        cal.add(5, day);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return new Date(cal.getTimeInMillis());
    }

    public static Calendar getLocalCalendar() {
        TimeZone zone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(zone);
        return Calendar.getInstance();
    }
}
