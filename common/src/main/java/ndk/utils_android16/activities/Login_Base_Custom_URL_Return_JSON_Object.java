package ndk.utils_android16.activities;

import android.support.v4.util.Pair;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android16.Error_Utils;
import ndk.utils_android16.SharedPreference_Utils;
import ndk.utils_android16.network_task.REST_Select_Task;
import ndk.utils_android16.network_task.REST_Select_Task_Wrapper;

/**
 * Created on 24-08-2018 20:21 under VLottery.
 */
public abstract class Login_Base_Custom_URL_Return_JSON_Object extends Login_Base_Custom_URL {

//    Context activity_context = this;

    @Override
    protected void configure_FURTHER_PROCESSING() {

        //Example Response : {"user_count":"1","id":"125"}
        REST_Select_Task_Wrapper.execute(configure_SELECT_USER_URL(), activity_context, mProgressView, mLoginFormView, configure_APPLICATION_NAME(), configure_http_call_parameters(), configure_JSON_object_handler());
    }

    protected abstract String configure_SELECT_USER_URL();

    public REST_Select_Task.Async_Response_JSON_object configure_JSON_object_handler() {

        return new REST_Select_Task.Async_Response_JSON_object() {

            @Override
            public void processFinish(JSONObject json_object) {

                try {

                    String user_count = json_object.getString("user_count");

                    switch (user_count) {

                        case "1":
                            SharedPreference_Utils.commit_Shared_Preferences(getApplicationContext(), configure_APPLICATION_NAME(), new Pair[]{new Pair<>("user_id", json_object.getString("id"))});
                            ActivityUtils.start_activity_with_finish(activity_context, configure_NEXT_ACTIVITY_CLASS(), configure_APPLICATION_NAME());
                            break;

                        case "0":
                            Toast.makeText(activity_context, "Login Failure!", Toast.LENGTH_LONG).show();
                            username.requestFocus();
                            break;

                        default:
                            Error_Utils.display_JSON_Field_Miss(getApplicationContext(), json_object, configure_APPLICATION_NAME(), configure_is_debug());

                    }

                } catch (JSONException JSON_exception) {

                    Error_Utils.display_Exception(getApplicationContext(), JSON_exception, configure_APPLICATION_NAME(), configure_is_debug());

                }
            }
        };

    }

    protected abstract String configure_APPLICATION_NAME();

    protected abstract boolean configure_is_debug();

    protected abstract Class configure_NEXT_ACTIVITY_CLASS();
}
