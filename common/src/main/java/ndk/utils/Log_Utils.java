package ndk.utils;

import android.util.Log;

/**
 * Created on 18-07-2018 20:31 under DStock.
 */
public class Log_Utils {

    public static void debug(String tag, String message, boolean is_debug) {
        if (is_debug) {
            Log.d(tag, message);
        }
    }

}
