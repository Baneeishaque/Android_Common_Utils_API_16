package ndk.utils;

import android.content.Context;

import org.json.JSONObject;

public class Error_Utils {

    Context context;
    private Log_Utils log_utils;

    public Error_Utils(Context context, String APPLICATION_NAME, boolean is_debug) {
        this.context = context;
        log_utils = new Log_Utils(is_debug, APPLICATION_NAME);

    }

    public void display_Exception(Exception exception) {
        display_Error_Toast(context);
        log_utils.debug(Exception_Utils.get_exception_details(exception));
    }

    void display_JSON_Field_Miss(JSONObject json_object) {
        Toast_Utils.longToast(context, "Error...");
        log_utils.debug("Error, Application_Utils JSON : " + String.valueOf(json_object));
    }

    private static void display_Error_Toast(Context application_context) {
        Toast_Utils.longToast(application_context, "Error...");
    }
}
