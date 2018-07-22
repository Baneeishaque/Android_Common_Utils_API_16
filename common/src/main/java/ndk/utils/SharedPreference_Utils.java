package ndk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.Pair;

public class SharedPreference_Utils {

    public static void commit_Shared_Preferences(Context context, String application_name, Pair[] shared_preference_pairs) {

        if (shared_preference_pairs.length != 0) {
            SharedPreferences settings = context.getSharedPreferences(application_name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            for (Pair shared_preference_pair : shared_preference_pairs) {
                editor.putString(shared_preference_pair.first != null ? shared_preference_pair.first.toString() : null, shared_preference_pair.second != null ? shared_preference_pair.second.toString() : null);
            }
            editor.apply();
        }
    }

    public static boolean is_first_run(Context context, String application_name) {
        SharedPreferences settings = context.getSharedPreferences(application_name, Context.MODE_PRIVATE);
        if (settings.getString("first_run", String.valueOf(true)).equals(String.valueOf(true))) {
            // Do first run stuff here then set 'firstrun' as false
            //TODO : Implement this interface
//            first_run_actions.on_first_run();

            // using the following line to edit/commit prefs
            commit_Shared_Preferences(context, application_name, new Pair[]{new Pair<>("first_run", String.valueOf(false))});
            return true;
        }
        return false;
    }

    public interface first_run_actions {
        void on_first_run();
    }
}
