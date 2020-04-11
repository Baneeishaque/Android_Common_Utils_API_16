package ndk.utils_android16;

import java.util.Arrays;

public class ExceptionUtils {

    public static String getExceptionDetails(Exception e) {

        return "Exception Message : " + e.getLocalizedMessage()
                + "\n" + "Exception Code : " + e.hashCode()
                + "\n" + "Exception Class : " + e.getClass()
                + "\n" + "Exception Cause : " + e.getCause()
                + "\n" + "Exception StackTrace : " + Arrays.toString(e.getStackTrace())
                + "\n" + "Exception : " + e.toString();

    }

}
