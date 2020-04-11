package ndk.utils_android16.activities;

import androidx.core.util.Pair;

public abstract class LoginBaseJsonObjectCustomResponseHandlerAndCustomHttpApiCallParametersActivity extends LoginBaseJsonObjectCustomResponseHandlerActivity {

    @Override
    public Pair[] configureHttpApiCallParameters() {
        return configure_HTTP_API_CALL_PARAMETERS();
    }

    protected abstract Pair[] configure_HTTP_API_CALL_PARAMETERS();
}
