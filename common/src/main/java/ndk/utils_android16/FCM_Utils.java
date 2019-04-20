package ndk.utils_android16;

import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created on 22-07-2018 13:03 under Android_Common_Utils.
 */
public class FCM_Utils {

    public static void subscribe_for_topics(String[] topics, String TAG, boolean is_debug) {

        if (topics.length != 0) {

            for (String topic : topics) {
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                Log_Utils.debug(TAG, "Subscribed for topic " + topic, is_debug);
            }

        }
    }

    public static void unsubscribe_from_topics(String[] topics, String TAG, boolean is_debug) {

        if (topics.length != 0) {

            for (String topic : topics) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                Log_Utils.debug(TAG, "Unsubscribed from topic " + topic, is_debug);
            }

        }
    }
}
