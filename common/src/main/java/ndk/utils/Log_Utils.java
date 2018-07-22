package ndk.utils;

import android.util.Log;

/**
 * Created on 18-07-2018 20:31 under DStock.
 */
public class Log_Utils {

    private static Log_Utils instance;
    private String tag;

    private Log_Utils(String tag) {
        this.tag = tag;
    }

    public static synchronized Log_Utils getInstance(String tag) {
        if (instance == null) {
            instance = new Log_Utils(tag);
        }
        return instance;
    }

    public void debug(String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

}
