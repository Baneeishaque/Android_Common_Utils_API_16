package ndk.utils_android16.activities;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import ndk.utils_android16.R;

public abstract class SplashWithAutomatedUpdateCustomLogoActivity extends SplashWithAutomatedUpdateActivity {

    protected abstract Drawable configure_LOGO();

    @Override
    public void initializeScreen() {
        super.initializeScreen();

        ImageView image_logo = findViewById(R.id.img_Logo);
        image_logo.setImageDrawable(configure_LOGO());
    }
}
