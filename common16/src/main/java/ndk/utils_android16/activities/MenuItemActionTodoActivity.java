package ndk.utils_android16.activities;

import android.view.MenuItem;

import ndk.utils_android1.ActivityWithContexts;
import ndk.utils_android16.TodoUtils;

public class MenuItemActionTodoActivity extends ActivityWithContexts {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TodoUtils.displayTodoSnackBar(currentActivityContext);
        return true;
    }
}
