package ndk.util.tests;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ndk.utils.Activity_Utils;
import ndk.utils.activities.Login_Bundle;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Login_Test {

    //TODO : Check Network Response
    //TODO : Check Next Screen

    @Rule
    public ActivityTestRule<Login_Bundle> mActivityRule =
            new ActivityTestRule<Login_Bundle>(Login_Bundle.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = getInstrumentation().getTargetContext();

                    return Activity_Utils.construct_Intent_With_String_Extras(targetContext, Login_Bundle.class, new Pair[]{new Pair<>("APPLICATION_NAME", Application_Specification.APPLICATION_NAME), new Pair<>("NEXT_ACTIVITY_CLASS", "Splash_Version_OK"), new Pair<>("SELECT_USER_URL", "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_User.php")});
                }
            };

    @Test
    public void check_login() {

        // Type text and then press the button.
        onView(withId(R.id.username)).perform(typeText("banee_10_5"));
        onView(withId(R.id.passcode)).perform(typeText("9895204814"));
        onView(withId(R.id.sign_in_button)).perform(click());

    }


}
