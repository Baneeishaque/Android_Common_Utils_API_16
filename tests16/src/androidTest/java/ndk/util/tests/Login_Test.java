package ndk.util.tests;

import android.content.Context;
import android.content.Intent;

import androidx.core.util.Pair;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android16.activities.LoginBundleActivity;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will executePostThenReturnJsonObject on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class Login_Test {

    //TODO : Application_Utils Network Response
    //TODO : Application_Utils Next Screen

    @Rule
    public ActivityTestRule<LoginBundleActivity> mActivityRule = new ActivityTestRule<LoginBundleActivity>(LoginBundleActivity.class) {
        @Override
        protected Intent getActivityIntent() {

            Context targetContext = getInstrumentation().getTargetContext();

            return ActivityUtils.getIntentWithStringExtras(targetContext, LoginBundleActivity.class, new Pair[]{new Pair<>("APPLICATION_NAME", Application_Specification.APPLICATION_NAME), new Pair<>("NEXT_ACTIVITY_CLASS", "Splash_Version_OK"), new Pair<>("SELECT_USER_URL", "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_User.php")});
        }
    };

    @Test
    public void check_login() {

        // Type text and then press the button.
        onView(withId(R.id.editText_username)).perform(typeText("banee_10_5"));
        onView(withId(R.id.editText_password)).perform(typeText("9895204814"));
        onView(withId(R.id.button_sign_in)).perform(click());

    }


}
