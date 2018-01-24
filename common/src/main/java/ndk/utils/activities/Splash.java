package ndk.utils.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.onehilltech.metadata.ManifestMetadata;

import ndk.utils.R;
import ndk.utils.network_task.update.Update_Check_Task;

import static ndk.utils.update.Check.attempt_Update_Check;

//TODO:Full screen splash

public class Splash extends AppCompatActivity {

    Context application_context;
    private Update_Check_Task Update_Task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        application_context = getApplicationContext();
        try {
            attempt_Update_Check(ManifestMetadata.get (this).getValue ("APPLICATION_NAME"),this, ManifestMetadata.get (this).getValue ("SELECT_CONFIGURATION_URL"), Update_Task, ManifestMetadata.get (this).getValue ("APK_UPDATE_URL"),Class.forName(ManifestMetadata.get (this).getValue ("NEXT_ACTIVITY_CLASS")));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "<meta-data> Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            Log.d("<meta-data> Error", e.getLocalizedMessage());
        } catch (ClassNotFoundException e) {
            Toast.makeText(this, "Next Class Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            try {
                Log.d(ManifestMetadata.get (this).getValue ("APPLICATION_NAME"), e.getLocalizedMessage());
            } catch (PackageManager.NameNotFoundException e1) {
                Toast.makeText(this, "<meta-data> Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d("<meta-data> Error", e.getLocalizedMessage());
            }
        }

    }
}
