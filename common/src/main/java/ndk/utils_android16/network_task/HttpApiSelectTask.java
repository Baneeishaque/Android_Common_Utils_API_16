package ndk.utils_android16.network_task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils_android14.LogUtilsWrapperBase;
import ndk.utils_android16.ExceptionUtils;
import ndk.utils_android16.NetworkUtils;
import ndk.utils_android16.ToastUtils;

import static ndk.utils_android16.NetworkUtils.performHttpClientPostTask;
import static ndk.utils_android16.ProgressBarUtils.showProgress;

public class HttpApiSelectTask extends AsyncTask<Void, Void, String[]> {

    private String url, tag;
    private Context context;
    private ProgressBar progressBar;
    private ScrollView scrollView;

    private int progressFlag = 0;
    private int responseFlag = 0;
    private int splashFlag = 0;
    private boolean backgroundFlag = false;

    private Pair[] nameValuePairs;

    private boolean errorFlag = true;
    private AsyncResponseJSONArray asyncResponseJSONArray = null;
    private AsyncResponse asyncResponse = null;
    private AsyncResponseJSONObject asyncResponseJSONObject = null;

    public HttpApiSelectTask(String url, Context context, ProgressBar progressBar, ScrollView scrollView, String tag, Pair[] nameValuePairs, AsyncResponseJSONArray asyncResponseJSONArray
    ) {
        this.url = url;
        this.context = context;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
    }

    HttpApiSelectTask(String url, Context context, ProgressBar progressBar, ScrollView scrollView, String tag, Pair[] nameValuePairs, AsyncResponse asyncResponse) {

        this.url = url;
        this.context = context;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponse = asyncResponse;
        responseFlag = 1;
    }

    HttpApiSelectTask(String url, Context context, ProgressBar progressBar, ScrollView scrollView, String tag, Pair[] nameValuePairs, AsyncResponseJSONObject asyncResponseJSONObject) {

        this.url = url;
        this.context = context;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONObject = asyncResponseJSONObject;
        responseFlag = 2;
    }

    public HttpApiSelectTask(String url, Context context, String tag, Pair[] nameValuePairs, AsyncResponse asyncResponse) {

        this.url = url;
        this.context = context;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponse = asyncResponse;
        progressFlag = 1;
        responseFlag = 1;
    }

    public HttpApiSelectTask(String url, Context context, String tag, Pair[] nameValuePairs, AsyncResponseJSONArray asyncResponseJSONArray) {

        this.url = url;
        this.context = context;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        progressFlag = 1;
        splashFlag = 1;
    }

    public HttpApiSelectTask(String url, Context context, ProgressBar progressBar, ScrollView scrollView, String tag, Pair[] nameValuePairs, AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag) {

        this.url = url;
        this.context = context;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.errorFlag = errorFlag;
    }

    public HttpApiSelectTask(String url, Context context, String tag, Pair[] nameValuePairs, AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag) {

        this.url = url;
        this.context = context;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        this.progressFlag = 1;
        this.errorFlag = errorFlag;
    }

    public HttpApiSelectTask(String url, Context context, String tag, Pair[] nameValuePairs, AsyncResponseJSONArray asyncResponseJSONArray, boolean errorFlag, boolean backgroundFlag) {

        this.url = url;
        this.context = context;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.asyncResponseJSONArray = asyncResponseJSONArray;
        this.progressFlag = 1;
        this.errorFlag = errorFlag;
        this.backgroundFlag = backgroundFlag;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        LogUtilsWrapper.debug("URL : " + url);
        return performHttpClientPostTask(url, nameValuePairs);
    }

    @Override
    protected void onPostExecute(final String[] networkActionResponseArray) {

        LogUtilsWrapper.debug("Network Action status is " + networkActionResponseArray[0]);
        LogUtilsWrapper.debug("Network Action response is " + networkActionResponseArray[1]);

        if (progressFlag == 0) {
            showProgress(false, context, progressBar, scrollView);
        }

        if (responseFlag == 1) {

            if (networkActionResponseArray[0].equals("1")) {

                NetworkUtils.displayFriendlyExceptionMessage(context, networkActionResponseArray[1]);
                asyncResponse.processFinish("exception");

            } else {
                asyncResponse.processFinish(networkActionResponseArray[1]);
            }

        } else if (responseFlag == 2) {

            if (networkActionResponseArray[0].equals("1")) {

                NetworkUtils.displayFriendlyExceptionMessage(context, networkActionResponseArray[1]);

            } else {
                try {
                    JSONObject jsonObject = new JSONObject(networkActionResponseArray[1]);
                    asyncResponseJSONObject.processFinish(jsonObject);

                } catch (JSONException e) {
                    ToastUtils.errorToast(context);
                    LogUtilsWrapper.debug("Error : " + e.getLocalizedMessage());
                }
            }

        } else {

            if (networkActionResponseArray[0].equals("1")) {

                if (backgroundFlag) {
                    LogUtilsWrapper.debug("Error...");
                } else {
                    ToastUtils.errorToast(context);
                }

                if (splashFlag == 1) {
                    ((AppCompatActivity) context).finish();
                }

            } else {

                try {
                    JSONArray jsonArray = new JSONArray(networkActionResponseArray[1]);

                    if ((splashFlag == 1) || (!errorFlag)) {
                        asyncResponseJSONArray.processFinish(jsonArray);
                    } else {

                        JSONObject tempJsonObject = jsonArray.getJSONObject(0);
                        switch (tempJsonObject.getString("status")) {
                            case "2":
                                LogUtilsWrapper.debug("No Entries...");
                                if (!backgroundFlag) {
                                    ToastUtils.longToast(context, "No Entries...");
                                }
                                break;
                            case "0":
                                asyncResponseJSONArray.processFinish(jsonArray);
                                break;
                            case "1":
                                LogUtilsWrapper.debug("Error : " + tempJsonObject.getString("error"));
                                ToastUtils.errorToast(context);
                                break;
                        }
                    }
                } catch (JSONException e) {
                    ToastUtils.errorToast(context);
                    LogUtilsWrapper.debug("Error : " + ExceptionUtils.getExceptionDetails(e));
                }
            }
        }
    }

    @Override
    protected void onCancelled() {
        if (progressFlag == 0) {
            showProgress(false, context, progressBar, scrollView);
        }
    }

    public interface AsyncResponseJSONArray {
        void processFinish(JSONArray jsonArray);
    }

    public interface AsyncResponse {
        void processFinish(String response);
    }

    public interface AsyncResponseJSONObject {
        void processFinish(JSONObject jsonObject);
    }

    private class LogUtilsWrapper extends LogUtilsWrapperBase {
        private LogUtilsWrapper() {
            super(tag);
        }
    }
}