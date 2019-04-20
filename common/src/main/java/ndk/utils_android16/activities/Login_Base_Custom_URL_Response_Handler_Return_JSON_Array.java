package ndk.utils_android16.activities;

import ndk.utils_android16.network_task.REST_Select_Task;
import ndk.utils_android16.network_task.REST_Select_Task_Wrapper;

/**
 * Created on 24-08-2018 21:08 under VLottery.
 */
public abstract class Login_Base_Custom_URL_Response_Handler_Return_JSON_Array extends Login_Base_Custom_URL {

    @Override
    protected void configure_FURTHER_PROCESSING() {

        //Example Response : [{"user_count":"1","id":"125"},{"time_status":"1"}]
        REST_Select_Task_Wrapper.execute(configure_SELECT_USER_URL(), activity_context, mProgressView, mLoginFormView, configure_APPLICATION_NAME(), configure_http_call_parameters(), configure_JSON_ARRAY_RESPONSE_HANDLER(), false);
    }

    protected abstract String configure_APPLICATION_NAME();

    protected abstract REST_Select_Task.Async_Response_JSON_array configure_JSON_ARRAY_RESPONSE_HANDLER();

    protected abstract String configure_SELECT_USER_URL();
}
