package ndk.utils_android16.network_task;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android1.NetworkUtils;
import ndk.utils_android14.NetworkUtils14;
import ndk.utils_android1.ProgressBarUtils;
import ndk.utils_android1.ToastUtils;

import static ndk.utils_android1.NetworkUtils.isOnline;

public class HttpApiSelectTaskWrapper {

    public static void executePostThenReturnResponse(String taskUrl, Context context, ProgressBar progressBar, View scrollView, String applicationName, HttpApiSelectTask.AsyncResponse asyncResponse) {

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, asyncResponse);

            httpApiSelectTask.execute();

        } else {

            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void executeGetThenReturnJsonObject(String taskUrl, Context context, ProgressBar progressBar, View scrollView, String applicationName, HttpApiSelectTask.AsyncResponseJSONObject asyncResponseJSONObject) {

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, asyncResponseJSONObject).execute();

        } else {

            ToastUtils.offlineToast(context);
        }
    }

    public static void executePostThenReturnJsonArray(String taskUrl, Context context, ProgressBar progressBar, View scrollView, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray) {

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            HttpApiSelectTask httpApiSelectTask = new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, asyncResponseJSONArray);

            httpApiSelectTask.execute();

        } else {

            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void executeGetThenReturnJsonArrayWithErrorStatus(String taskUrl, Context context, ProgressBar progressBar, View scrollView, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray, boolean errorFlag) {

        if (isOnline(context)) {

            ProgressBarUtils.showProgress(true, context, progressBar, scrollView);
            new HttpApiSelectTask(taskUrl, context, progressBar, scrollView, applicationName, asyncResponseJSONArray, errorFlag).execute();

        } else {

            ToastUtils.offlineToast(context);
        }
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusCheck(String taskUrl, Context context, ProgressBar progressBar, View scrollView, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray) {

        executeGetThenReturnJsonArrayWithErrorStatus(taskUrl, context, progressBar, scrollView, applicationName, asyncResponseJSONArray, false);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusCheck(String taskUrl, Context context, ProgressBar progressBar, View scrollView, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray) {

        executeGetThenReturnJsonArrayWithErrorStatus(taskUrl, context, progressBar, scrollView, applicationName, asyncResponseJSONArray, true);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray, boolean errorFlag, boolean backgroundFlag) {

        if (isOnline(context)) {

            new HttpApiSelectTask(taskUrl, context, applicationName, asyncResponseJSONArray, errorFlag, backgroundFlag).execute();

        } else {

            if (backgroundFlag) {

                Log.d(applicationName, "Internet is unavailable");

            } else {

                ToastUtils.longToast(context, "Internet is unavailable");
            }
        }
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray, boolean backgroundFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, asyncResponseJSONArray, true, backgroundFlag);
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusAndBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray, boolean backgroundFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, asyncResponseJSONArray, false, backgroundFlag);
    }

    public static void executePostThenReturnJsonArrayWithBackgroundWorkStatusAndErrorStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray, boolean errorFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, asyncResponseJSONArray, errorFlag, true);
    }

    public static void executePostThenReturnJsonArrayWithoutBackgroundWorkStatusAndErrorStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray, boolean errorFlag) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatus(taskUrl, context, applicationName, asyncResponseJSONArray, errorFlag, false);
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusCheckAndWithoutBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithoutErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, asyncResponseJSONArray, false);
    }

    public static void executePostThenReturnJsonArrayWithoutErrorStatusCheckAndWithBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithoutErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, asyncResponseJSONArray, true);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusCheckAndWithoutBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, asyncResponseJSONArray, false);
    }

    public static void executePostThenReturnJsonArrayWithErrorStatusCheckAndWithBackgroundWorkStatusCheck(String taskUrl, Context context, String applicationName, HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJSONArray) {

        executePostThenReturnJsonArrayWithErrorStatusAndBackgroundWorkStatusCheck(taskUrl, context, applicationName, asyncResponseJSONArray, true);
    }

    public static void performSplashScreenThenReturnJsonArray(final Context context, final String taskUrl, final String applicationName, final HttpApiSelectTask.AsyncResponseJsonArray asyncResponseJsonArray) {

        if (NetworkUtils.isOnline(context)) {

            new HttpApiSelectTask(taskUrl, context, applicationName, asyncResponseJsonArray).execute();

        } else {

            View.OnClickListener retryFailedNetworkTask = view -> performSplashScreenThenReturnJsonArray(context, taskUrl, applicationName, asyncResponseJsonArray);

            NetworkUtils14.displayOfflineLongNoFabBottomSnackBar(((AppCompatActivity) context).getWindow().getDecorView(), retryFailedNetworkTask);
        }
    }
}
