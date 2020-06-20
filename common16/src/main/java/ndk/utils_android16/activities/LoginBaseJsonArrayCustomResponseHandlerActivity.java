package ndk.utils_android16.activities;

import ndk.utils_android16.network_task.HttpApiSelectTask;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;

public abstract class LoginBaseJsonArrayCustomResponseHandlerActivity extends LoginBaseActivity {

    @Override
    public void performHttpApiSelectTask() {

        //Example Response : [{"user_count":"1","id":"125"},{"time_status":"1"}]
        HttpApiSelectTaskWrapper.executePostThenReturnJsonArrayWithoutErrorStatusCheck(configure_SELECT_USER_URL(), this, progressBar, scrollView, configure_APPLICATION_NAME(), configure_JSON_ARRAY_RESPONSE_HANDLER());
    }

    protected abstract HttpApiSelectTask.AsyncResponseJsonArray configure_JSON_ARRAY_RESPONSE_HANDLER();
}
