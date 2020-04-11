package ndk.utils_android16;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created on 18-07-2018 08:53 under New folder.
 */
public class Custom_NotificationManager {

    private static Custom_NotificationManager mInstance;
    private Context mCtx;

    private Custom_NotificationManager(Context context) {
        mCtx = context;
    }

    public static synchronized Custom_NotificationManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Custom_NotificationManager(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body, int notification_image, Class pending_activity, String CHANNEL_ID) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, CHANNEL_ID)
                        .setSmallIcon(notification_image)
                        .setContentTitle(title)
                        .setContentText(body);


        /*
         *  Clicking on the notification will take us to this intent
         *  Right now we are using the MainActivity as this is the only activity we have in our application
         *  But for your project you can customize it as you want
         * */

        Intent resultIntent = new Intent(mCtx, pending_activity);

        /*
         *  Now we will create a pending intent
         *  The method getActivity is taking 4 parameters
         *  All paramters are describing themselves
         *  0 is the request code (the second parameter)
         *  We can detect this code in the activity that will open by this we can get
         *  Which notification opened the activity
         * */
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*
         *  Setting the pending intent to notification builder
         * */

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);

        /*
         * The first parameter is the notification id
         * better don't give a literal here (right now we are giving a int literal)
         * because using this id we can modify it later
         * */
        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }
}
