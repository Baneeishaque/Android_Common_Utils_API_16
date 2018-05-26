package ndk.utils.activities;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import ndk.utils.Activity_Utils;
import ndk.utils.Exception_Utils;
import ndk.utils.R;
import ndk.utils.Server_Utils;
import ndk.utils.Toast_Utils;
import ndk.utils.Update_Utils;
import ndk.utils.network_task.REST_Select_Task;
import ndk.utils.network_task.REST_Select_Task_Wrapper;
import ndk.utils.update.Update_Application;

//TODO : Full screen splash
//TODO : Ensure Overriding of fields with appropriate messages
//TODO : Implement hiding of fields - in case of layout
//TODO : Develop tests

public class Splash_Base extends AppCompatActivity {

    final String API_GET_CONFIGURATION_URL, API_UPDATE_URL, APPLICATION_NAME;
    final Class NEXT_ACTIVITY_CLASS;
    AppCompatActivity current_activity = this;

    public Splash_Base(String api_get_configuration_url, String api_update_url, String application_name, Class next_activity_class) {
        API_GET_CONFIGURATION_URL = api_get_configuration_url;
        API_UPDATE_URL = api_update_url;
        APPLICATION_NAME = application_name;
        NEXT_ACTIVITY_CLASS = next_activity_class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        REST_Select_Task_Wrapper.execute_splash(this, API_GET_CONFIGURATION_URL, APPLICATION_NAME, new Pair[]{}, new REST_Select_Task.Async_Response_JSON_array() {

            public void processFinish(JSONArray json_array) {
                try {
                    if (Server_Utils.check_system_status(getApplicationContext(), json_array.getJSONObject(0).getString("system_status"))) {
                        if (Integer.parseInt(json_array.getJSONObject(0).getString("version_code")) != Update_Utils.getVersionCode(getApplicationContext())) {
                            Update_Application.update_application(APPLICATION_NAME, current_activity, Float.parseFloat(json_array.getJSONObject(0).getString("version_name")), API_UPDATE_URL);
                        } else if (Float.parseFloat(json_array.getJSONObject(0).getString("version_name")) != Update_Utils.getVersionName(getApplicationContext())) {
                            Update_Application.update_application(APPLICATION_NAME, current_activity, Float.parseFloat(json_array.getJSONObject(0).getString("version_name")), API_UPDATE_URL);
                        } else {
                            Toast_Utils.longToast(getApplicationContext(), "Latest Version...");
                            launch_Next_Screen();
                        }
                    }
                } catch (JSONException exception) {
                    Toast_Utils.longToast(getApplicationContext(), "JSON Response Error...");
                    Log.d(APPLICATION_NAME, Exception_Utils.get_exception_details(exception));
                }
            }
        });


    }

    protected void launch_Next_Screen() {
        Activity_Utils.start_activity_with_finish(current_activity, NEXT_ACTIVITY_CLASS);
    }

}
