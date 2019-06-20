package ndk.utils_android16.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class InstallApk {
    public static void installApk(Uri uri, DownloadManager downloadManager, long downloadId, Context context, BroadcastReceiver download_complete_trigger) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        install.setDataAndType(uri, downloadManager.getMimeTypeForDownloadedFile(downloadId));
        context.startActivity(install);
        context.unregisterReceiver(download_complete_trigger);
        ((AppCompatActivity) context).finish();
    }
}
