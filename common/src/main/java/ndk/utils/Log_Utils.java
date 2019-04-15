package ndk.utils;

import android.util.Log;

public class Log_Utils {

    private boolean is_debug;
    private String APPLICATION_NAME;

    public Log_Utils(boolean is_debug, String APPLICATION_NAME) {
        this.is_debug = is_debug;
        this.APPLICATION_NAME = APPLICATION_NAME;
    }

//    void debug(String tag, String message) {
//        if (is_debug) {
//            Log.d(tag, message);
//        }
//    }

    public void debug(String message) {
        if (is_debug) {
            Log.d(APPLICATION_NAME, message);
        }
    }

}
