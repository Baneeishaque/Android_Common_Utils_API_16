package ndk.utils_android16.activities;

import android.os.Bundle;

import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.R;
import ndk.utils_android16.ServerUtils;
import ndk.utils_android16.ToastUtils;
import ndk.utils_android16.UpdateUtils;
import ndk.utils_android16.network_task.HttpApiSelectTask;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;
import ndk.utils_android16.network_task.update.CheckAndUpdateTaskWrapper;

//TODO : Full screen splash
//TODO : Implement hiding of fields - in case of layout
//TODO : Develop tests

public abstract class SplashWithAutomatedUpdateActivity extends ContextActivity {

    protected abstract String configure_GET_CONFIGURATION_URL();

    protected abstract String configure_UPDATE_URL();

    protected abstract Class configure_NEXT_ACTIVITY_CLASS();

    protected abstract Pair[] configure_NEXT_ACTIVITY_CLASS_EXTRAS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initializeScreen();

        HttpApiSelectTaskWrapper.performSplashScreenThenReturnJsonArray(this, configure_GET_CONFIGURATION_URL(), configure_APPLICATION_NAME(), new Pair[]{}, new HttpApiSelectTask.AsyncResponseJSONArray() {

            CheckAndUpdateTaskWrapper.getCheckAndUpdateWithoutTabIndexTask(configure_APPLICATION_NAME(), this, configure_GET_CONFIGURATION_URL(), configure_UPDATE_URL(), configure_NEXT_ACTIVITY_CLASS(), configure_SECURITY_FLAG(), configure_NEXT_ACTIVITY_CLASS_EXTRAS()).checkAndUpdate(jsonArray);
        });
    }

    public void initializeScreen() {
        setContentView(R.layout.splash);
    }

    protected abstract boolean configure_SECURITY_FLAG();

    protected abstract String configure_APPLICATION_NAME();
}
