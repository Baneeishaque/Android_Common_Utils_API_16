package ndk.utils;

import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created on 22-07-2018 13:03 under Android_Common_Utils.
 */
public class FCM_Utils {

    public static void subscribe_for_topics(String[] topics, String TAG) {

        if (topics.length != 0) {

            for (String topic : topics) {
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                Log_Utils.getInstance(TAG).debug("Subscribed for topic " + topic);
            }

        }
    }

    public static void unsubscribe_from_topics(String[] topics, String TAG) {

        if (topics.length != 0) {

            for (String topic : topics) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                Log_Utils.getInstance(TAG).debug("Unsubscribed from topic " + topic);
            }

        }
    }
}
