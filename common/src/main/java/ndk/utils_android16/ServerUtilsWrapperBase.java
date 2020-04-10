package ndk.utils_android16;

import android.content.Context;

public class ServerUtilsWrapperBase {

    private static String applicationName;
    private static String systemStatus;

    public ServerUtilsWrapperBase(String applicationName, String systemStatus) {
        ServerUtilsWrapperBase.applicationName = applicationName;
        ServerUtilsWrapperBase.systemStatus = systemStatus;
    }

    public static void checkSystemStatusWithMessage(Context activityContext) {
        ServerUtils.checkSystemStatusWithMessage(activityContext, systemStatus, applicationName);
    }

    public static void checkSystemStatus(Context activityContext) {
        ServerUtils.checkSystemStatus(activityContext, systemStatus, applicationName);
    }
}
