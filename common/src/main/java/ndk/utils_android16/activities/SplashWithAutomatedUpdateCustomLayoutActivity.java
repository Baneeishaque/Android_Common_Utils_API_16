package ndk.utils_android16.activities;

//TODO : Use fragment instead of layout
public abstract class SplashWithAutomatedUpdateCustomLayoutActivity extends SplashWithAutomatedUpdateActivity {

    protected abstract int configure_LAYOUT();

    @Override
    public void initializeScreen() {
        setContentView(configure_LAYOUT());
    }
}
