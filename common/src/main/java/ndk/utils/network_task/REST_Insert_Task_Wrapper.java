package ndk.utils.network_task;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import ndk.utils.ProgressBar_Utils;
import ndk.utils.Toast_Utils;

import static ndk.utils.Network_Utils.isOnline;

public class REST_Insert_Task_Wrapper {

    public static void execute(Context context, String task_URL, AppCompatActivity current_activity, View mProgressView, View mLoginFormView, String application_Name, Pair[] name_value_pairs, View view_to_focus_on_error, Class next_activity, String APPLICATION_NAME) {

        Log.d(APPLICATION_NAME, "REST Insert TASK URL : " + task_URL);

        if (isOnline(context)) {

            REST_Insert_Task rest_insert_task = null;

            ProgressBar_Utils.showProgress(true, context, mProgressView, mLoginFormView);

            rest_insert_task = new REST_Insert_Task(task_URL, rest_insert_task, current_activity, mProgressView, mLoginFormView, application_Name, name_value_pairs, view_to_focus_on_error, next_activity);

            rest_insert_task.execute((Void) null);
        } else {
            Toast_Utils.longToast(context, "Internet is unavailable");
        }
    }
}
