package ndk.utils.update;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import ndk.utils.Activity_Utils;
import ndk.utils.Application_Utils;
import ndk.utils.Log_Utils;
import ndk.utils.Network_Utils;
import ndk.utils.Server_Utils;
import ndk.utils.network_task.update.Application_VCS_Utils;

public class Update_Utils {

    public static void attempt_Update_Check(final String application_name, final AppCompatActivity current_activity, final String URL, Update_Check_Task Update_Task, final String update_URL, final Class next_activity) {

        if (Update_Task != null) {
            return;
        }

        Network_Utils network_utils = new Network_Utils(current_activity, application_name, is_debug)
        if (network_utils.isOnline()) {
            Update_Task = new Update_Check_Task(application_name, current_activity, URL, Update_Task, update_URL, next_activity, is_debug);
            Update_Task.execute((Void) null);
        } else {
            final Update_Check_Task final_Update_Task = Update_Task;
            View.OnClickListener retry_Failed_Network_Task = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attempt_Update_Check(application_name, current_activity, URL, final_Update_Task, update_URL, next_activity);
                }
            };
            network_utils.display_Long_no_FAB_no_network_bottom_SnackBar(current_activity.getWindow().getDecorView(), retry_Failed_Network_Task);
        }

    }
}

class Update_Check_Task extends AsyncTask<Void, Void, String[]> {

    private AppCompatActivity current_activity;
    private Class next_activity;
    private String URL, application_name, update_URL;
    private Update_Check_Task Update_Task;
    private boolean is_debug;

    public Update_Check_Task(String application_name, AppCompatActivity current_activity, String URL, Update_Check_Task Update_Task, String update_URL, Class next_activity, boolean is_debug) {
        this.current_activity = current_activity;
        this.URL = URL;
        this.Update_Task = Update_Task;
        this.application_name = application_name;
        this.update_URL = update_URL;
        this.next_activity = next_activity;
        this.is_debug = is_debug;
    }

    public static void update_application(final String application_name, final AppCompatActivity current_activity, final float version_name, final String update_URL) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(current_activity);
        builder1.setMessage("New version is available, please update...").setCancelable(false)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Application_VCS_Utils.download_and_install_apk(application_name, version_name, update_URL, current_activity, application_name);
                    }
                });
        AlertDialog alert = builder1.create();
        alert.setTitle("Warning!");
        alert.show();
    }

    @Override
    protected String[] doInBackground(Void... params) {

        return Application_Utils.get_server_version(URL);
    }

    @Override
    protected void onPostExecute(final String[] network_action_response_array) {

        Update_Task = null;

        Log_Utils log_utils = new Log_Utils(is_debug, application_name);
        log_utils.debug(network_action_response_array[0]);
        log_utils.debug(network_action_response_array[1]);

        if (network_action_response_array[0].equals("1")) {
            Network_Utils network_utils = new Network_Utils(current_activity, application_name, is_debug);
            network_utils.display_Friendly_Exception_Message(network_action_response_array[1]);
            current_activity.finish();
        } else {

            try {
                JSONArray json_Array = new JSONArray(network_action_response_array[1]);

                if (Server_Utils.check_system_status(current_activity, json_Array.getJSONObject(0).getString("system_status"))) {

                    if (Integer.parseInt(json_Array.getJSONObject(0).getString("version_code")) != Application_Utils.getVersionCode(current_activity)) {
                        update_application(application_name, current_activity, Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")), update_URL);

                    } else {
                        if (Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")) != Application_Utils.getVersionName(current_activity)) {
                            update_application(application_name, current_activity, Float.parseFloat(json_Array.getJSONObject(0).getString("version_name")), update_URL);
                        } else {
                            Toast.makeText(current_activity, "Latest Version...", Toast.LENGTH_SHORT).show();
                            // After completing http call will close this activity and launch main activity
                            Activity_Utils activity_utils = new Activity_Utils(current_activity, application_name, is_debug);
                            activity_utils.start_activity_with_finish_and_tab_index(next_activity, 0);
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