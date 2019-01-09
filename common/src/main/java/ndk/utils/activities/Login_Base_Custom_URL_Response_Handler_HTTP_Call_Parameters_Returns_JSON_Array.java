package ndk.utils.activities;

import android.support.v4.util.Pair;

/**
 * Created on 24-08-2018 21:16 under VLottery.
 */
public abstract class Login_Base_Custom_URL_Response_Handler_HTTP_Call_Parameters_Returns_JSON_Array extends Login_Base_Custom_URL_Response_Handler_Return_JSON_Array {

    @Override
    public Pair[] configure_http_call_parameters() {
        return configure_HTTP_CALL_PARAMETERS();
    }

    protected abstract Pair[] configure_HTTP_CALL_PARAMETERS();

}
