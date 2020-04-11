package ndk.utils_android16;

import android.text.TextUtils;
import android.widget.EditText;

import androidx.core.util.Pair;

import java.util.Objects;

public class ValidationUtils {

    public static void resetErrors(EditText[] editTexts) {
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }

    public static boolean emptyCheckEditText(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    public static boolean nonEmptyCheckEditText(EditText editText) {
        return !emptyCheckEditText(editText);
    }

    public static boolean emptyCheckEditTextArray(EditText[] editTexts) {
        for (EditText editText : editTexts) {
            if (nonEmptyCheckEditText(editText)) {
                return false;
            }
        }
        return true;
    }

    public static boolean nonEmptyCheckEditTextArray(EditText[] editTexts) {
        for (EditText editText : editTexts) {
            if (emptyCheckEditText(editText)) {
                return false;
            }
        }
        return true;
    }

    public static boolean zeroCheckInteger(int integer) {
        return integer == 0;
    }

    public static boolean nonZeroCheckInteger(int integer) {
        return !zeroCheckInteger(integer);
    }

    public static boolean zeroCheckIntegerArray(int[] integers) {
        for (int integer : integers) {
            if (nonZeroCheckInteger(integer)) {
                return false;
            }
        }
        return true;
    }

    public static boolean nonZeroCheckIntegerArray(int[] integers) {
        for (int integer : integers) {
            if (zeroCheckInteger(integer)) {
                return false;
            }
        }
        return true;
    }

    public static boolean zeroCheckDouble(double aDouble) {
        return zeroCheckInteger(Integer.parseInt(String.valueOf(aDouble)));
    }

    public static boolean zeroCheckDoubleArray(double[] doubles) {
        return zeroCheckIntegerArray(DoubleUtils.doubleArrayToIntegerArray(doubles));
    }

    public static boolean zeroCheckEditText(EditText editText) {
        return zeroCheckInteger(Integer.parseInt(editText.getText().toString()));
    }

    public static boolean nonZeroCheckEditText(EditText editText) {
        return nonZeroCheckInteger(Integer.parseInt(editText.getText().toString()));
    }

    public static Pair<Boolean, EditText> zeroCheckEditTextPairs(Pair<EditText, String>[] editTextErrorPairs) {

        for (Pair<EditText, String> editTextErrorPair : editTextErrorPairs) {

            if (nonZeroCheckEditText(Objects.requireNonNull(editTextErrorPair.first))) {
                editTextErrorPair.first.setError(editTextErrorPair.second);
                return new Pair<>(false, editTextErrorPair.first);
            }
        }

        return new Pair<>(true, null);
    }

    public static Pair<Boolean, EditText> emptyCheckEditTextPairs(Pair<EditText, String>[] editTextErrorPairs) {
        for (Pair<EditText, String> editTextErrorPair : editTextErrorPairs) {

            if (nonEmptyCheckEditText(editTextErrorPair.first)) {
                Objects.requireNonNull(editTextErrorPair.first).setError(editTextErrorPair.second);
                return new Pair<>(false, editTextErrorPair.first);
            }
        }
        return new Pair<>(true, null);
    }

    public static boolean doubleCheckString(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean doubleCheckEditText(EditText editText) {
        return doubleCheckString(editText.getText().toString());
    }

    public static boolean nonDoubleCheckString(String string) {
        return !doubleCheckString(string);
    }

    public static boolean nonDoubleCheckEditText(EditText editText) {
        return !doubleCheckEditText(editText);
    }

    public static Pair<Boolean, EditText> doubleCheckEditTextPairs(Pair<EditText, String>[] editTextErrorPairs) {
        for (Pair<EditText, String> editTextErrorPair : editTextErrorPairs) {
            if (nonDoubleCheckEditText(editTextErrorPair.first)) {
                Objects.requireNonNull(editTextErrorPair.first).setError(editTextErrorPair.second);
                return new Pair<>(false, editTextErrorPair.first);
            }
        }
        return new Pair<>(true, null);
    }

    public static boolean integerCheckString(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean integerCheckEditText(EditText editText) {
        return integerCheckString(editText.getText().toString());
    }

    public static boolean nonIntegerCheckString(String string) {
        return !integerCheckString(string);
    }

    public static boolean nonIntegerCheckEditText(EditText editText) {
        return !integerCheckEditText(editText);
    }

    public static Pair<Boolean, EditText> integerCheckEditTextPairs(Pair<EditText, String>[] editTextErrorPairs) {
        for (Pair<EditText, String> editTextErrorPair : editTextErrorPairs) {

            if (nonIntegerCheckEditText(editTextErrorPair.first)) {
                Objects.requireNonNull(editTextErrorPair.first).setError(editTextErrorPair.second);
                return new Pair<>(false, editTextErrorPair.first);
            }
        }
        return new Pair<>(true, null);
    }
}
