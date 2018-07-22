package ndk.utils;

import android.content.Context;

public class Resource_Utils {

    private static int get_identifier(Context application_context, String resource_name, String resource_type) {
        return application_context.getResources().getIdentifier(application_context.getPackageName() + ":" + resource_type + "/" + resource_name, null, null);
    }

    public static int get_drawable_identifier(Context application_context, String resource_name) {
        return get_identifier(application_context, resource_name, "drawable");
    }

}
