package ndk.utils;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created on 24-08-2018 20:50 under VLottery.
 */
public class Error_Utils {

    public static void display_Exception(Context application_context, Exception exception, String APPLICATION_NAME, boolean is_debug) {
        display_Error_Toast(application_context);
        Log_Utils.debug(APPLICATION_NAME, Exception_Utils.get_exception_details(exception), is_debug);
    }

    public static void display_JSON_Field_Miss(Context application_context, JSONObject json_object, String APPLICATION_NAME, boolean is_debug) {
        Toast_Utils.longToast(application_context, "Error...");
        Log_Utils.debug(APPLICATION_NAME, "Error, Check JSON : " + String.valueOf(json_object), is_debug);
    }

    private static void display_Error_Toast(Context application_context) {
        Toast_Utils.longToast(application_context, "Error...");
    }
}
