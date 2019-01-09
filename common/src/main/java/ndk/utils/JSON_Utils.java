package ndk.utils;

import android.content.Context;
import android.support.v4.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created on 25-08-2018 01:50 under VLottery.
 */
public class JSON_Utils extends ndk.utils_j2se.JSON_Utils {

    public static void JSON_Object_Fields_to_Shared_Preferences(JSONObject json_Object, String[] fields_to_ignore, Context application_context, String APPLICATION_NAME, boolean is_debug) {

        for (int i = 0; i < json_Object.names().length(); i++) {

            try {

                Log_Utils.debug(APPLICATION_NAME, "key = " + json_Object.names().getString(i) + " value = " + json_Object.get(json_Object.names().getString(i)), is_debug);

                String key = json_Object.names().getString(i);
                if (Arrays.asList(fields_to_ignore).contains(key)) {
                    continue;
                }

                SharedPreference_Utils.commit_Shared_Preferences(application_context, APPLICATION_NAME, new Pair[]{new Pair<>(key, json_Object.get(key))});

            } catch (JSONException json_exception) {

                Error_Utils.display_Exception(application_context, json_exception, APPLICATION_NAME, is_debug);

            }

        }
    }

    public static void JSON_Array_to_Shared_Preferences(JSONArray json_Array, String[] fields_to_ignore, Context application_context, String APPLICATION_NAME, boolean is_debug) {

        for (int i = 0; i < json_Array.length(); i++) {

            try {

                JSON_Object_Fields_to_Shared_Preferences(json_Array.getJSONObject(i), fields_to_ignore, application_context, APPLICATION_NAME, is_debug);

            } catch (JSONException json_exception) {

                Error_Utils.display_Exception(application_context, json_exception, APPLICATION_NAME, is_debug);
            }

        }
    }

    public static void JSON_array_to_array_list(JSONArray jsonArray, ArrayList arrayList, int start_position, Object_Utils.IGet_object iGet_object, Context context, String APPLICATION_NAME, boolean is_debug) {

        for (int i = start_position; i < jsonArray.length(); i++) {

            try {

                arrayList.add(iGet_object.get_object(jsonArray.getJSONObject(i)));

            } catch (JSONException e) {

                Error_Utils.display_Exception(context, e, APPLICATION_NAME, is_debug);

            }
        }
    }
}
