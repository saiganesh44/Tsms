package com.codestub.tsms.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

/**
 * Util class for date formats
 * Created by ganesh on 27/4/16.
 */
public class DateUtils {

    /**
     *
     * @param time : timestamp
     * @return the displayable date of the timestamp
     */
    public static String getDate(String time) {
        if(time == null) {
            return "";
        }
        String displayTime = time;
        Long timeStamp = Long.parseLong(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        Calendar currentCalendar = Calendar.getInstance();

        if(currentCalendar.get(Calendar.YEAR) > calendar.get(Calendar.YEAR)) {
            displayTime = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()) + " " + calendar.get(Calendar.YEAR);
        } else if (currentCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && currentCalendar.get(Calendar.DATE) == calendar.get(Calendar.DATE)) {
            displayTime = calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " " + calendar.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault());
        } else {
            displayTime = calendar.get(Calendar.DATE) + " " + calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        }
        return displayTime;
    }
}
