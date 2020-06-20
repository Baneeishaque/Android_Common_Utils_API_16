package ndk.utils_android16.network_task;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import ndk.utils_android16.ProgressBarUtils;
import ndk.utils_android16.ToastUtils;

import static ndk.utils_android16.NetworkUtils.FurtherActions;
import static ndk.utils_android16.NetworkUtils.isOnline;

public class RestInsertTaskWrapper {

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, Class nextActivity) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressView, formView);

            RestInsertTask restInsertTask = new RestInsertTask(taskUrl, currentActivity, progressView, formView, applicationName, nameValuePairs, viewToFocusOnError, nextActivity);

            restInsertTask.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, Class nextActivity, Pair[] nextClassExtras) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBarView, formView);

            RestInsertTask restInsertTask = new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, nextActivity, nextClassExtras);

            restInsertTask.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, EditText[] editTextsToClear) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBarView, formView);

            RestInsertTask restInsertTask = new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, editTextsToClear);

            restInsertTask.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, EditText[] editTextsToClear, FurtherActions furtherActions) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBarView, formView);

            new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, editTextsToClear, furtherActions).execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBarView, formView);

            RestInsertTask restInsertTask = new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError);

            restInsertTask.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, FurtherActions furtherActions) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBarView, formView);

            new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, furtherActions).execute();

        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }
}
