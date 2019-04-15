package ndk.utils.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public abstract class SharedPreferences_Activity extends AppCompatActivity {

    public SharedPreferences get_shared_preferences() {
        return getApplicationContext().getSharedPreferences(configure_APPLICATION_NAME(), Context.MODE_PRIVATE);
    }

    protected abstract String configure_APPLICATION_NAME();
}
