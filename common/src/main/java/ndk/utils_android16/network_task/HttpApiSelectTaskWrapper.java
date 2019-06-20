package ndk.utils_android16.network_task;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import ndk.utils_android16.NetworkUtils;
import ndk.utils_android16.ProgressBarUtils;
import ndk.utils_android16.ToastUtils;

import static ndk.utils_android16.NetworkUtils.isOnline;

public class HttpApiSelectTaskWrapper {

    public static void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.AsyncResponse async_response) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, mProgressView, mLoginFormView);
            HttpApiSelectTask rest_select_task = new HttpApiSelectTask(task_URL, context, mProgressView, mLoginFormView, application_Name, name_value_pairs, async_response);

            rest_select_task.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void executePostThenReturnJsonObject(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONObject asyncResponseJSONObject) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONObject);
            httpApiSelectTask.execute();
        } else {
            ToastUtils.offlineToast(context);
        }
    }

    public static void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.AsyncResponseJSONArray async_response_json_array) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, mProgressView, mLoginFormView);
            HttpApiSelectTask rest_select_task = new HttpApiSelectTask(task_URL, context, mProgressView, mLoginFormView, application_Name, name_value_pairs, async_response_json_array);

            rest_select_task.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void executePostThenReturnJsonArray(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONArray, errorFlag);
            httpApiSelectTask.execute();
        } else {
            ToastUtils.offlineToast(context);
        }
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusCheck(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArray(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONArray, false);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusCheck(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArray(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONArray, true);
    }

    public static void execute(String task_URL, Context context, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.AsyncResponseJSONArray async_response_json_array, boolean error_flag, boolean background_flag) {

        if (isOnline(context)) {

            HttpApiSelectTask rest_select_task = new HttpApiSelectTask(task_URL, context, application_Name, name_value_pairs, async_response_json_array, error_flag, background_flag);

            rest_select_task.execute();
        } else {
            if (background_flag) {
                Log.d(application_Name, "Internet is unavailable");
            } else {
                ToastUtils.longToast(context, "Internet is unavailable");
            }
        }
    }

    public static void performSplashScreenThenReturnJsonArray(final Context context, final String taskUrl, final String applicationName, final Pair[] nameValuePairs, final HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        if (NetworkUtils.isOnline(context)) {
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray);
            httpApiSelectTask.execute();
        } else {
            View.OnClickListener retryFailedNetworkTask = view -> performSplashScreenThenReturnJsonArray(context, taskUrl, applicationName, nameValuePairs, asyncResponseJSONArray);
            NetworkUtils.displayOfflineLongNoFabBottomSnackBar(((AppCompatActivity) context).getWindow().getDecorView(), retryFailedNetworkTask);
        }
    }

}
