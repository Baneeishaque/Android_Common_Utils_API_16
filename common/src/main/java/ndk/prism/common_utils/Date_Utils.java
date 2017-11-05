package ndk.prism.common_utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Date_Utils {

    public static SimpleDateFormat mysql_Date_Format = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
    public static SimpleDateFormat normal_Date_Format = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
    public static SimpleDateFormat normal_Date_Format_words = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.UK);

    public static String get_current_date_string_in_mysql_format() {
        return mysql_Date_Format.format(new Date());
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String date_to_mysql_date_string(Date date) {
        return mysql_Date_Format.format(date);
    }

    public static String date_to_normal_date_string(Date date) {
        return normal_Date_Format.format(date);
    }

    public static String mysql_date_string_to_date_string(String mysql_date) {
        try {
            return normal_Date_Format.format(normal_Date_Format.parse(mysql_date));
        } catch (ParseException e) {
            return  normal_Date_Format.format(new Date());
        }
    }

}
