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

    public static void executePostThenReturnResponse(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponse asyncResponse) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponse);

            httpApiSelectTask.execute();
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

    public static void executePostThenReturnJsonArray(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONArray);

            httpApiSelectTask.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void executePostThenReturnJsonArrayWithErrorStatus(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONArray, errorFlag);
            httpApiSelectTask.execute();
        } else {
            ToastUtils.offlineToast(context);
        }
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusCheck(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithErrorStatus(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONArray, false);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusCheck(String taskUrl, Context context, ProgressBar progressBar, ScrollView scrollView, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithErrorStatus(taskUrl, context, progressBar, scrollView, applicationName, nameValuePairs, asyncResponseJSONArray, true);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag, boolean backgroundFlag) {

        if (isOnline(context)) {
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, errorFlag, backgroundFlag);

            httpApiSelectTask.execute();
        } else {
            if (backgroundFlag) {
                Log.d(applicationName, "Internet is unavailable");
            } else {
                ToastUtils.longToast(context, "Internet is unavailable");
            }
        }
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray, boolean backgroundFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, true, backgroundFlag);
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusAndBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray, boolean backgroundFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, false, backgroundFlag);
    }

    public static void executePostThenReturnJsonArrayWithBackgroundWorkStatusAndErrorStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, errorFlag, true);
    }

    public static void executePostThenReturnJsonArrayWithoutBackgroundWorkStatusAndErrorStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, errorFlag, false);
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusCheckAndWithoutBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithoutErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, false);
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusCheckAndWithBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithoutErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, true);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusCheckAndWithoutBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, false);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusCheckAndWithBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, Pair[] nameValuePairs, HttpApiSelectTask.AsyncResponseJSONArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, nameValuePairs, asyncResponseJSONArray, true);
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
