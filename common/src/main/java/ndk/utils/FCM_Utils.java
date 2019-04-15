package ndk.utils;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;

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
                log_utils.debug("Subscribed for topic " + topic);
            }
        }
    }

    void unsubscribe_from_topics(String[] topics) {
        if (topics.length != 0) {
            for (String topic : topics) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                log_utils.debug("Unsubscribed from topic " + topic);
            }
        }
    }
}
