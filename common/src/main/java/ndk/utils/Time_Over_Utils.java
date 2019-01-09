package ndk.utils;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created on 24-08-2018 21:22 under VLottery.
 */
public class Time_Over_Utils {

    private Time_Over_Actions time_over_actions = null;
    private Time_Not_Over_Actions time_not_over_actions = null;

    public Time_Over_Utils(Time_Over_Actions time_over_actions, Time_Not_Over_Actions time_not_over_actions) {

        this.time_over_actions = time_over_actions;
        this.time_not_over_actions = time_not_over_actions;

    }

    public void check_Time_Status(AppCompatActivity activity, JSONArray json_array, String APPLICATION_NAME, boolean is_debug) {
        try {

            //Example Response : [{"user_count":"1","id":"125"},{"time_status":"1"}]
            switch (json_array.getJSONObject(1).getString("time_status")) {
                case "0": //OK
                    time_not_over_actions.configure_time_not_over_actions();
                    break;

                case "1": //Time Over
                    time_over_actions.configure_time_over_actions();
                    break;

                case "-1": //Close Application
                    Server_Utils.close_application(activity);
                    break;
            }

        } catch (JSONException json_exception) {

            Error_Utils.display_Exception(activity, json_exception, APPLICATION_NAME, is_debug);

        }
    }

    public interface Time_Over_Actions {
        void configure_time_over_actions();
    }

    public interface Time_Not_Over_Actions {
        void configure_time_not_over_actions();
    }
}
