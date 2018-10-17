package com.sdigitizers.utils.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.getInstance;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class is intended to provide basic date-time manipulation functions and
 * utilities
 *
 * @author Shriram Prajapat
 */
public class DateUtil {

    


    /**
     * To check if the date is valid (Legacy Java Time API)
     * @param date date to be checked
     * @return true - if the date id valid else false
     */
    public static boolean isValidDate(java.util.Date date) {
        return date != null;
    }

    /**
     * To check if the date is valid (SQL Date APi)
     * @param date date to be checked
     * @return true - if the date id valid else false
     */
    public static boolean isValidDate(java.sql.Date date) {
        return date != null;
    }

    /**
     * To get the latest instance of time (*Using java.util.time)
     *
     * @return Date with the latest instance of time when called
     */
    public static Date getCurrentDateTime() {
        return new Date();
    }

    /**
     * To get the latest instance of time (*Using Georgian Calendar getTime)
     *
     * @return Date with the latest instance of time when called
     */
    public static Date getCurrentDateTime_() {
        return (new GregorianCalendar()).getTime();
    }

    /**
     * To get the number of days from the month
     *
     * @param month Month
     * @param year Year
     * @return No. of days in the specified month
     */
    public static int getNumberOfDays_Month(int month, int year) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                return (year % 4 == 0) ? 29 : 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
            default:
                return 0;
        }
    }

    /**
     * ("dd MMM yyyy - hh:mm:ss a") eg 30 Jul 2016 - 06:13:10 AM
     *
     * @param date input date that is desired to be formatted
     * @return returns as Complete format date
     */
    public static String toDateTimeFormat(Date date) {
        return toDateFormat3(date) + " - " + toTimeFormat(date);
    }
    /**
     * ("dd MMM yyyy - hh:mm:ss a") eg 30 Jul 2016 - 06:13:10 AM
     *
     * @param date input date that is desired to be formatted
     * @return returns as Complete format date
     */
    public static String toDateTimeFormat(java.time.LocalDateTime date) {
        return toDateFormat3(date.toLocalDate()) + " - " + toTimeFormat(date.toLocalTime());
    }

    /**
     * (yyyy-MM-dd) eg: 2016-07-06
     *
     * @param date input date that is desired to be formatted
     * @return returns date as (yyyy-MM-dd)
     */
    public static String toDateFormat2(Date date) {
        if (date != null) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            return fmt.format(date);
        }
        return null;
    }
    /**
     * (yyyy-MM-dd) eg: 2016-07-06
     *
     * @param date input date that is desired to be formatted
     * @return returns date as (yyyy-MM-dd)
     */
    public static String toDateFormat2(java.time.LocalDate date) {
        if (date != null) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            return fmt.format(date);
        }
        return null;
    }

    /**
     * To get the year from the date
     *
     * @param date input date from which Year is to be extracted
     * @return returns Year from the date
     */
    public static int getYearFromDate(Date date) {
        if (date != null) {
            String d = toDateFormat2(date);
            return Integer.parseInt(d.substring(0, 4));
        }
        return 0;
    }

    /**
     * To get name of the day from the specified date (eg. Monday)
     *
     * @param d Date
     * @return Name of the day on specified date (eg. Monday)
     */
    public static String getDayName(Date d) {
        Calendar c = getInstance();
        c.setTime(d);
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }
        return null;
    }

    /**
     * To get the month name from the date
     *
     * @param date input date from which month name is to be extracted
     * @return returns month name from the date
     */
    public static String getMonthFromDate(Date date) {
        if (date != null) {
            String d = toDateFormat2(date);
            try {
                return decodeMonth(Byte.parseByte(d.substring(5, 7)));
            } catch (NumberFormatException ex) {
                return "N/A";
            }
        }
        return "N/A";
    }

    /**
     * To get day-no from the date
     *
     * @param date input date from which Day no. is to be extracted
     * @return returns Day no. from the date
     */
    public static int getDayNoFromDate(Date date) {
        if (date != null) {
            String d = toDateFormat1(date);
            return Integer.parseInt(d.substring(0, 2));
        }
        return 0;
    }

    /**
     * ("hh:mm:ss a") eg 06:13:10 AM
     *
     * @param date input date that is desired to be formatted
     * @return returns time as ("hh:mm:ss a")
     */
    public static String toTimeFormat(Date date) {
        return (new SimpleDateFormat("hh:mm:ss a")).format(date);
    }
    /**
     * ("hh:mm:ss a") eg 06:13:10 AM
     *
     * @param time input date that is desired to be formatted
     * @return returns time as ("hh:mm:ss a")
     */
    public static String toTimeFormat(java.time.LocalTime time) {
        return (new SimpleDateFormat("hh:mm:ss a")).format(time);
    }

    /**
     * ("dd MMM yyyy") eg 26 Aug 2016
     *
     * @param date input date that is desired to be formatted
     * @return returns date as ("dd MMM yyyy")
     */
    public static String toDateFormat3(Date date) {
        return (new SimpleDateFormat("dd MMM yyyy")).format(date);
    }
    /**
     * ("dd MMM yyyy") eg 26 Aug 2016
     *
     * @param date input date that is desired to be formatted
     * @return returns date as ("dd MMM yyyy")
     */
    public static String toDateFormat3(java.time.LocalDate date) {
        return (new SimpleDateFormat("dd MMM yyyy")).format(date);
    }

    /**
     * ("dd-MM-yyyy") eg: 30-07-2016
     *
     * @param date input date that is desired to be formatted
     * @return returns date as ("dd-MM-yyyy")
     */
    public static String toDateFormat1(Date date) {
        return (new SimpleDateFormat("dd-MM-yyyy")).format(date);
    }
    /**
     * ("dd-MM-yyyy") eg: 30-07-2016
     *
     * @param date input date that is desired to be formatted
     * @return returns date as ("dd-MM-yyyy")
     */
    public static String toDateFormat1(java.time.LocalDate date) {
        return (new SimpleDateFormat("dd-MM-yyyy")).format(date);
    }
    
    /**
     * Shift date by specified number of days
     *
     * @param d date to be shifted
     * @param days No. of days to shift (-ve: Shift backward, +ve: Shift
     * forward)
     * @return new date
     */
    public static Date shiftDate(Date d, int days) {
        long MILLISECONDS_PER_DAY = 1000L * 60 * 60 * 24 * days;
        long time = d.getTime();
        time += MILLISECONDS_PER_DAY;
        d.setTime(time);
        return d;
    }

    /////////////////////////////////////////////////////////////////////////////
    public static int encodeMonth(String m) {
        switch (m) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return 0;
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    public static String decodeMonth(int m) {
        switch (m) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return null;
        }
    }

}
