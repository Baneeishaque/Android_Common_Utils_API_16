package ndk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

import static android.graphics.Color.RED;

public class Network_Utils {

    private static further_Actions further_actions;

    Context context;
    private String APPLICATION_NAME;
    private boolean is_debug;
    private Activity_Utils activity_utils;

    public Network_Utils(Context context, String APPLICATION_NAME, boolean is_debug) {
        this.context = context;
        this.APPLICATION_NAME = APPLICATION_NAME;
        this.is_debug = is_debug;
        this.activity_utils = new Activity_Utils(context, APPLICATION_NAME, is_debug);
    }

    public void display_Long_no_FAB_no_network_bottom_SnackBar(View view, View.OnClickListener network_function) {
        Snackbar snackbar = Snackbar
                .make(view, "Internet unavailable!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", network_function);
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

    public static String[] perform_http_client_network_task(String URL, Pair[] name_pair_values) {
        try {

            DefaultHttpClient http_client;
            HttpPost http_post;
            ArrayList<NameValuePair> name_pair_value_array;
            String network_action_response;

            http_client = new DefaultHttpClient();
            http_post = new HttpPost(URL);
            if (name_pair_values.length != 0) {
                name_pair_value_array = new ArrayList<>(name_pair_values.length);
                for (Pair name_pair_value : name_pair_values) {
                    name_pair_value_array.add(new BasicNameValuePair(name_pair_value.first != null ? name_pair_value.first.toString() : null, name_pair_value.second != null ? name_pair_value.second.toString() : null));
                }
                http_post.setEntity(new UrlEncodedFormEntity(name_pair_value_array));
            }
            ResponseHandler<String> response_handler = new BasicResponseHandler();
            network_action_response = http_client.execute(http_post, response_handler);
            return new String[]{"0", network_action_response};

        } catch (UnsupportedEncodingException e) {
            return new String[]{"1", "UnsupportedEncodingException : " + e.getLocalizedMessage()};
        } catch (ClientProtocolException e) {
            return new String[]{"1", "ClientProtocolException : " + e.getLocalizedMessage()};
        } catch (IOException e) {
            return new String[]{"1", "IOException : " + e.getLocalizedMessage()};
        }
    }

    private void handle_json_insertion_response_and_switch_with_finish_or_clear_fields(String[] network_action_response_array, Class to_switch_activity, EditText[] texts_to_clear, View view_to_focus_on_error, int action_flag, Pair[] next_class_extras, further_Actions further_actions) {

        Network_Utils.further_actions = further_actions;

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

                            case 1: //finish and switch
                                activity_utils.start_activity_with_finish(to_switch_activity);
                                break;

                            case 2: //clear fields
                                Text_Clear_Utils.reset_fields(texts_to_clear);
                                break;

                            case 3: //self finish
                                ((AppCompatActivity) context).finish();
                                break;

                            case 4: //finish and switch with extras
                                activity_utils.start_activity_with_string_extras_and_finish(to_switch_activity, next_class_extras);
                                break;

                            case 5: //No Action
                                log_utils.debug("Further Action...");
                                further_actions.onSuccess();
                                break;

                            case 6: //clear fields & further actions
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

    //TODO : Improve to handle exception objects
    public void display_Friendly_Exception_Message(String network_exception_message) {
        if (network_exception_message.contains("IOException")) {
            Toast_Utils.longToast(context, "Application_Utils your network connection");
        }

    }

    void handle_json_insertion_response_and_switch_with_finish_and_toggle_view(String[] network_action_response_array, Class to_switch_activity, View view_to_focus_on_error, View view_to_toggle) {

        handle_json_insertion_response_and_switch_with_finish_or_clear_fields(network_action_response_array, to_switch_activity, new EditText[]{}, view_to_focus_on_error, 1, new Pair[]{}, further_actions);
        view_to_toggle.setEnabled(true);
    }

    void check_network_then_start_activity_with_string_extras(Class activity, Pair[] extras, boolean for_result_flag, int request_code) {
        if (isOnline()) {
            activity_utils.start_activity_with_string_extras(activity, extras, for_result_flag, request_code);
        }
    }

    void check_network_then_start_activity(Context context, Class activity) {
        if (isOnline()) {
            activity_utils.start_activity(activity);
        }
    }

    public interface further_Actions {
        void onSuccess();
    }
}
