package ndk.utils.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.onehilltech.metadata.ManifestMetadata;

import org.json.JSONArray;
import org.json.JSONException;

import ndk.utils.Activity_Utils;
import ndk.utils.R;
import ndk.utils.Server_Utils;
import ndk.utils.Toast_Utils;
import ndk.utils.Update_Utils;
import ndk.utils.network_task.REST_Select_Task;
import ndk.utils.network_task.REST_Select_Task_Wrapper;
import ndk.utils.update.Update_Application;

//TODO:Full screen splash

public class Splash extends AppCompatActivity {

    Context application_context, activity_context = this;
    private REST_Select_Task REST_select_task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        application_context = getApplicationContext();

        try {
            REST_Select_Task_Wrapper.execute_splash(this, ManifestMetadata.get(this).getValue("SELECT_CONFIGURATION_URL"), ManifestMetadata.get(this).getValue("APPLICATION_NAME"), new Pair[]{}, new REST_Select_Task.Async_Response_JSON_array() {

                public void processFinish(JSONArray json_array_with_error_status) {
                    try {
                        if (Server_Utils.check_system_status(getApplicationContext(), json_array_with_error_status.getJSONObject(1).getString("system_status"))) {
                            if (Integer.parseInt(json_array_with_error_status.getJSONObject(1).getString("version_code")) != Update_Utils.getVersionCode(getApplicationContext())) {
                                Update_Application.update_application(ManifestMetadata.get(getApplicationContext()).getValue("APPLICATION_NAME"), Splash.this, Float.parseFloat(json_array_with_error_status.getJSONObject(0).getString("version_name")), ManifestMetadata.get(getApplicationContext()).getValue("APK_UPDATE_URL"), ManifestMetadata.get(getApplicationContext()).getValue("APPLICATION_NAME"));
                            } else if (Float.parseFloat(json_array_with_error_status.getJSONObject(1).getString("version_name")) != Update_Utils.getVersionName(getApplicationContext())) {
                                Update_Application.update_application(ManifestMetadata.get(getApplicationContext()).getValue("APPLICATION_NAME"), Splash.this, Float.parseFloat(json_array_with_error_status.getJSONObject(0).getString("version_name")), ManifestMetadata.get(getApplicationContext()).getValue("APK_UPDATE_URL"), ManifestMetadata.get(getApplicationContext()).getValue("APPLICATION_NAME"));
                            } else {
                                Toast_Utils.longToast(getApplicationContext(), "Latest Version...");
                                launch_Next_Screen();
                            }
                        }
                    } catch (JSONException exception) {
                        Toast_Utils.longToast(getApplicationContext(), "JSON Response Error");
                        try {
                            Log.d(ManifestMetadata.get(getApplicationContext()).getValue("APPLICATION_NAME"), exception.getLocalizedMessage());
                        } catch (PackageManager.NameNotFoundException e) {
                            Toast_Utils.longToast(getApplicationContext(), "<meta-data> Error : " + e.getLocalizedMessage());
                            Log.d("<meta-data> Error", e.getLocalizedMessage());
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Toast_Utils.longToast(getApplicationContext(), "<meta-data> Error : " + e.getLocalizedMessage());
                        Log.d("<meta-data> Error", e.getLocalizedMessage());
                    }
                }
            });


        } catch (PackageManager.NameNotFoundException exception) {
            Toast_Utils.longToast(getApplicationContext(), "<meta-data> Error : " + exception.getLocalizedMessage());
            Log.d("<meta-data> Error", exception.getLocalizedMessage());
        }

    }

    protected void launch_Next_Screen() {
        try {
            Activity_Utils.start_activity_with_finish(activity_context, Class.forName(ManifestMetadata.get(getApplicationContext()).getValue("NEXT_ACTIVITY_CLASS")));
        } catch (ClassNotFoundException | PackageManager.NameNotFoundException e) {
            Toast_Utils.longToast(getApplicationContext(), "Next Activity Error : " + e.getLocalizedMessage());
            try {
                Log.d(ManifestMetadata.get(getApplicationContext()).getValue("APPLICATION_NAME"), e.getLocalizedMessage());
            } catch (PackageManager.NameNotFoundException exc) {
                Toast_Utils.longToast(getApplicationContext(), "<meta-data> Error : " + exc.getLocalizedMessage());
                Log.d("<meta-data> Error", exc.getLocalizedMessage());
            }
        }
    }
}
