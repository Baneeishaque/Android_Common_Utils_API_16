package ndk.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;

import com.github.kimkevin.cachepot.CachePot;

import java.util.Objects;

public class Activity_Utils {

    Context context;
    private String APPLICATION_NAME;
    private boolean is_debug;

    public Activity_Utils(Context context, String APPLICATION_NAME, boolean is_debug) {
        this.context = context;
        this.APPLICATION_NAME = APPLICATION_NAME;
        this.is_debug = is_debug;
    }

    private Intent construct_Intent_With_String_Extras(Class activity, Pair[] extras) {
        Intent intent = new Intent(context, activity);
        if (extras.length != 0) {
            for (Pair extra : extras) {
                intent.putExtra(extra.first != null ? extra.first.toString() : null, extra.second != null ? extra.second.toString() : null);
            }
        }
        return intent;
    }

    private Intent construct_Intent_With_Integer_Extras(Class activity, Pair[] extras) {
        Intent intent = new Intent(context, activity);
        if (extras.length != 0) {
            for (Pair extra : extras) {
                intent.putExtra(extra.first != null ? extra.first.toString() : null, Integer.parseInt(Objects.requireNonNull(extra.second != null ? extra.second.toString() : null)));
            }
        }
        return intent;
    }

    void start_activity(Class activity) {
        context.startActivity(new Intent(context, activity));
    }

    private void start_activity_with_integer_extras(Class activity, Pair[] extras) {
        context.startActivity(construct_Intent_With_Integer_Extras(activity, extras));
    }

    void start_activity_with_string_extras(Class activity, Pair[] extras, boolean for_result_flag, int request_code) {
        Intent intent = construct_Intent_With_String_Extras(activity, extras);
        if (for_result_flag) {
            ((AppCompatActivity) context).startActivityForResult(intent, request_code);
        } else {
            context.startActivity(intent);
        }
    }

    void start_activity_with_integer_extras_and_finish(Class activity, Pair[] extras) {
        start_activity_with_integer_extras(activity, extras);
        ((AppCompatActivity) context).finish();
    }

    public void start_activity_with_finish(Class activity) {

        Log_Utils log_utils = new Log_Utils(is_debug, APPLICATION_NAME);
        log_utils.debug("Next Activity : " + activity.getCanonicalName());
        log_utils.debug("Next Activity : " + activity.getName());
        log_utils.debug("Next Activity : " + activity.getSimpleName());

        context.startActivity(new Intent(context, activity));
        ((AppCompatActivity) context).finish();
    }

    public void start_activity_with_finish_and_tab_index(Class activity, int tab_index) {
        context.startActivity(new Intent(context, activity).putExtra("tab_index", tab_index));
        ((AppCompatActivity) context).finish();
    }

    private void start_activity_with_object_push(Class activity, Object object_to_push) {
        CachePot.getInstance().push(object_to_push);
        context.startActivity(new Intent(context, activity));
    }

    void start_activity_with_object_push_and_finish(Class activity, Object object_to_push) {
        start_activity_with_object_push(activity, object_to_push);
        ((AppCompatActivity) context).finish();
    }

    private void start_activity_with_object_push_and_integer_extras(Class activity, Pair[] extras, Object object_to_push) {
        CachePot.getInstance().push(object_to_push);
        context.startActivity(construct_Intent_With_Integer_Extras(activity, extras));
    }

    void start_activity_with_object_push_and_integer_extras_and_finish(Class activity, Pair[] extras, Object object_to_push) {
        start_activity_with_object_push_and_integer_extras(activity, extras, object_to_push);
        ((AppCompatActivity) context).finish();
    }

    private void start_activity_with_object_push_and_origin(Class activity, Object object_to_push, String origin) {
        CachePot.getInstance().push(object_to_push);
        context.startActivity(new Intent(context, activity).putExtra("origin", origin));
    }

    void start_activity_with_object_push_and_finish_and_origin(Class activity, Object object_to_push, String origin) {
        start_activity_with_object_push_and_origin(activity, object_to_push, origin);
        ((AppCompatActivity) context).finish();
    }

    public void start_activity_with_string_extras_and_finish(Class activity, Pair[] extras) {
        start_activity_with_integer_extras(activity, extras);
        ((AppCompatActivity) context).finish();
    }

    void close_activity() {
        ((AppCompatActivity) context).finish();
    }

    void close_activity_with_message(String message) {
        Toast_Utils.longToast(context, "System is in Maintenance, Try Again later...");
        close_activity();
    }

}
