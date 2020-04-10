package ndk.utils_android16;

import android.content.Context;

import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import ndk.utils_android14.LogUtilsWrapperBase;

public class JsonUtils extends ndk.utils_j2se.JSON_Utils {

    public static void jsonObjectFieldsToSharedPreferences(JSONObject jsonObject, boolean fieldsToIgnoreFlag, String[] fieldsToIgnore, Context applicationContext, String applicationName) {

        for (int i = 0; i < jsonObject.names().length(); i++) {

            try {

                class LogUtilsWrapper extends LogUtilsWrapperBase {
                    private LogUtilsWrapper() {
                        super(applicationName);
                    }
                }

                LogUtilsWrapper.debug("key = " + jsonObject.names().getString(i) + " value = " + jsonObject.get(jsonObject.names().getString(i)));

                String key = jsonObject.names().getString(i);

                if (fieldsToIgnoreFlag && Arrays.asList(fieldsToIgnore).contains(key)) {
                    continue;
                }

                SharedPreferenceUtils.commitSharedPreferences(applicationContext, applicationName, new Pair[]{new Pair<>(key, jsonObject.get(key))});

            } catch (JSONException json_exception) {

                ErrorUtilsWrapperBase.displayException(applicationContext, json_exception);
            }
        }
    }

    public static void jsonObjectFieldsToSharedPreferencesWithoutFieldsToIgnore(JSONObject jsonObject, Context applicationContext, String applicationName) {
        jsonObjectFieldsToSharedPreferences(jsonObject, false, new String[]{}, applicationContext, applicationName);
    }

    public static void jsonObjectFieldsToSharedPreferencesWithFieldsToIgnore(JSONObject jsonObject, String[] fieldsToIgnore, Context applicationContext, String applicationName) {
        jsonObjectFieldsToSharedPreferences(jsonObject, true, fieldsToIgnore, applicationContext, applicationName);
    }

    public static void jsonArrayToSharedPreferencesWithFieldsToIgnore(JSONArray jsonArray, String[] fieldsToIgnore, Context applicationContext, String applicationName) {

        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                jsonObjectFieldsToSharedPreferencesWithFieldsToIgnore(jsonArray.getJSONObject(i), fieldsToIgnore, applicationContext, applicationName);

            } catch (JSONException jsonException) {

                ErrorUtilsWrapperBase.displayException(applicationContext, jsonException);
            }

        }
    }

    public static void JSON_array_to_array_list(JSONArray jsonArray, ArrayList arrayList, int start_position, Object_Utils.IGet_object iGet_object, Context context) {

        for (int i = start_position; i < jsonArray.length(); i++) {

            try {

                arrayList.add(iGet_object.get_object(jsonArray.getJSONObject(i)));

            } catch (JSONException e) {

                ErrorUtilsWrapperBase.displayException(context, e);

            }
        }
    }
}
