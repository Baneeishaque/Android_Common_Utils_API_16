package ndk.utils_android16;

public class ApiUtils {

    public static String getSubSectionedHttpApi(String section, String subSection, String methodName, String serverIpAddress, String httpApiFolder, String fileExtension) {
        return serverIpAddress + "/" + httpApiFolder + "/" + section + "/" + subSection + "/" + methodName + fileExtension;
    }

    public static String getSectionedHttpApi(String section, String methodName, String serverIpAddress, String httpApiFolder, String fileExtension) {
        return serverIpAddress + "/" + httpApiFolder + "/" + section + "/" + methodName + fileExtension;
    }

    public static String getHttpApi(String methodName, String serverIpAddress, String httpApiFolder, String fileExtension) {
        return serverIpAddress + "/" + httpApiFolder + "/" + methodName + fileExtension;
    }

}
