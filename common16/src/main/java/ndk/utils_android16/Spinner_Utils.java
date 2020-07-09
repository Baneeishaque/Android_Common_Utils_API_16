package ndk.utils_android16;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ndk.utils_android1.ToastUtils;
import ndk.utils_android16.network_task.HttpApiSelectTask;

import static ndk.utils_android1.NetworkUtils.isOnline;
import static ndk.utils_android1.ProgressBarUtils.showProgress;

/**
 * Created on 20-09-2018 14:16 under VLottery.
 */
public class Spinner_Utils {

    public static void attach_items_to_simple_spinner(Context context, Spinner spinner, ArrayList<String> items) {
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);
    }

    public static void attach_items_to_custom_spinner_item_spinner(Context context, Spinner spinner, ArrayList<String> items, int custom_spinner_item) {
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(context, custom_spinner_item, items);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);
    }

    public static void populate_spinner_from_json_array(int start_index, JSONArray json_array, Context context, String application_name, Spinner spinner, ArrayList<String> spinner_items, String key) {
        for (int i = start_index; i < json_array.length(); i++) {
            try {
                spinner_items.add(json_array.getJSONObject(i).getString(key));
            } catch (JSONException e) {
                Toast.makeText(context, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(application_name, e.getLocalizedMessage());
            }
        }
        attach_items_to_simple_spinner(context, spinner, spinner_items);
    }

    public static void get_json_from_network_and_populate(final Context context, ProgressBar progress_Bar, ScrollView form, String URL, final String application_name, final int start_index, final Spinner spinner, final ArrayList<String> spinner_items, final String key) {

        if (isOnline(context)) {

            showProgress(true, context, progress_Bar, form);

            HttpApiSelectTask REST_select_task = new HttpApiSelectTask(URL, context, progress_Bar, form, application_name, jsonArray -> populate_spinner_from_json_array(start_index, jsonArray, context, application_name, spinner, spinner_items, key));

            REST_select_task.execute();

        } else {

            ToastUtils.longToast(context, "Internet is unavailable...");
        }
    }
}
