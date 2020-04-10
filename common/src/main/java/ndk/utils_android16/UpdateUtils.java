package ndk.utils_android16;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ndk.utils_android14.LogUtilsWrapperBase;

public class UpdateUtils {

    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            return 0;
        }

    }

    public static float getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return Float.parseFloat(pi.versionName);
        } catch (PackageManager.NameNotFoundException ex) {
            return 0;
        }

    }

    public static String[] getFlavouredServerVersion(String flavour, String fullVersionCheckUrl, String applicationName) {

        //TODO : Use wrappers
        class LogUtilsWrapper extends LogUtilsWrapperBase {
            private LogUtilsWrapper() {
                super(applicationName);
            }
        }

        String networkActionResponse;
        try {
            // Network access.
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(fullVersionCheckUrl);
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("flavour", flavour));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();
            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);
            LogUtilsWrapper.debug("Network Action Response : " + networkActionResponse);
            return new String[]{"0", networkActionResponse};

        } catch (UnsupportedEncodingException e) {
            return new String[]{"1", "UnsupportedEncodingException : " + e.getLocalizedMessage()};
        } catch (ClientProtocolException e) {
            return new String[]{"1", "ClientProtocolException : " + e.getLocalizedMessage()};
        } catch (IOException e) {
            return new String[]{"1", "IOException : " + e.getLocalizedMessage()};
        }
    }

    public static String[] getServerVersion(String fullVersionCheckUrl, String applicationName) {

        class LogUtilsWrapper extends LogUtilsWrapperBase {
            private LogUtilsWrapper() {
                super(applicationName);
            }
        }

        String networkActionResponse;
        try {
            // Network access.
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(fullVersionCheckUrl);

            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();
            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);
            LogUtilsWrapper.debug("Network Action Response : " + networkActionResponse);
            return new String[]{"0", networkActionResponse};

        } catch (UnsupportedEncodingException e) {
            return new String[]{"1", "UnsupportedEncodingException : " + e.getLocalizedMessage()};
        } catch (ClientProtocolException e) {
            return new String[]{"1", "ClientProtocolException : " + e.getLocalizedMessage()};
        } catch (IOException e) {
            return new String[]{"1", "IOException : " + e.getLocalizedMessage()};
        }
    }
}
