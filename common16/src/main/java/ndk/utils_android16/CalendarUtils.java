package ndk.utils_android16;

import java.util.Calendar;

public class CalendarUtils {

    public static Calendar addMinutesToCalendar(Calendar calendar, int minutesToAdd) {

        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(org.apache.commons.lang3.time.DateUtils.addMinutes(calendar.getTime(), minutesToAdd));
        return tempCalendar;
    }


    public static Calendar addFiveMinutesToCalendar(Calendar calendar) {
        
        return addMinutesToCalendar(calendar, 5);
    }
}
