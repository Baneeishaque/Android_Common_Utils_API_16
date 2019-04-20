package ndk.utils_android16;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

import ndk.utils_android16.models.sortable_tableView.pass_book.Pass_Book_Entry;
import ndk.utils_android16.models.sortable_tableView.pass_book.Pass_Book_Entry_v2;
import ndk.utils_android16.widgets.pass_book.Pass_Book_TableView;
import ndk.utils_android16.widgets.pass_book.Pass_Book_TableView_Data_Adapter;
import ndk.utils_android16.widgets.pass_book.Pass_Book_TableView_Data_Adapter_v2;
import ndk.utils_android16.widgets.pass_book.Pass_Book_TableView_v2;

public class Pass_Book_Utils {

    private static ArrayList<Pass_Book_Entry> current_pass_book_entries;
    private static ArrayList<Pass_Book_Entry_v2> current_pass_book_entries_v2;

    private static boolean v2_flag;

    public static void bind(Pass_Book_TableView pass_book_tableView, Context context, ArrayList<Pass_Book_Entry> pass_book_entries) {
        if (pass_book_tableView != null) {
            final Pass_Book_TableView_Data_Adapter pass_book_tableView_data_adapter = new Pass_Book_TableView_Data_Adapter(context, pass_book_entries, pass_book_tableView);
            pass_book_tableView.setDataAdapter(pass_book_tableView_data_adapter);
            current_pass_book_entries = pass_book_entries;
            v2_flag = false;
        }
    }

    public static void bindv2(Pass_Book_TableView_v2 pass_book_tableView_v2, Context context, ArrayList<Pass_Book_Entry_v2> pass_book_entries_v2) {
        if (pass_book_tableView_v2 != null) {
            final Pass_Book_TableView_Data_Adapter_v2 pass_book_tableView_data_adapter_v2 = new Pass_Book_TableView_Data_Adapter_v2(context, pass_book_entries_v2, pass_book_tableView_v2);
            pass_book_tableView_v2.setDataAdapter(pass_book_tableView_data_adapter_v2);
            pass_book_tableView_v2.setScrollY(pass_book_tableView_v2.getBottom());
            current_pass_book_entries_v2 = pass_book_entries_v2;
            v2_flag = true;
        }
    }

    static void email_Pass_Book(String application_name, String time_stamp, String email_subject, String email_text, File pass_book_pdf, Context context) {
        if (email_subject.isEmpty() && email_text.isEmpty()) {
            Email_Utils.email_attachment(application_name + " : Pass Book : " + time_stamp, "See attachment...", pass_book_pdf, context);
        } else if (email_text.isEmpty()) {
            Email_Utils.email_attachment(email_subject, "See attachment...", pass_book_pdf, context);
        } else {
            Email_Utils.email_attachment(email_subject, email_text + "\nSee attachment...", pass_book_pdf, context);
        }
    }
}
