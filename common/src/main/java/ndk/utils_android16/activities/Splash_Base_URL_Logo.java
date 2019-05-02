package ndk.utils_android16.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android16.Exception_Utils;
import ndk.utils_android16.R;
import ndk.utils_android16.Server_Utils;
import ndk.utils_android16.Toast_Utils;
import ndk.utils_android16.Update_Utils;
import ndk.utils_android16.network_task.REST_Select_Task;
import ndk.utils_android16.network_task.REST_Select_Task_Wrapper;
import ndk.utils_android16.update.Update_Application;

public abstract class Splash_Base_URL_Logo extends AppCompatActivity {

    AppCompatActivity current_activity = this;

    protected abstract String configure_GET_CONFIGURATION_URL();

    protected abstract String configure_UPDATE_URL();

    protected abstract String configure_APPLICATION_NAME();

    protected abstract Class configure_NEXT_ACTIVITY_CLASS();

    protected abstract Drawable configure_LOGO();

    protected abstract Pair[] configure_NEXT_ACTIVITY_CLASS_EXTRAS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView image_logo = findViewById(R.id.img_Logo);
        image_logo.setImageDrawable(configure_LOGO());

        REST_Select_Task_Wrapper.execute_splash(this, configure_GET_CONFIGURATION_URL(), configure_APPLICATION_NAME(), new Pair[]{}, new REST_Select_Task.Async_Response_JSON_array() {

            public void processFinish(JSONArray json_array) {
                try {
                    if (Server_Utils.check_system_status(getApplicationContext(), json_array.getJSONObject(0).getString("system_status"))) {
                        if (Integer.parseInt(json_array.getJSONObject(0).getString("version_code")) != Update_Utils.getVersionCode(getApplicationContext())) {
                            Update_Application.update_application(configure_APPLICATION_NAME(), current_activity, Float.parseFloat(json_array.getJSONObject(0).getString("version_name")), configure_UPDATE_URL());
                        } else if (Float.parseFloat(json_array.getJSONObject(0).getString("version_name")) != Update_Utils.getVersionName(getApplicationContext())) {
                            Update_Application.update_application(configure_APPLICATION_NAME(), current_activity, Float.parseFloat(json_array.getJSONObject(0).getString("version_name")), configure_UPDATE_URL());
                        } else {
                            Toast_Utils.longToast(getApplicationContext(), "Latest Version...");
                            ActivityUtils.start_activity_with_string_extras_and_finish(current_activity, configure_NEXT_ACTIVITY_CLASS(), configure_NEXT_ACTIVITY_CLASS_EXTRAS());
                        }
                    }
                } catch (JSONException exception) {
                    Toast_Utils.longToast(getApplicationContext(), "JSON Response Error...");
                    Log.d(configure_APPLICATION_NAME(), Exception_Utils.get_exception_details_api16(exception));
                }
            }
        });
    }
}