package ndk.util.tests;

import org.junit.Test;

import ndk.utils_android16.DoubleUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class DoubleUtilsTest {

    @Test
    public void testDoubleArrayToIntegerArrayConversionSuccess() {
        assertThat(DoubleUtils.doubleArrayToIntegerArray(new double[]{0.6, 1.3, 2.5, 3.0, 4, 5}), is(equalTo(new int[]{0, 1, 2, 3, 4, 5})));
    }

    @Test
    public void testDoubleArrayToIntegerArrayConversionFailure() {
        assertThat(DoubleUtils.doubleArrayToIntegerArray(new double[]{0.6, 1.3, 2.5, 3.0, 4, 5}), is(not(equalTo(new int[]{5, 4, 3, 2, 1, 0}))));
    }
}
