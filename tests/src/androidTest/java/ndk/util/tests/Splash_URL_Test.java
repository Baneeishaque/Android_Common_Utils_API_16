package ndk.util.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Splash_URL_Test {

    @Rule
    public ActivityTestRule<Splash_URL> activity_Test_Rule = new ActivityTestRule<>(Splash_URL.class);

    @Test
    public void check_network_response() {
    }

}
