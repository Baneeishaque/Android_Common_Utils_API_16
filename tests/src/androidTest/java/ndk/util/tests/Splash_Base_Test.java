package ndk.util.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Splash_Base_Test {

    @Rule
    public ActivityTestRule<Splash> activity_Test_Rule = new ActivityTestRule<>(Splash.class);

    @Before
    public void setUp() {

    }

}
