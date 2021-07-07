package ndk.utils_android16;

import android.content.Context;

import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SnackbarUtils16 {

    public static void displayShortNoFabSuccessBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.SUCCESS)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortNoFabErrorBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.ERROR)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortNoFabUpdateBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.UPDATE)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortNoFabWarningBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.WARNING)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortNoFabCustomBottomSnackBar(Context context, String message, int color) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.CUSTOM, color)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayLongNoFabSuccessBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.SUCCESS)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongNoFabErrorBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.ERROR)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongNoFabUpdateBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.UPDATE)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongNoFabWarningBottomSnackBar(Context context, String message) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.WARNING)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongNoFabCustomBottomSnackBar(Context context, String message, int color) {
        com.chootdev.csnackbar.Snackbar.with(context, null)
                .type(Type.CUSTOM, color)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayShortFabSuccessBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.SUCCESS)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortFabErrorBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.ERROR)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortFabUpdateBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.UPDATE)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortFabWarningBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.WARNING)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayShortFabCustomBottomSnackBar(Context context, String message, int color, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.CUSTOM, color)
                .message(message)
                .duration(Duration.SHORT)
                .show();
    }

    public static void displayLongFabSuccessBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.SUCCESS)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongFabErrorBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.ERROR)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongFabUpdateBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.UPDATE)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongFabWarningBottomSnackBar(Context context, String message, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.WARNING)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }

    public static void displayLongFabCustomBottomSnackBar(Context context, String message, int color, FloatingActionButton floatingActionButton) {
        com.chootdev.csnackbar.Snackbar.with(context, floatingActionButton)
                .type(Type.CUSTOM, color)
                .message(message)
                .duration(Duration.LONG)
                .show();
    }
}
