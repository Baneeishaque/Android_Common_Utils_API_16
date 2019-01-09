package ndk.utils.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ndk.utils.TODO_Utils;

/**
 * Created on 24-08-2018 23:43 under VLottery.
 */
public abstract class Context_Activity extends AppCompatActivity {

    public Context activity_context = this;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TODO_Utils.display_TODO_no_FAB_SnackBar(activity_context);
        return true;

    }

    public SharedPreferences get_shared_preferences() {
        return getApplicationContext().getSharedPreferences(configure_APPLICATION_NAME(), Context.MODE_PRIVATE);
    }

    protected abstract String configure_APPLICATION_NAME();
}
