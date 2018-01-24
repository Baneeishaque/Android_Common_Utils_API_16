package ndk.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

    public static void display_Long_no_FAB_no_network_bottom_SnackBar(View view, View.OnClickListener network_function) {
        Snackbar snackbar = Snackbar
                .make(view, "Internet unavailable!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", network_function);
        snackbar.getView().setBackgroundColor(RED);
        snackbar.show();
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
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
                for (Pair<String, String> name_pair_value : name_pair_values) {
                    name_pair_value_array.add(new BasicNameValuePair(name_pair_value.first, name_pair_value.second));
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

    public static void handle_json_insertion_response_and_switch_with_finish(String[] network_action_response_array, AppCompatActivity current_activity, Class to_switch_activity, View view_to_focus_on_error, String TAG) {
        Log.d(TAG, network_action_response_array[0]);
        Log.d(TAG, network_action_response_array[1]);

        if (network_action_response_array[0].equals("1")) {
            Toast.makeText(current_activity, "Error : " + network_action_response_array[1], Toast.LENGTH_LONG).show();
            Log.d(TAG, network_action_response_array[1]);
        } else {

            try {
                JSONObject json = new JSONObject(network_action_response_array[1]);
                String response_code = json.getString("status");
                switch (response_code) {
                    case "0":
                        Toast.makeText(current_activity, "OK", Toast.LENGTH_LONG).show();
                        Activity_Utils.start_activity_with_finish(current_activity, to_switch_activity);
                        break;
                    case "1":
                        Toast.makeText(current_activity, "Error : " + json.getString("error"), Toast.LENGTH_LONG).show();
                        view_to_focus_on_error.requestFocus();
                        break;
                    default:
                        Toast.makeText(current_activity, "Error : Check json", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Toast.makeText(current_activity, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, e.getLocalizedMessage());
            }


        }
    }

    //TODO : Improve to handle exception objects
    public static void display_Friendly_Exception_Message(Context context, String network_exception_message) {
        if (network_exception_message.contains("IOException")) {
            Toast_Utils.longToast(context, "Check your network connection");
        }

    }

    public static void handle_json_insertion_response_and_switch_with_finish_and_toggle_view(String[] network_action_response_array, AppCompatActivity current_activity, Class to_switch_activity, Context context, View view_to_focus_on_error,View view_to_toggle,String TAG) {
        Log.d(TAG, network_action_response_array[0]);
        Log.d(TAG, network_action_response_array[1]);

        if (network_action_response_array[0].equals("1")) {
            Toast.makeText(context, "Error : " + network_action_response_array[1], Toast.LENGTH_LONG).show();
            Log.d(TAG, network_action_response_array[1]);
        } else {

            try {
                JSONObject json = new JSONObject(network_action_response_array[1]);
                String response_code = json.getString("status");
                switch (response_code) {
                    case "0":
                        Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
                        Activity_Utils.start_activity_with_finish(current_activity,to_switch_activity);
                        break;
                    case "1":
                        Toast.makeText(context, "Error : " + json.getString("error"), Toast.LENGTH_LONG).show();
                        view_to_focus_on_error.requestFocus();
                        break;
                    default:
                        Toast.makeText(context, "Error : Check json", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Toast.makeText(context, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, e.getLocalizedMessage());
            }
        }
        view_to_toggle.setEnabled(true);
    }
}
