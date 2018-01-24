package ndk.utils.network_task.update;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import ndk.utils.Activity_Utils;
import ndk.utils.Server_Utils;
import ndk.utils.Update_Utils;

import static ndk.utils.Network_Utils.display_Friendly_Exception_Message;
import static ndk.utils.update.Update_Application.update_application;

/**
 * Created by Nabeel on 21-01-2018.
 */

public class Update_Check_Task extends AsyncTask<Void, Void, String[]> {

    private AppCompatActivity current_activity;
    private Class next_activity;
    private String URL, TAG,application_name,update_URL;
    private Update_Check_Task Update_Task;

    public Update_Check_Task(String application_name,AppCompatActivity current_activity, String URL, String TAG, Update_Check_Task Update_Task,String update_URL,Class next_activity) {
        this.current_activity = current_activity;
        this.URL = URL;
        this.TAG = TAG;
        this.Update_Task = Update_Task;
        this.application_name=application_name;
        this.update_URL=update_URL;
        this.next_activity=next_activity;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        return Update_Utils.get_server_version(URL);
    }

    @Override
    protected void onPostExecute(final String[] network_action_response_array) {
        Update_Task = null;

        Log.d(TAG, network_action_response_array[0]);
        Log.d(TAG, network_action_response_array[1]);

        if (network_action_response_array[0].equals("1")) {
            display_Friendly_Exception_Message(current_activity, network_action_response_array[1]);
            Log.d(TAG, network_action_response_array[1]);
            current_activity.finish();
        } else {

            try {
                JSONArray json_Array = new JSONArray(network_action_response_array[1]);

                if (Server_Utils.check_system_status(current_activity, json_Array.getJSONObject(0).getString("system_status"))) {

                    if (Integer.parseInt(json_Array.getJSONObject(0).getString("version_code")) != Update_Utils.getVersionCode(current_activity)) {
                        update_application(application_name,current_activity,Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")),update_URL,TAG);

                    } else {
                        if (Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")) != Update_Utils.getVersionName(current_activity)) {
                            update_application(application_name,current_activity,Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")),update_URL,TAG);
                        } else {
                            Toast.makeText(current_activity, "Latest Version...", Toast.LENGTH_SHORT).show();
                            // After completing http call will close this activity and launch main activity
                            Activity_Utils.start_activity_with_finish_and_tab_index(current_activity, next_activity, 0);
//                                Activity_Utils.start_activity_with_finish(current_activity,Dashboard_Page.class);
                        }
                    }
                }

            } catch (JSONException e) {
                Toast.makeText(current_activity, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, e.getLocalizedMessage());
            }
        }
    }

}