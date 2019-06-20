package ndk.utils_android16.activities;

import ndk.utils_android16.ErrorUtilsWrapperBase;

public class LoginBundleActivity extends LoginBaseActivity {

    @Override
    protected String configure_SELECT_USER_URL() {
        return getIntent().getStringExtra("SELECT_USER_URL");
    }

    @Override
    protected String configure_APPLICATION_NAME() {
        return getIntent().getStringExtra("APPLICATION_NAME");
    }

    @Override
    protected Class configure_NEXT_ACTIVITY_CLASS() {
        try {
            return Class.forName(getIntent().getStringExtra("NEXT_ACTIVITY_CLASS"));
        } catch (ClassNotFoundException e) {

            class ErrorUtilsWrapper extends ErrorUtilsWrapperBase {
                private ErrorUtilsWrapper() {
                    super(configure_APPLICATION_NAME());
                }
            }

            ErrorUtilsWrapper.displayException(activityContext, e);
            return null;
        }
    }
}

