package ndk.utils_android16.activities;

import java.util.Objects;

import ndk.utils_android1.ErrorUtils;

public class LoginBundleActivity extends LoginBaseActivity {

    @Override
    public String configure_SELECT_USER_URL() {

        return getIntent().getStringExtra("SELECT_USER_URL");
    }

    @Override
    public String configure_APPLICATION_NAME() {

        return getIntent().getStringExtra("APPLICATION_NAME");
    }

    @Override
    public Class configure_NEXT_ACTIVITY_CLASS() {

        try {

            return Class.forName(Objects.requireNonNull(getIntent().getStringExtra("NEXT_ACTIVITY_CLASS")));

        } catch (ClassNotFoundException e) {

            ErrorUtils.displayException(this, e, configure_APPLICATION_NAME());
            return null;
        }
    }

    @Override
    public String configureTestPassword() {

        return "9895204814";
    }

    @Override
    public String configureTestUsername() {

        return "banee_ishaque_k_10_04_2019";
    }
}
