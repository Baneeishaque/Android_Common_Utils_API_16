package ndk.utils.network_task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils.Network_Utils;

import static ndk.utils.Network_Utils.perform_http_client_network_task;
import static ndk.utils.ProgressBar_Utils.showProgress;

public class REST_Select_Task extends AsyncTask<Void, Void, String[]> {

    private String URL, TAG;
    private Context context;
    private View progressBar, form;

    private int progress_flag = 0;
    private int response_flag = 0;
    private int splash_flag = 0;

    private Pair[] name_value_pair;

    private Async_Response_JSON_array_with_error_status_delegate async_response_json_array_with_error_status_delegate = null;
    private Async_Response async_response_delegate = null;
    private Async_Response_JSON_object async_response_json_object_delegate = null;

    public REST_Select_Task(String URL, Context context, View progressBar, View form, String TAG, Pair[] name_value_pair, Async_Response_JSON_array_with_error_status_delegate async_response_json_array_with_error_status_delegate
    ) {

        this.URL = URL;
        this.context = context;
        this.progressBar = progressBar;
        this.form = form;
        this.TAG = TAG;
        this.name_value_pair = name_value_pair;
        this.async_response_json_array_with_error_status_delegate = async_response_json_array_with_error_status_delegate;
    }

    REST_Select_Task(String URL, Context context, View progressBar, View form, String TAG, Pair[] name_value_pair, Async_Response async_response_delegate) {

        this.URL = URL;
        this.context = context;
        this.progressBar = progressBar;
        this.form = form;
        this.TAG = TAG;
        this.name_value_pair = name_value_pair;
        this.async_response_delegate = async_response_delegate;
        response_flag = 1;
    }

    REST_Select_Task(String URL, Context context, View progressBar, View form, String TAG, Pair[] name_value_pair, Async_Response_JSON_object async_response_json_object_delegate) {

        this.URL = URL;
        this.context = context;
        this.progressBar = progressBar;
        this.form = form;
        this.TAG = TAG;
        this.name_value_pair = name_value_pair;
        this.async_response_json_object_delegate = async_response_json_object_delegate;
        response_flag = 2;
    }

    public REST_Select_Task(String URL, Context context, String TAG, Pair[] name_value_pair, Async_Response async_response_delegate) {

        this.URL = URL;
        this.context = context;
        this.TAG = TAG;
        this.name_value_pair = name_value_pair;
        this.async_response_delegate = async_response_delegate;
        progress_flag = 1;
        response_flag = 1;
    }

    public REST_Select_Task(String URL, Context context, String TAG, Pair[] name_value_pair, Async_Response_JSON_array_with_error_status_delegate async_response_json_array_with_error_status_delegate) {

        this.URL = URL;
        this.context = context;
        this.TAG = TAG;
        this.name_value_pair = name_value_pair;
        this.async_response_json_array_with_error_status_delegate = async_response_json_array_with_error_status_delegate;
        progress_flag = 1;
        splash_flag = 1;
    }

    @Override
    protected String[] doInBackground(Void... params) {
        Log.d(TAG, "URL is " + URL);
        return perform_http_client_network_task(URL, name_value_pair);
    }

    @Override
    protected void onPostExecute(final String[] network_action_response_array) {

        if (progress_flag == 0) {
            showProgress(false, context, progressBar, form);
        }

        if (response_flag == 1) {

            Log.d(TAG, "Network Action status is " + network_action_response_array[0]);
            Log.d(TAG, "Network Action response is " + network_action_response_array[1]);

            if (network_action_response_array[0].equals("1")) {

                Network_Utils.display_Friendly_Exception_Message(context, network_action_response_array[1]);
                Log.d(TAG, "Network Action response is " + network_action_response_array[1]);
                async_response_delegate.processFinish("exception");

            } else {
                async_response_delegate.processFinish(network_action_response_array[1]);
            }

        } else if (response_flag == 2) {

            Log.d(TAG, "Network Action status is " + network_action_response_array[0]);
            Log.d(TAG, "Network Action response is " + network_action_response_array[1]);

            if (network_action_response_array[0].equals("1")) {

                Network_Utils.display_Friendly_Exception_Message(context, network_action_response_array[1]);
                Log.d(TAG, "Network Action response is " + network_action_response_array[1]);

            } else {
                try {
                    JSONObject json_object = new JSONObject(network_action_response_array[1]);
                    async_response_json_object_delegate.processFinish(json_object);

                } catch (JSONException e) {
                    Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Error : " + e.getLocalizedMessage());
                }
            }

        } else {

            Log.d(TAG, "Network Action Response Array 0 : " + network_action_response_array[0]);
            Log.d(TAG, "Network Action Response Array 1 : " + network_action_response_array[1]);

            if (network_action_response_array[0].equals("1")) {
                Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Network Action Response Array 1 : " + network_action_response_array[1]);

                if (splash_flag == 1) {
                    ((AppCompatActivity) context).finish();
                }

            } else {

                try {
                    JSONArray json_array_with_error_status = new JSONArray(network_action_response_array[1]);
                    if (json_array_with_error_status.getJSONObject(0).getString("status").equals("1")) {
                        Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                    } else if (json_array_with_error_status.getJSONObject(0).getString("status").equals("0")) {
                        async_response_json_array_with_error_status_delegate.processFinish(json_array_with_error_status);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Error...", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Error : " + e.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    protected void onCancelled() {
        if (progress_flag == 0) {
            showProgress(false, context, progressBar, form);
        }
    }

    // you may separate this or combined to caller class.
    public interface Async_Response_JSON_array_with_error_status_delegate {
        void processFinish(JSONArray json_array_with_error_status);
    }

    public interface Async_Response {
        void processFinish(String response);
    }

    public interface Async_Response_JSON_object {
        void processFinish(JSONObject json_object);
    }
}