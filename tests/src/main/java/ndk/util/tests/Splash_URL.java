package ndk.util.tests;

import ndk.utils.activities.Login;
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
        return Login.class;
    }

}
