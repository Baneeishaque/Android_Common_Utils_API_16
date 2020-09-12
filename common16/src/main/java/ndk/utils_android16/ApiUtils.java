package ndk.utils_android16;

public class ApiUtils {

    public static String getSubSectionedHttpApi(String section, String subSection, String methodName, String serverIpAddress, String httpApiFolder, String fileExtension) {

        return getSectionedHttpApi(section, subSection + "/" + methodName, serverIpAddress, httpApiFolder, fileExtension);
    }

    public static String getSectionedHttpApi(String section, String methodName, String serverIpAddress, String httpApiFolder, String fileExtension) {

        return getHttpApi(section + "/" + methodName, serverIpAddress, httpApiFolder, fileExtension);
    }

    public static String getHttpApi(String methodName, String serverIpAddress, String httpApiFolder, String fileExtension) {

        return serverIpAddress + "/" + httpApiFolder + "/" + methodName+fileExtension;
    }
}
