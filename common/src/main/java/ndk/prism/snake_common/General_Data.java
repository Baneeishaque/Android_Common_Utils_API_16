package ndk.prism.snake_common;

import javax.inject.Singleton;


@Singleton
public class General_Data {

    //    public static String SERVER_IP_ADDRESS = "192.168.1.200/lottery";
//    public static String SERVER_IP_ADDRESS = "192.168.43.89/lottery";
    public static String SERVER_IP_ADDRESS = "filebooker.com.md-1.webhostbox.net/lottery2";

    public static final String POS_LITE_UPDATE_URL = SERVER_IP_ADDRESS+"/builds/pos_lite-debug.apk";
    public static final String MASTER_UPDATE_URL = SERVER_IP_ADDRESS+"/builds/admin-debug.apk";
    public static final String POS_UPDATE_URL = SERVER_IP_ADDRESS+"/builds/pos-debug.apk";
    public static final String RESELLER_UPDATE_URL = SERVER_IP_ADDRESS+"/builds/reseller-debug.apk";
    public static String TAG = "Lottery";

    public static String SHARED_PREFERENCE = "Lottery";
}
