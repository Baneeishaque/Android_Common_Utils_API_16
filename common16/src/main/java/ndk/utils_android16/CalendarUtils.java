package ndk.utils_android16;

import java.util.Calendar;

public class CalendarUtils {

    public static Calendar addMinutesToCalendar(Calendar calendar, int minutes) {

        Calendar tempCalender = Calendar.getInstance();
        tempCalender.setTime(org.apache.commons.lang3.time.DateUtils.addMinutes(calendar.getTime(), minutes));
        return tempCalender;
    }

    public static Calendar addFiveMinutesToCalendar(Calendar calendar) {

        return addMinutesToCalendar(calendar, 5);
    }
}
