package ndk.utils.activities;

import android.view.MenuItem;

import ndk.utils.TODO_Utils;

public class Options_Item_Selected_TODO_Activity extends Context_Activity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TODO_Utils.display_TODO_no_FAB_SnackBar(activity_context);
        return true;

    }
}
