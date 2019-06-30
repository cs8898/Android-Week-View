package com.alamkanak.weekview;

import java.util.Calendar;

/**
 * Created by jesse on 6/02/2016.
 */
public class WeekViewUtil {


    /////////////////////////////////////////////////////////////////
    //
    //      Helper methods.
    //
    /////////////////////////////////////////////////////////////////

    /**
     * Checks if two dates are on the same day.
     *
     * @param dateOne The first date.
     * @param dateTwo The second date.     *
     * @return Whether the dates are on the same day.
     */
    public static boolean isSameDay(WeekView context, Calendar dateOne, Calendar dateTwo) {
        int minTime = context.getMinTime();
        int maxTime = context.getMaxTime();
        //TODO Silvester New Year Bug ;D
        if (dateOne.get(Calendar.YEAR) != dateTwo.get(Calendar.YEAR))
            return false;
        if (minTime <= maxTime) {
            return dateOne.get(Calendar.DAY_OF_YEAR) == dateTwo.get(Calendar.DAY_OF_YEAR);
        } else {
            int dayOne = dateOne.get(Calendar.DAY_OF_YEAR);
            int dayTwo = dateTwo.get(Calendar.DAY_OF_YEAR);
            if (dateOne.get(Calendar.HOUR_OF_DAY) < maxTime) {
                dayOne--;
            }
            if (dateTwo.get(Calendar.HOUR_OF_DAY) < maxTime) {
                dayTwo--;
            }
            return dayOne == dayTwo;
        }
    }

    /**
     * Returns a calendar instance at the start of today
     *
     * @return the calendar instance
     */
    public static Calendar today() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today;
    }

    /**
     * Checks if two dates are on the same day and hour.
     *
     * @param dateOne The first day.
     * @param dateTwo The second day.
     * @return Whether the dates are on the same day and hour.
     */
    public static boolean isSameDayAndHour(WeekView context, Calendar dateOne, Calendar dateTwo) {

        if (dateTwo != null) {
            return isSameDay(context, dateOne, dateTwo) && dateOne.get(Calendar.HOUR_OF_DAY) == dateTwo.get(Calendar.HOUR_OF_DAY);
        }
        return false;
    }

    /**
     * Returns the amount of days between the second date and the first date
     *
     * @param dateOne the first date
     * @param dateTwo the second date
     * @return the amount of days between dateTwo and dateOne
     */
    public static int daysBetween(Calendar dateOne, Calendar dateTwo) {
        return (int) (((dateTwo.getTimeInMillis() + dateTwo.getTimeZone().getOffset(dateTwo.getTimeInMillis())) / (1000 * 60 * 60 * 24)) -
                ((dateOne.getTimeInMillis() + dateOne.getTimeZone().getOffset(dateOne.getTimeInMillis())) / (1000 * 60 * 60 * 24)));
    }

    /*
     * Returns the amount of minutes passed in the day before the time in the given date
     * @param date
     * @return amount of minutes in day before time
     */
    public static int getPassedMinutesInDay(WeekView context, Calendar date) {
        return getPassedMinutesInDay(context, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE));
    }

    /**
     * Returns the amount of minutes in the given hours and minutes
     *
     * @param hour
     * @param minute
     * @return amount of minutes in the given hours and minutes
     */
    public static int getPassedMinutesInDay(WeekView context, int hour, int minute) {
        int minTime = context.getMinTime();
        int maxTime = context.getMaxTime();
        if (minTime <= maxTime) {
            return hour * 60 + minute;
        } else {
            if (hour < minTime) {
                return (24 + hour) * 60 + minute;
            } else {
                return hour + minTime * 60 + minute;
            }
        }
    }
}
