package com.clean.common.utility;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

public class PersianDate {
    private static final ULocale PERSIAN_LOCALE = new ULocale("fa_AF@calendar=persian");
    private static final ULocale PASTHO_LOCALE = new ULocale("pa_AF@calendar=persian");

    private static final ZoneId KABUL_ZONE_ID = ZoneId.of("Asia/Kabul");

    public static Date persianToGregorianDate(int year, int month, int day, int hour, int minutes, int seconds) {
        return new Date(fromPersianDate(year, month, day, hour, minutes, seconds));
    }

    public static Calendar gregorianToPersianCalendar(Date date) {
        Calendar persianCalendar = Calendar.getInstance(PERSIAN_LOCALE);
        persianCalendar.clear();
        persianCalendar.setTime(date);
        return persianCalendar;
    }

    /**
     * @param date
     * @param field example: Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, etc
     */
    public static int gregorianToPersianCalendarField(Date date, int field) {
        return gregorianToPersianCalendar(date).get(field);
    }

    public static String gregorianToPersianString(int year, int month, int day, int hour, int minutes, int seconds) {
        return gregorianToPersianString(persianToGregorianDate(year, month, day, hour, minutes, seconds));
    }

    public static String gregorianToPersianString(Date date) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, PERSIAN_LOCALE);
        return df.format(date);
    }

    public static LocalDateTime persianToLocalDateTime(int year, int month, int day, int hour, int minutes, int seconds) {
        return persianDateToZonedDateTime(year, month, day, hour, minutes, seconds).toLocalDateTime();
    }

    public static ZonedDateTime persianDateToZonedDateTime(int year, int month, int day, int hour, int minutes, int seconds) {
        return toZonedDateTime(fromPersianDate(year, month, day, hour, minutes, seconds));
    }

    public static long fromPersianDate(int year, int month, int day, int hour, int minutes, int seconds) {
        Calendar persianCalendar = Calendar.getInstance(PERSIAN_LOCALE);
        persianCalendar.clear();
        persianCalendar.set(year, month, day, hour, minutes, seconds);
        return persianCalendar.getTimeInMillis();
    }

    public static ZonedDateTime toZonedDateTime(Long epochMilli) {
        if(epochMilli == null) return null;
        return Instant.ofEpochMilli(epochMilli).atZone(KABUL_ZONE_ID);
    }
}
