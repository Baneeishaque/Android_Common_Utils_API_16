package ndk.utils_android16.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.firebase.firestore.FirebaseFirestore;

import ndk.utils_android14.ActivityWithContexts;
import ndk.utils_android16.R;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;
import ndk.utils_android16.network_task.update.CheckAndUpdateTaskFireStoreWrapper;

//TODO : Full screen splash
//TODO : Implement hiding of fields - in case of layout
//TODO : Develop tests

public abstract class SplashWithAutomatedUpdateFireStoreActivity extends SplashWithAutomatedUpdateActivity {

    public void checkThenPerformUpdateIfNeeded() {

        HttpApiSelectTaskWrapper.performSplashScreenThenReturnJsonArray(this, configure_GET_CONFIGURATION_URL(), configure_APPLICATION_NAME(), jsonArray -> CheckAndUpdateTaskFireStoreWrapper.getCheckAndUpdateWithoutTabIndexTask(configure_APPLICATION_NAME(), (AppCompatActivity) currentActivityContext, configure_GET_CONFIGURATION_URL(), configure_UPDATE_URL(), configure_NEXT_ACTIVITY_CLASS(), configure_SECURITY_FLAG(), configure_NEXT_ACTIVITY_CLASS_EXTRAS(), configureFireStoreDb(), getApplicationContext()).execute());
    }

    public abstract FirebaseFirestore configureFireStoreDb();
}
