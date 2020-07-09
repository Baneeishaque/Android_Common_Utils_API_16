package ndk.utils_android16;

import ndk.utils_android1.UpdateUtils;

public class UpdateUtilsWrapperBase {

    private static String applicationName;

    UpdateUtilsWrapperBase(String applicationName) {
        UpdateUtilsWrapperBase.applicationName = applicationName;
    }

    public static String[] getFlavouredServerVersion(String flavour, String fullVersionCheckUrl) {
        return UpdateUtils.getFlavouredServerVersion(flavour, fullVersionCheckUrl, applicationName);
    }

    public static String[] getServerVersion(String fullVersionCheckUrl) {
        return UpdateUtils.getServerVersion(fullVersionCheckUrl, applicationName);
    }
}
