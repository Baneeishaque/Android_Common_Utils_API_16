package ndk.util.tests;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ArrayToStringTest {

    @Test
    public void testArrayToStringSuccess() {
        String actualStringedIntegers = Arrays.toString(new int[]{5, 4, 3, 2, 1, 0});
        String expectedStringedIntegers = "[5, 4, 3, 2, 1, 0]";
        assertThat(actualStringedIntegers, is(equalTo(expectedStringedIntegers)));
    }

    @Test
    public void testArrayToStringFailure() {
        String actualStringedIntegers = Arrays.toString(new int[]{5, 4, 3, 2, 1, 0});
        String expectedStringedIntegers = "[0, 1, 2, 3, 4, 5]";
        assertThat(actualStringedIntegers, is(not(equalTo(expectedStringedIntegers))));
    }
}
