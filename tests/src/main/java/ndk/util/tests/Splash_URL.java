package ndk.util.tests;

import android.support.v4.util.Pair;

import ndk.utils.activities.Login_Base_Bundle;
import ndk.utils.activities.Splash_Base_URL;

public class Splash_URL extends Splash_Base_URL {

    @Override
    protected String configure_GET_CONFIGURATION_URL() {
        return "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_Configuration.php";
    }

    @Override
    protected String configure_UPDATE_URL() {
        return "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/builds/app-debug.apk";
    }

    @Override
    protected String configure_APPLICATION_NAME() {
        return "Splash_URL Tests";
    }

    @Override
    protected Class configure_NEXT_ACTIVITY_CLASS() {
        return Login_Base_Bundle.class;
    }

    @Override
    protected Pair[] configure_NEXT_ACTIVITY_CLASS_EXTRAS() {
        return new Pair[]{new Pair<>("APPLICATION_NAME", "Splash_URL Tests"), new Pair<>("NEXT_ACTIVITY_CLASS", "Splash_URL"), new Pair<>("SELECT_USER_URL", "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_User.php")};
    }

}
