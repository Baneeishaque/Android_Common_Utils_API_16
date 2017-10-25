package ndk.prism.common_utils;

import android.content.Context;

/**
 * Created by Babu on 05-09-2017.
 */

public class Network_Utils {
    //TODO : Improve to handle exception objects
    public static void display_Friendly_Exception_Message(Context context, String network_exception_message) {
        if (network_exception_message.contains("IOException")) {
            Toast_Utils.longToast(context, "Check your network connection");
        }

    }
}
