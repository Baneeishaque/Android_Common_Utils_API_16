package ndk.utils_android16.activities;

import android.view.MenuItem;

import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.TODO_Utils;

public class Options_Item_Selected_TODO_Activity extends ContextActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TODO_Utils.display_TODO_no_FAB_SnackBar(activityContext);
        return true;

    }
}
