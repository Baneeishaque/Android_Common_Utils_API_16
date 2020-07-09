package ndk.utils_android16.update;

import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android11.ApplicationVCSUtils;

public class UpdateApplication {

    public static void updateApplication(final String applicationName, final AppCompatActivity appCompatActivity, final float versionName, final String updateUrl, boolean securityFlag) {

        //TODO : Use Alert Dialog Utils
        AlertDialog.Builder builder1 = new AlertDialog.Builder(appCompatActivity);
        builder1.setMessage("New version is available, please update...").setCancelable(false).setPositiveButton("Update", (dialog, id) -> ApplicationVCSUtils.downloadAndInstallApk(applicationName, versionName, updateUrl, appCompatActivity));
        AlertDialog alert = builder1.create();
        alert.setTitle("Warning!");
        alert.show();
    }
}
