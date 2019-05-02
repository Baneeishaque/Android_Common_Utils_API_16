package ndk.utils_android16;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created on 24-08-2018 21:48 under VLottery.
 */
public class Server_Utils {

    public static boolean check_system_status(Context context, String system_status) {

        if (Integer.parseInt(system_status) == 0) {
            close_application((AppCompatActivity) context);
        } else if (Integer.parseInt(system_status) == 1) {
            Toast.makeText(context, "System Status is OK", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;

    }

    public static void close_application(AppCompatActivity activity) {

        Toast.makeText(activity, "System is in Maintenance, Try Again later...", Toast.LENGTH_LONG).show();
        activity.finish();

    }
}
