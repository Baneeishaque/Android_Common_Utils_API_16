package ndk.utils_android16;

public class DoubleUtils {

    public static int[] doubleArrayToIntegerArray(double[] doubles) {
        int[] integers = new int[doubles.length];
        for (int i = 0; i < integers.length; ++i) {
            integers[i] = (int) doubles[i];
        }
        return integers;
    }
}
