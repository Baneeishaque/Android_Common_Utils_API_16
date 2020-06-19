package ndk.utils_android16;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DateUtils {

    public static SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
    public static SimpleDateFormat normalDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
    public static SimpleDateFormat normalDateFormatWords = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.UK);
    public static SimpleDateFormat mysqlDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
    public static SimpleDateFormat normalDateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.UK);
    public static SimpleDateFormat normalDateTimeShortYearFormat = new SimpleDateFormat("dd-MM-yy HH:mm", Locale.UK);
    public static SimpleDateFormat normalDateShortYearFormat = new SimpleDateFormat("dd-MM-yy", Locale.UK);
    public static SimpleDateFormat normalDateTimeFormatWords = new SimpleDateFormat("EEE, MMM dd, yyyy HH:mm", Locale.UK);
    public static SimpleDateFormat normalStrippedDateFormat = new SimpleDateFormat("MMM dd", Locale.UK);

    public static String getCurrentDateStringInMysqlFormat() {

        return mysqlDateFormat.format(new Date());
    }

    //TODO : Use Apache Commons lang API
    public static Date addDays(Date date, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String dateToMysqlDateString(Date date) {

        return mysqlDateFormat.format(date);
    }

    public static String dateToNormalDateString(Date date) {

        return normalDateFormat.format(date);
    }

    public static String mysqlDateStringToDateString(String mysql_date) {

        try {

            return normalDateFormat.format(Objects.requireNonNull(normalDateFormat.parse(mysql_date)));

        } catch (ParseException e) {

            return normalDateFormat.format(new Date());
        }
    }

    public static String getCurrentDateStringInNormalFormat() {

        return normalDateFormat.format(new Date());
    }

    public static String getTomorrowDateStringInNormalFormat() {

        return normalDateFormat.format(addDays(new Date(), 1));
    }

    public static String normalDateStringToMysqlDateString(String normalDate, String applicationName) {

        try {

            return mysqlDateFormat.format(Objects.requireNonNull(normalDateFormat.parse(normalDate)));

        } catch (ParseException e) {

            Log.d(applicationName, "Unable to convert Normal Date String " + normalDate + " to MySQL Date String, Error : " + e.getLocalizedMessage());
            return mysqlDateFormat.format(new Date());
        }
    }

    public static String normalDateTimeWordsStringToMysqlDateTimeString(String normalDateTimeWords, String applicationName) {

        try {

            return mysqlDateTimeFormat.format(Objects.requireNonNull(normalDateTimeFormatWords.parse(normalDateTimeWords)));

        } catch (ParseException e) {

            Log.d(applicationName, "Unable to convert Normal Date Time Words String " + normalDateTimeWords + " to MySQL Date Time String, Error : " + e.getLocalizedMessage());
            return mysqlDateTimeFormat.format(new Date());
        }
    }

    public static String mysqlDateStringToNormalDateString(String mysqlDate, String applicationName) {

        try {

            return normalDateFormat.format(Objects.requireNonNull(mysqlDateFormat.parse(mysqlDate)));

        } catch (ParseException e) {

            Log.d(applicationName, "Unable to convert MySQL Date String " + mysqlDate + " to Normal Date String, Error : " + e.getLocalizedMessage());
            return normalDateFormat.format(new Date());
        }
    }

    public static Date mysqlDateStringToDate(String mysqlDate, Context context, String applicationName) {

        try {

            Log.d(applicationName, Objects.requireNonNull(mysqlDateFormat.parse(mysqlDate)).toString());
            return mysqlDateFormat.parse(mysqlDate);

        } catch (ParseException e) {

            ToastUtils.longToast(context, "Date Conversion Error");
            return new Date();
        }
    }

    public static Date normalDateStringToDate(String normalDate, Context context, String applicationName) {

        try {

            Log.d(applicationName, Objects.requireNonNull(normalDateFormat.parse(normalDate)).toString());
            return normalDateFormat.parse(normalDate);

        } catch (ParseException e) {

            ToastUtils.longToast(context, "Date Conversion Error");
            return new Date();
        }
    }

    public static String dateToMysqlDateTimeString(Date date) {
        return mysqlDateTimeFormat.format(date);
    }
}
