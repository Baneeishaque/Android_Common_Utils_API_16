package ndk.utils_android16;

import android.widget.EditText;

public class TextClearUtils {

    public static void resetFields(EditText[] editTexts) {
        if (editTexts.length != 0) {
            for (EditText editText : editTexts) {
                editText.setText("");
            }
        }
    }

    public static void resetFieldsAndFocus(EditText[] editTexts, EditText viewToFocus) {
        resetFields(editTexts);
        viewToFocus.requestFocus();
    }
}
