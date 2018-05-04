package ndk.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Arrays;

public class Exception_Utils {
    public static String get_exception_details(Exception e) {
        return "Exception Message : " + e.getLocalizedMessage()
                + "\n" + "Exception Code : " + e.hashCode()
                + "\n" + "Exception Class : " + e.getClass()
                + "\n" + "Exception Cause : " + e.getCause()
                + "\n" + "Exception StackTrace : " + Arrays.toString(e.getStackTrace())
                + "\n" + "Exception Suppressed : " + Arrays.toString(e.getSuppressed())
                + "\n" + "Exception : " + e.toString();

    }

    public static void display_exception_toast(Context context, Exception e) {
        Toast.makeText(context, get_exception_details(e), Toast.LENGTH_LONG).show();
    }
}
