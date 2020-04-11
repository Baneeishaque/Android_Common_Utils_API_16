package ndk.utils_android16;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void longToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void shortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void errorToast(Context context) {
        longToast(context, "Error...");
    }

    public static void offlineToast(Context context) {
        longToast(context, "Internet is unavailable...");
    }

    public static void noEntriesToast(Context context) {
        longToast(context, "No Entries...");
    }
}
