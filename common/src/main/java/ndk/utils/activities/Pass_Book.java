package ndk.utils.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ndk.utils.R;
import ndk.utils.models.sortable_tableView.pass_book.Pass_Book_Entry;
import ndk.utils.network_tasks.Load_Pass_Book_Task;
import ndk.utils.widgets.pass_book.Pass_Book_TableView;

import static ndk.utils.Pass_Book_Utils.create_Pass_Book_Pdf;
import static ndk.utils.Pdf_Utils.prompt_For_Next_Action_After_Creation;
import static ndk.utils.ProgressBar_Utils.showProgress;

public class Pass_Book extends AppCompatActivity {

    private ProgressBar mProgressView;
    private Pass_Book_TableView pass_book_tableView;
    ArrayList<Pass_Book_Entry> pass_book_entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_book);
        initView();

        if (load_pass_Book_task != null) {
            load_pass_Book_task.cancel(true);
            load_pass_Book_task = null;
        }

//        String URL="";
//        String TAG="";
//        String application_name="";
//        Intent intent = new Intent(getBaseContext(), Pass_Book.class);
//        intent.putExtra("URL",URL);
//        intent.putExtra("TAG",TAG);
//        intent.putExtra("application_name",application_name);
//        startActivity(intent);

        showProgress(true, this, mProgressView, pass_book_tableView);
        load_pass_Book_task = new Load_Pass_Book_Task(getIntent().getStringExtra("URL"), load_pass_Book_task, this, mProgressView, pass_book_tableView, getIntent().getStringExtra("TAG"), pass_book_tableView, pass_book_entries);
        load_pass_Book_task.execute((Void) null);

    }

    private void initView() {
        mProgressView = findViewById(R.id.login_progress);
        pass_book_tableView = findViewById(R.id.tableView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pass_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String time_stamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        File pass_book_pdf = new File(new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), getIntent().getStringExtra("application_name")) + "/Pass_Book_" + time_stamp + ".pdf");

        if (id == R.id.menu_item_save) {
            if (create_Pass_Book_Pdf(getIntent().getStringExtra("TAG"), pass_book_entries, this, pass_book_pdf, getIntent().getStringExtra("application_name")))
                prompt_For_Next_Action_After_Creation(this, "Pass Book Saved, What Next?", pass_book_pdf, getIntent().getStringExtra("application_name"), time_stamp, "", "");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Load_Pass_Book_Task load_pass_Book_task = null;
}
