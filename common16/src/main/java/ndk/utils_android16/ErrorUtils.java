package ndk.utils_android16;

import android.content.Context;

import org.json.JSONObject;

import ndk.utils_android14.LogUtils;

public class ErrorUtils {

    public static void displayException(Context context, Exception exception, String applicationName) {

        ToastUtils.errorToast(context);
        LogUtils.debug(applicationName, ExceptionUtils.getExceptionDetails(exception));
    }

    public static void displayJSONFieldMiss(Context context, JSONObject jsonObject, String applicationName) {

        ToastUtils.errorToast(context);
        LogUtils.debug(applicationName, "Error, Check JSON : " + jsonObject);
    }
}
