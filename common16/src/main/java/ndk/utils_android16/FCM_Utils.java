package ndk.utils_android16;

import com.google.firebase.messaging.FirebaseMessaging;

import ndk.utils_android1.LogUtils1;

/**
 * Created on 22-07-2018 13:03 under Android_Common_Utils.
 */
public class FCM_Utils {

    void subscribe_for_topics(String[] topics, String tag) {

        if (topics.length != 0) {

            for (String topic : topics) {

                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                LogUtils1.debug(tag, "Subscribed for topic " + topic);
            }
        }
    }

    void unsubscribe_from_topics(String[] topics, String tag) {

        if (topics.length != 0) {

            for (String topic : topics) {

                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                LogUtils1.debug(tag, "Unsubscribed from topic " + topic);
            }
        }
    }
}
