package ndk.utils_android16.activities;

import android.widget.TextView;

import ndk.utils_android16.R;

public abstract class SplashWithAutomatedUpdateCustomLogoCustomDeveloperNameActivity extends SplashWithAutomatedUpdateCustomLogoActivity {

    //TODO : Multiple inheritance
    protected abstract String configure_DEVELOPER_NAME();

    @Override
    public void initializeScreen() {
        super.initializeScreen();

        TextView text_developer = findViewById(R.id.text_Developer);
        text_developer.setText(configure_DEVELOPER_NAME());
    }
}
