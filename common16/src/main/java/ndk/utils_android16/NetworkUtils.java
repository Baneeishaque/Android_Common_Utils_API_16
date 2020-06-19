package ndk.utils_android16;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import ndk.utils_android14.LogUtils;
import ndk.utils_android14.LogUtilsWrapperBase;

import static android.graphics.Color.RED;

public class NetworkUtils {

    private static FurtherActions furtherActions;

    public static void displayOfflineLongNoFabBottomSnackBar(View view, View.OnClickListener networkFunction) {
        Snackbar snackbar = Snackbar.make(view, "Internet unavailable!", Snackbar.LENGTH_INDEFINITE).setAction("Retry",
                networkFunction);
        snackbar.getView().setBackgroundColor(RED);
        snackbar.show();
    }

    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        if (!(netInfo != null && netInfo.isConnectedOrConnecting())) {

            ToastUtils.longToast(context, "Internet is unavailable");
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

                    nameValuePairs.add(new BasicNameValuePair(namePairValue.first != null ? namePairValue.first.toString() : null, namePairValue.second != null ? namePairValue.second.toString() : null));
                }

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            }

            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();
            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);
            return new String[]{"0", networkActionResponse};

        } catch (UnsupportedEncodingException e) {

            return new String[]{"1", "UnsupportedEncodingException : " + e.getLocalizedMessage()};

        } catch (ClientProtocolException e) {

            return new String[]{"1", "ClientProtocolException : " + e.getLocalizedMessage()};

        } catch (IOException e) {

            return new String[]{"1", "IOException : " + e.getLocalizedMessage()};
        }
    }

    public static void handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(String[] networkActionResponseArray, AppCompatActivity currentActivity, Class toSwitchActivity, EditText[] editTextsToClear, View viewToFocusOnError, String tag, int actionFlag, Pair[] nextClassExtras, FurtherActions furtherActions) {

        NetworkUtils.furtherActions = furtherActions;

        LogUtils.debug(tag, "Network Action Response Index 0 : " + networkActionResponseArray[0], BuildConfig.DEBUG);
        LogUtils.debug(tag, "Network Action Response Index 1 : " + networkActionResponseArray[1], BuildConfig.DEBUG);

        if (networkActionResponseArray[0].equals("1")) {

            Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
            LogUtils.debug(tag, "Error, Network Action Response Index 1 : " + networkActionResponseArray[1], BuildConfig.DEBUG);

        } else {

            try {
                JSONObject json = new JSONObject(networkActionResponseArray[1]);

                switch (json.getString("status")) {

                    case "0":

                        Toast.makeText(currentActivity, "OK", Toast.LENGTH_LONG).show();

                        switch (actionFlag) {

                            case 1: // finish and switch
                                ActivityUtils.startActivityWithFinish(currentActivity, toSwitchActivity);
                                break;

                            case 2: // clear fields
                                TextClearUtils.resetFields(editTextsToClear);
                                break;

                            case 3: // self finish
                                currentActivity.finish();
                                break;

                            case 4: // finish and switch with extras
                                ActivityUtils.startActivityWithStringExtrasAndFinish(currentActivity,
                                        toSwitchActivity, nextClassExtras);
                                break;

                            case 5: // No Action
                                LogUtils.debug(tag, "Further Action...", BuildConfig.DEBUG);
                                furtherActions.onSuccess();
                                break;

                            case 6: // clear fields & further actions
                                LogUtils.debug(tag, "Further Action...", BuildConfig.DEBUG);
                                TextClearUtils.resetFields(editTextsToClear);
                                furtherActions.onSuccess();
                                break;
                        }
                        break;

                    case "1":
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils.debug(tag, "Error : " + json.getString("error"), BuildConfig.DEBUG);
                        viewToFocusOnError.requestFocus();
                        break;

                    default:
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils.debug(tag, "Error : Application_Utils json", BuildConfig.DEBUG);
                }
            } catch (JSONException e) {
                Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                LogUtils.debug(tag, "Error : " + e.getLocalizedMessage(), BuildConfig.DEBUG);
            }
        }
    }

    // TODO : Improve to handle exception objects
    public static void displayFriendlyExceptionMessage(Context context, String networkExceptionMessage) {
        if (networkExceptionMessage.contains("IOException")) {
            ToastUtils.longToast(context, "Check your network connection...");
        }
    }

    void handleJsonInsertionResponseAndSwitchWithFinishAndToggleView(String[] networkActionResponseArray, Class toSwitchActivity, View viewToFocusOnError, View viewToToggle, String tag, AppCompatActivity currentActivity) {

        handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, toSwitchActivity, new EditText[]{}, viewToFocusOnError, tag, 1, new Pair[]{}, furtherActions);
        viewToToggle.setEnabled(true);
    }

    public static void checkNetworkThenStartActivityWithStringExtras(Context context, Class activity, Pair[] extras, boolean forResultFlag, int requestCode) {
        if (isOnline(context)) {
            ActivityUtils.startActivityWithStringExtras(context, activity, extras);
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public static void checkNetworkThenStartActivity(Context context, Class activity) {
        if (isOnline(context)) {
            ActivityUtils.startActivity(context, activity);
        } else {
            ToastUtils.offlineToast(context);
        }
    }

    public interface FurtherActions {

        void onSuccess();
    }

    public static void displayNetworkActionResponse(String tag, String[] networkActionResponseArray) {

        class LogUtilsWrapper extends LogUtilsWrapperBase {

            private LogUtilsWrapper() {
                super(tag, BuildConfig.DEBUG);
            }
        }

        LogUtilsWrapper.debug("Network Action Response Array : " + Arrays.toString(networkActionResponseArray));
    }
}
