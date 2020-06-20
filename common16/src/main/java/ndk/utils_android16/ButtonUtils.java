package ndk.utils_android16;

import android.widget.Button;

import java.util.Calendar;

public class ButtonUtils {

    public static void associateButtonWithIncrementedTimeStampOfFiveMinutes(Button buttonDate, Calendar calendar) {

        calendar.setTime(org.apache.commons.lang3.time.DateUtils.addMinutes(calendar.getTime(), 5));
        associateButtonWithTimeStamp(buttonDate, calendar);
    }

    public static void associateButtonWithTimeStamp(Button buttonDate, Calendar calendar) {

        buttonDate.setText(DateUtils.normalDateTimeFormatWords.format(calendar.getTime()));
    }

}
