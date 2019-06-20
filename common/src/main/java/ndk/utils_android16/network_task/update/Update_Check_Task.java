package ndk.utils_android16.network_task.update;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android16.ServerUtils;
import ndk.utils_android16.UpdateUtils;

import static ndk.utils_android16.NetworkUtils.displayFriendlyExceptionMessage;
import static ndk.utils_android16.update.UpdateApplication.updateApplication;

public class Update_Check_Task extends AsyncTask<Void, Void, String[]> {

    private AppCompatActivity current_activity;
    private Class next_activity;
    private String URL, application_name, update_URL;
    private Update_Check_Task Update_Task;

    public Update_Check_Task(String application_name, AppCompatActivity current_activity, String URL, Update_Check_Task Update_Task, String update_URL, Class next_activity) {
        this.current_activity = current_activity;
        this.URL = URL;
        this.Update_Task = Update_Task;
        this.application_name = application_name;
        this.update_URL = update_URL;
        this.next_activity = next_activity;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        return UpdateUtils.getServerVersion(URL, applicationName);
    }

    @Override
    protected void onPostExecute(final String[] network_action_response_array) {
        Update_Task = null;

        Log.d(application_name, network_action_response_array[0]);
        Log.d(application_name, network_action_response_array[1]);

        if (network_action_response_array[0].equals("1")) {
            displayFriendlyExceptionMessage(current_activity, network_action_response_array[1]);
            Log.d(application_name, network_action_response_array[1]);
            current_activity.finish();
        } else {

            try {
                JSONArray json_Array = new JSONArray(network_action_response_array[1]);

                if (ServerUtils.checkSystemStatus(current_activity, json_Array.getJSONObject(0).getString("system_status"), applicationName)) {

                    if (Integer.parseInt(json_Array.getJSONObject(0).getString("version_code")) != UpdateUtils.getVersionCode(current_activity)) {
                        updateApplication(application_name, current_activity, Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")), update_URL);

                    } else {
                        if (Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")) != UpdateUtils.getVersionName(current_activity)) {
                            updateApplication(application_name, current_activity, Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")), update_URL);
                        } else {
                            Toast.makeText(current_activity, "Latest Version...", Toast.LENGTH_SHORT).show();
                            // After completing http call will close this activity and launch main activity
                            ActivityUtils.startActivityWithFinishAndTabIndex(current_activity, next_activity, 0);
                        }
                    }
                }

            } catch (JSONException e) {
                Toast.makeText(current_activity, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(application_name, e.getLocalizedMessage());
            }
        }
    }

}