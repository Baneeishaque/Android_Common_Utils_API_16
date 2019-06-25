package ndk.utils_android16.network_task.update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

public class CheckAndUpdateTaskWrapper {

    //TODO : Get Task Objects

    public static void executeCheckAndUpdateWithTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, int tabIndex, Pair[] nextClassExtras) {
        CheckAndUpdateTask checkAndUpdateTask = new CheckAndUpdateTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, true, tabIndex, nextClassExtras);

        checkAndUpdateTask.execute();
    }

    public static void executeCheckAndUpdateWithZeroTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, Pair[] nextClassExtras) {
        executeCheckAndUpdateWithTabIndexTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, 0, nextClassExtras);
    }

    public static void executeCheckAndUpdateWithoutTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, Pair[] nextClassExtras) {

        getCheckAndUpdateWithoutTabIndexTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, nextClassExtras).execute();
    }

    public static CheckAndUpdateTask getCheckAndUpdateWithoutTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, Pair[] nextClassExtras) {

        return new CheckAndUpdateTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, false, 0, nextClassExtras);
    }
}
