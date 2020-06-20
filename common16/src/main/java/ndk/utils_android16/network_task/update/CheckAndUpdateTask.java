package ndk.utils_android16.network_task.update;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.LogUtilsWrapperBase;
import ndk.utils_android16.BuildConfig;
import ndk.utils_android16.ErrorUtilsWrapperBase;
import ndk.utils_android16.NetworkUtils;
import ndk.utils_android16.ServerUtils;
import ndk.utils_android16.ToastUtils;
import ndk.utils_android16.UpdateUtils;

import static ndk.utils_android16.NetworkUtils.displayFriendlyExceptionMessage;
import static ndk.utils_android16.update.UpdateApplication.updateApplication;

public class CheckAndUpdateTask extends AsyncTask<Void, Void, String[]> {

    private AppCompatActivity currentActivity;
    private Class nextActivity;
    private String URL, applicationName, updateUrl;
    private boolean securityFlag, tabIndexFlag;
    private int tabIndex;
    private Pair[] nextActivityExtras;

    public CheckAndUpdateTask(String applicationName, AppCompatActivity currentActivity, String URL, String updateUrl, Class nextActivity, boolean securityFlag, boolean tabIndexFlag, int tabIndex, Pair[] nextActivityExtras) {

        this.currentActivity = currentActivity;
        this.URL = URL;
        this.applicationName = applicationName;
        this.updateUrl = updateUrl;
        this.nextActivity = nextActivity;
        this.securityFlag = securityFlag;
        this.tabIndexFlag = tabIndexFlag;
        this.tabIndex = tabIndex;
        this.nextActivityExtras = nextActivityExtras;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        return UpdateUtils.getServerVersion(URL, applicationName);
    }

    @Override
    protected void onPostExecute(final String[] networkActionResponseArray) {

        NetworkUtils.displayNetworkActionResponse(applicationName, networkActionResponseArray);

        if (networkActionResponseArray[0].equals("1")) {

            displayFriendlyExceptionMessage(currentActivity, networkActionResponseArray[1]);
            currentActivity.finish();

        } else {

            try {

                JSONArray jsonArray = new JSONArray(networkActionResponseArray[1]);
                checkAndUpdate(jsonArray);

            } catch (JSONException e) {

                class ErrorUtilsWrapper extends ErrorUtilsWrapperBase {
                    public ErrorUtilsWrapper(String applicationName) {
                        super(applicationName);
                    }
                }

                ErrorUtilsWrapper.displayException(currentActivity, e);
            }
        }
    }

    public void checkAndUpdate(JSONArray jsonArray) {

        try {
            JSONObject tempJsonObject = jsonArray.getJSONObject(0);

            if (ServerUtils.checkSystemStatus(currentActivity, tempJsonObject.getString("system_status"), applicationName)) {

                if (Integer.parseInt(tempJsonObject.getString("version_code")) != UpdateUtils.getVersionCode(currentActivity) || Float.parseFloat(tempJsonObject.getString("version_name")) != UpdateUtils.getVersionName(currentActivity)) {

                    updateApplication(applicationName, currentActivity, Float.parseFloat(tempJsonObject.getString("version_name")), updateUrl, securityFlag);

                } else {

                    class LogUtilsWrapper extends LogUtilsWrapperBase {

                        private LogUtilsWrapper() {
                            super(applicationName);
                        }
                    }

                    LogUtilsWrapper.debug("Latest Version...");

                    if (!securityFlag) {

                        ToastUtils.shortToast(currentActivity, "Latest Version...");
                    }
                    // After completing http call will close this activity and launch main activity
                    if (tabIndexFlag) {

                        //TODO : Tab Index with Other extras
                        ActivityUtils.startActivityWithFinishAndTabIndex(currentActivity, nextActivity, tabIndex);

                    } else {

                        if (nextActivityExtras.length == 0) {

                            ActivityUtils.startActivityWithFinish(currentActivity, nextActivity);

                        } else {

                            ActivityUtils.startActivityWithStringExtrasAndFinish(currentActivity, nextActivity, nextActivityExtras);
                        }
                    }
                }
            }
        } catch (JSONException e) {

            class ErrorUtilsWrapper extends ErrorUtilsWrapperBase {

                public ErrorUtilsWrapper(String applicationName) {
                    super(applicationName);
                }
            }

            ErrorUtilsWrapper.displayException(currentActivity, e);
        }
    }
}
