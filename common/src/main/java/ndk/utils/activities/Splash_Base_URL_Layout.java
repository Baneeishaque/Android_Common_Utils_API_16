package ndk.utils.activities;

//TODO : Use fragment instead of layout
public abstract class Splash_Base_URL_Layout extends Splash_Base_URL {

    protected abstract int configure_LAYOUT();

    @Override
    void perform_customization() {
        setContentView(configure_LAYOUT());
    }

}
