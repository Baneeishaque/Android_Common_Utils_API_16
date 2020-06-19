package ndk.utils_android16.network_task;

import androidx.core.util.Pair;

public class RestGetTask {
    public static String prepareGetUrl(String URL, Pair[] keys) {
        if (keys.length != 0) {
            StringBuilder URLBuilder = new StringBuilder(URL);
            for (int i = 0; i < keys.length; i++) {
                Pair key = keys[i];
                if (i == 0) {
                    URLBuilder.append("?").append(key.first).append("=").append(key.second);
                } else {
                    URLBuilder.append("&").append(key.first).append("=").append(key.second);
                }
            }
            URL = URLBuilder.toString();
        }
        return URL;
    }
}
