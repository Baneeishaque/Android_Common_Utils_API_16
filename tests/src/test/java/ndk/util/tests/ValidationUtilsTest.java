package ndk.util.tests;

import org.junit.Test;

import ndk.utils_android16.ValidationUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ValidationUtilsTest {

    @Test
    public void testZeroCheckIntegerSuccess() {
        assertThat(true, is(equalTo(ValidationUtils.zeroCheckInteger(0))));
    }

    @Test
    public void testZeroCheckIntegerFailure() {
        assertThat(true, is(not(equalTo(ValidationUtils.zeroCheckInteger(1)))));
    }

    @Test
    public void testZeroCheckIntegerArraySuccess() {
        assertThat(true, is(equalTo(ValidationUtils.zeroCheckIntegerArray(new int[]{0, 0, 0}))));
    }

    @Test
    public void testZeroCheckIntegerArrayFailure() {
        assertThat(true, is(not(equalTo(ValidationUtils.zeroCheckIntegerArray(new int[]{0, 5, 0})))));
    }

    @Test
    public void testZeroCheckDoubleArraySuccess() {
        assertThat(true, is(equalTo(ValidationUtils.zeroCheckDoubleArray(new double[]{0.2, 0.45, 0}))));
    }

    @Test
    public void testZeroCheckDoubleArrayFailure() {
        assertThat(true, is(not(equalTo(ValidationUtils.zeroCheckDoubleArray(new double[]{0, 0.8, 5.2})))));
    }
}
