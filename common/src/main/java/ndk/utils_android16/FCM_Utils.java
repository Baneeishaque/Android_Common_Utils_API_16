package ndk.utils_android16;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;

import ndk.utils_android14.LogUtils;

/**
 * Created on 22-07-2018 13:03 under Android_Common_Utils.
 */
public class FCM_Utils {

    Context context;
    private Log_Utils log_utils;

    public FCM_Utils(Context context, String APPLICATION_NAME, boolean is_debug) {
        this.context = context;
        log_utils = new Log_Utils(is_debug, APPLICATION_NAME);

    }

    void subscribe_for_topics(String[] topics) {
        if (topics.length != 0) {
            for (String topic : topics) {
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                LogUtils.debug(TAG, "Subscribed for topic " + topic, is_debug);
            }
        }
    }

    void unsubscribe_from_topics(String[] topics) {
        if (topics.length != 0) {
            for (String topic : topics) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                LogUtils.debug(TAG, "Unsubscribed from topic " + topic, is_debug);
            }
        }
    }
}
