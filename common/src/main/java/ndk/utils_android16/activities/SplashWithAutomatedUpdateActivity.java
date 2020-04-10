package ndk.utils_android16.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android14.LogUtilsWrapperBase;
import ndk.utils_android16.ErrorUtilsWrapperBase;
import ndk.utils_android16.R;
import ndk.utils_android16.ServerUtils;
import ndk.utils_android16.ToastUtils;
import ndk.utils_android16.UpdateUtils;
import ndk.utils_android16.network_task.HttpApiSelectTask;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;
import ndk.utils_android16.update.UpdateApplication;

//TODO : Full screen splash
//TODO : Implement hiding of fields - in case of layout
//TODO : Develop tests

public abstract class SplashWithAutomatedUpdateActivity extends ContextActivity {

    protected abstract String configure_GET_CONFIGURATION_URL();

    protected abstract String configure_UPDATE_URL();

    protected abstract Class configure_NEXT_ACTIVITY_CLASS();

    protected abstract Pair[] configure_NEXT_ACTIVITY_CLASS_EXTRAS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initializeScreen();

        HttpApiSelectTaskWrapper.performSplashScreenThenReturnJsonArray(this, configure_GET_CONFIGURATION_URL(), configure_APPLICATION_NAME(), new Pair[]{}, new HttpApiSelectTask.AsyncResponseJSONArray() {

            @Override
            public void processFinish(JSONArray jsonArray) {

                try {

                    JSONObject tempJsonObject = jsonArray.getJSONObject(0);
                    if (ServerUtils.checkSystemStatus(getApplicationContext(), tempJsonObject.getString("system_status"), configure_APPLICATION_NAME())) {

                        if (Integer.parseInt(tempJsonObject.getString("version_code")) != UpdateUtils.getVersionCode(getApplicationContext())) {

                            UpdateApplication.updateApplication(configure_APPLICATION_NAME(), (AppCompatActivity) activityContext, Float.parseFloat(tempJsonObject.getString("version_name")), configure_UPDATE_URL(), configure_SECURITY_FLAG());

                        } else if (Float.parseFloat(tempJsonObject.getString("version_name")) != UpdateUtils.getVersionName(getApplicationContext())) {

                            UpdateApplication.updateApplication(configure_APPLICATION_NAME(), (AppCompatActivity) activityContext, Float.parseFloat(tempJsonObject.getString("version_name")), configure_UPDATE_URL(), configure_SECURITY_FLAG());

                        } else {

                            class LogUtilsWrapper extends LogUtilsWrapperBase {
                                private LogUtilsWrapper() {
                                    super(configure_APPLICATION_NAME());
                                }
                            }

                            LogUtilsWrapper.debug("Latest Version...");
                            if (!configure_SECURITY_FLAG()) {
                                ToastUtils.longToast(getApplicationContext(), "Latest Version...");
                            }

                            ActivityUtils.startActivityWithStringExtrasAndFinish(activityContext, configure_NEXT_ACTIVITY_CLASS(), configure_NEXT_ACTIVITY_CLASS_EXTRAS());
                        }
                    }

                } catch (JSONException json_exception) {

                    class ErrorUtilsWrapper extends ErrorUtilsWrapperBase {
                        private ErrorUtilsWrapper() {
                            super(configure_APPLICATION_NAME());
                        }
                    }

                    ErrorUtilsWrapper.displayException(getApplicationContext(), json_exception);
                }
            }
        });
    }

    public void initializeScreen() {
        setContentView(R.layout.splash);
    }

    protected abstract boolean configure_SECURITY_FLAG();

    protected abstract String configure_APPLICATION_NAME();
}
