package ndk.utils_android16;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.itextpdf.text.Paragraph;

import java.io.File;

import static ndk.utils_android16.Pass_Book_Utils.email_Pass_Book;

/**
 * Created by Nabeel on 23-01-2018.
 */

public class Pdf_Utils {

    static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static void prompt_For_Next_Action_After_Creation(final Context context, String dialog_Title, final File pass_book_pdf, final String application_name, final String time_stamp, final String email_subject, final String email_text) {
        final String[] options = {
                "Preview It",
                "Cancel"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Pass Book Saved, What Next?");
        builder.setTitle(dialog_Title);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (options[which]) {
                    case "Email It":
                        email_Pass_Book(application_name, time_stamp, email_subject, email_text, pass_book_pdf, context);
                        break;
                    case "Preview It":
                        viewPdf(pass_book_pdf, context);
                        break;
                    case "Cancel":
                        dialog.dismiss();
                        break;
                }
            }
        });

        builder.show();

    }

    private static void viewPdf(File pass_book_pdf, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(pass_book_pdf), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }
}
