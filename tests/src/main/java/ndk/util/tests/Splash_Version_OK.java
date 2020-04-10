package ndk.util.tests;

import androidx.core.util.Pair;

import ndk.utils_android16.activities.LoginBundleActivity;
import ndk.utils_android16.activities.SplashWithAutomatedUpdateActivity;

//TODO : Develop Backend - Single Method with operation variants
//TODO : Add Mismatch version URL
//TODO : Add System maintenance URL
//TODO : Add Login Test with backend API

public class Splash_Version_OK extends SplashWithAutomatedUpdateActivity {

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

        return Application_Specification.APPLICATION_NAME;
    }

    @Override
    protected Class configure_NEXT_ACTIVITY_CLASS() {
        return LoginBundleActivity.class;
    }

    @Override
    protected Pair[] configure_NEXT_ACTIVITY_CLASS_EXTRAS() {

        return new Pair[] { new Pair<>("APPLICATION_NAME", Application_Specification.APPLICATION_NAME),
                new Pair<>("NEXT_ACTIVITY_CLASS", "Splash_Version_OK"), new Pair<>("SELECT_USER_URL",
                        "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_User.php") };
    }

    @Override
    protected boolean configure_SECURITY_FLAG() {

        return BuildConfig.DEBUG;
    }
}
