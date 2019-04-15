package ndk.utils.activities;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import ndk.utils.R;

public abstract class Splash_Base_URL_Logo_Developer extends Splash_Base_URL {

    protected abstract String configure_DEVELOPER();

    protected abstract Drawable configure_LOGO();

    @Override
    void perform_customization() {
        setContentView(R.layout.splash);

        TextView text_developer = findViewById(R.id.text_Developer);
        text_developer.setText(configure_DEVELOPER());

        ImageView image_logo = findViewById(R.id.img_Logo);
        image_logo.setImageDrawable(configure_LOGO());
    }
}
