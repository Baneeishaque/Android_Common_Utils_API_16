package ndk.utils_android16;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.snackbar.Snackbar;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.LogUtilsWrapperBase;

import static android.graphics.Color.RED;

public class NetworkUtils {

    private static further_Actions further_actions;

    public static void displayOfflineLongNoFabBottomSnackBar(View view, View.OnClickListener networkFunction) {
        Snackbar snackbar = Snackbar.make(view, "Internet unavailable!", Snackbar.LENGTH_INDEFINITE).setAction("Retry",
                networkFunction);
        snackbar.getView().setBackgroundColor(RED);
        snackbar.show();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        if (!(netInfo != null && netInfo.isConnectedOrConnecting())) {
            Toast_Utils.longToast(context, "Internet is unavailable");
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String[] performHttpClientPostTask(String url, Pair[] namePairValues) {
        try {

            DefaultHttpClient defaultHttpClient;
            HttpPost httpPost;
            ArrayList<NameValuePair> nameValuePairs;
            String networkActionResponse;

            defaultHttpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            if (namePairValues.length != 0) {
                nameValuePairs = new ArrayList<>(namePairValues.length);
                for (Pair namePairValue : namePairValues) {
                    nameValuePairs.add(
                            new BasicNameValuePair(namePairValue.first != null ? namePairValue.first.toString() : null,
                                    namePairValue.second != null ? namePairValue.second.toString() : null));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            }
            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();
            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);
            return new String[] { "0", networkActionResponse };

        } catch (UnsupportedEncodingException e) {
            return new String[] { "1", "UnsupportedEncodingException : " + e.getLocalizedMessage() };
        } catch (ClientProtocolException e) {
            return new String[] { "1", "ClientProtocolException : " + e.getLocalizedMessage() };
        } catch (IOException e) {
            return new String[] { "1", "IOException : " + e.getLocalizedMessage() };
        }
    }

    private void handle_json_insertion_response_and_switch_with_finish_or_clear_fields(
            String[] network_action_response_array, Class to_switch_activity, EditText[] texts_to_clear,
            View view_to_focus_on_error, int action_flag, Pair[] next_class_extras, further_Actions further_actions) {

        NetworkUtils.further_actions = further_actions;

        Log_Utils log_utils = new Log_Utils(is_debug, APPLICATION_NAME);
        log_utils.debug("Network Action Response Index 0 : " + network_action_response_array[0]);
        log_utils.debug("Network Action Response Index 1 : " + network_action_response_array[1]);

        if (network_action_response_array[0].equals("1")) {
            Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
            log_utils.debug("Error, Network Action Response Index 1 : " + network_action_response_array[1]);
        } else {
            try {

                JSONObject json = new JSONObject(network_action_response_array[1]);

                switch (json.getString("status")) {

                    case "0":

                        Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();

                        switch (action_flag) {

                            case 1: // finish and switch
                                ActivityUtils.startActivityWithFinish(current_activity, to_switch_activity);
                                break;

                            case 2: // clear fields
                                Text_Clear_Utils.reset_fields(texts_to_clear);
                                break;

                            case 3: // self finish
                                ((AppCompatActivity) context).finish();
                                break;

                            case 4: // finish and switch with extras
                                ActivityUtils.startActivityWithStringExtrasAndFinish(current_activity,
                                        to_switch_activity, next_class_extras);
                                break;

                            case 5: // No Action
                                log_utils.debug("Further Action...");
                                further_actions.onSuccess();
                                break;

                            case 6: // clear fields & further actions
                                log_utils.debug("Further Action...");
                                Text_Clear_Utils.reset_fields(texts_to_clear);
                                further_actions.onSuccess();
                                break;
                        }
                        break;

                    case "1":
                        Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                        log_utils.debug("Error : " + json.getString("error"));
                        view_to_focus_on_error.requestFocus();
                        break;

                    default:
                        Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                        log_utils.debug("Error : Application_Utils json");
                }
            } catch (JSONException e) {
                Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                log_utils.debug("Error : " + e.getLocalizedMessage());
            }
        }
    }

    // TODO : Improve to handle exception objects
    public static void displayFriendlyExceptionMessage(Context context, String networkExceptionMessage) {
        if (networkExceptionMessage.contains("IOException")) {
            ToastUtils.longToast(context, "Check your network connection...");
        }
    }

    void handle_json_insertion_response_and_switch_with_finish_and_toggle_view(String[] network_action_response_array,
            Class to_switch_activity, View view_to_focus_on_error, View view_to_toggle) {

        handle_json_insertion_response_and_switch_with_finish_or_clear_fields(network_action_response_array,
                to_switch_activity, new EditText[] {}, view_to_focus_on_error, 1, new Pair[] {}, further_actions);
        view_to_toggle.setEnabled(true);
    }

    public static void check_network_then_start_activity_with_string_extras(Context context, Class activity,
            Pair[] extras, boolean for_result_flag, int request_code) {
        if (isOnline(context)) {
            ActivityUtils.startActivityWithStringExtras(context, activity, extras);
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void check_network_then_start_activity(Context context, Class activity) {
        if (isOnline(context)) {
            ActivityUtils.startActivity(context, activity);
        } else {
            ToastUtils.offlineToast(context);
        }
    }

    public interface further_Actions {
        void onSuccess();
    }

    public static void displayNetworkActionResponse(String tag, String[] networkActionResponseArray) {

        class LogUtilsWrapper extends LogUtilsWrapperBase {
            private LogUtilsWrapper() {
                super(tag);
            }
        }

        LogUtilsWrapper.debug("Network Action Response Array : " + Arrays.toString(networkActionResponseArray));
    }
}
