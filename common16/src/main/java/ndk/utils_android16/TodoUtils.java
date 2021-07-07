package ndk.utils_android16;

import android.content.Context;

public class TodoUtils {
    public static void displayTodoSnackBar(Context context) {
        SnackbarUtils16.displayLongNoFabWarningBottomSnackBar(context, "To Implement...");
    }
}
