package ndk.utils_android16.activities;

import ndk.utils_android16.network_task.HttpApiSelectTask;

public abstract class LoginBaseJsonObjectCustomResponseHandlerActivity extends LoginBaseActivity {

    @Override
    public HttpApiSelectTask.AsyncResponseJSONObject handleJsonObject() {

        return configure_JSON_OBJECT_HANDLER();
    }

    protected abstract HttpApiSelectTask.AsyncResponseJSONObject configure_JSON_OBJECT_HANDLER();
}
