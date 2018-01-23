package ndk.utils.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import ndk.utils.R;
import ndk.utils.network_tasks.Load_Pass_Book_Task;
import ndk.utils.widgets.pass_book.Pass_Book_TableView;

import static ndk.utils.ProgressBar_Utils.showProgress;

public class Pass_Book extends AppCompatActivity {

    private Context application_context;

    private ProgressBar mProgressView;
    private Pass_Book_TableView pass_book_tableView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_book);
        initView();

        application_context = getApplicationContext();
        if (load_pass_Book_task != null) {
            load_pass_Book_task.cancel(true);
            load_pass_Book_task = null;
        }

        showProgress(true,application_context,mProgressView,pass_book_tableView);
        load_pass_Book_task = new Load_Pass_Book_Task();
        load_pass_Book_task.execute((Void) null);
    }

    private void initView() {
        mProgressView = findViewById(R.id.login_progress);
        pass_book_tableView = findViewById(R.id.tableView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_work, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_item_save) {
            if (create_Account_Ledger_Pdf())
                promptForNextAction();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Load_Pass_Book_Task load_pass_Book_task = null;
}
