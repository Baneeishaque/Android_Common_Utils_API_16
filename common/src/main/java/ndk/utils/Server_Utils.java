package ndk.utils;

import android.content.Context;

public class Server_Utils {

    Context context;
    private Activity_Utils activity_utils;

    public Server_Utils(Context context, String APPLICATION_NAME, boolean is_Debug) {
        this.context = context;
        this.activity_utils = new Activity_Utils(context, APPLICATION_NAME, is_Debug);
    }

    boolean check_system_status(String system_status) {
        if (Integer.parseInt(system_status) == 0) {
            activity_utils.close_activity_with_message("System is in Maintenance, Try Again later...");
        } else if (Integer.parseInt(system_status) == 1) {
            Toast_Utils.longToast(context, "System Status is OK");
            return true;
        }
        return false;
    }

}
