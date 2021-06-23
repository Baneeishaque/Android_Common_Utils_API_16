package ndk.util.tests;

import org.junit.Test;

import ndk.utils_android16.ValidationUtils16;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ValidationUtils16Test {

    @Test
    public void testZeroCheckIntegerSuccess() {

        assertThat(true, is(equalTo(ValidationUtils16.zeroCheckInteger(0))));
    }

    @Test
    public void testZeroCheckIntegerFailure() {

        assertThat(true, is(not(equalTo(ValidationUtils16.zeroCheckInteger(1)))));
    }

    @Test
    public void testZeroCheckIntegerArraySuccess() {

        assertThat(true, is(equalTo(ValidationUtils16.zeroCheckIntegerArray(new int[]{0, 0, 0}))));
    }

    @Test
    public void testZeroCheckIntegerArrayFailure() {

        assertThat(true, is(not(equalTo(ValidationUtils16.zeroCheckIntegerArray(new int[]{0, 5, 0})))));
    }

    @Test
    public void testZeroCheckDoubleArraySuccess() {

        assertThat(true, is(equalTo(ValidationUtils16.zeroCheckDoubleArray(new double[]{0.2, 0.45, 0}))));
    }

    @Test
    public void testZeroCheckDoubleArrayFailure() {

        assertThat(true, is(not(equalTo(ValidationUtils16.zeroCheckDoubleArray(new double[]{0, 0.8, 5.2})))));
    }
}
