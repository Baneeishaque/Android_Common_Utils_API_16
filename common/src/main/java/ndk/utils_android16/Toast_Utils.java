package ndk.utils_android16;

import android.content.Context;
import android.widget.Toast;

public class Toast_Utils {
    public static void longToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
