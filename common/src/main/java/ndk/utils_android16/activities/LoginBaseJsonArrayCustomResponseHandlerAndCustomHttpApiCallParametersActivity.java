package ndk.utils_android16.activities;

import androidx.core.util.Pair;

public abstract class LoginBaseJsonArrayCustomResponseHandlerAndCustomHttpApiCallParametersActivity extends LoginBaseJsonArrayCustomResponseHandlerActivity {

    public Pair[] configureHttpApiCallParameters() {
        return configure_HTTP_API_CALL_PARAMETERS();
    }

    protected abstract Pair[] configure_HTTP_API_CALL_PARAMETERS();

}
