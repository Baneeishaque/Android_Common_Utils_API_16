package ndk.utils_android16.activities;

import ndk.utils_android16.network_task.REST_Select_Task;

public abstract class Login_Base_Custom_URL_Response_Handler_Return_JSON_Object extends Login_Base_Custom_URL_Return_JSON_Object {

    @Override
    public REST_Select_Task.Async_Response_JSON_object configure_JSON_object_handler() {

        return configure_JSON_OBJECT_RESPONSE_HANDLER();
    }

    protected abstract REST_Select_Task.Async_Response_JSON_object configure_JSON_OBJECT_RESPONSE_HANDLER();

}
