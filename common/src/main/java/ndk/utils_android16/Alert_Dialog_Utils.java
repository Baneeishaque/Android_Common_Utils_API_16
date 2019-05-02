package ndk.utils_android16;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

/**
 * Created on 20-09-2018 21:43 under VLottery.
 */
public class Alert_Dialog_Utils {

    Yes_Button_Actions yes_button_actions = null;
    No_Button_Actions no_button_actions = null;

    public Alert_Dialog_Utils(Yes_Button_Actions yes_button_actions, No_Button_Actions no_button_actions) {
        this.yes_button_actions = yes_button_actions;
        this.no_button_actions = no_button_actions;
    }

    public void ok_Dialogue(Context activity_context, String message, boolean cancelable) {

        AlertDialog alert_Dialog = get_OK_Dialogue(activity_context, message, "", cancelable);
        alert_Dialog.show();

    }

    public void titled_OK_Dialogue(Context activity_context, String message, String title, boolean cancelable) {

        AlertDialog alert_Dialog = get_OK_Dialogue(activity_context, message, title, cancelable);
        alert_Dialog.show();

    }

    public void yes_No_Dialogue(Context activity_context, String message, boolean cancelable) {

        AlertDialog alert_Dialog = get_Yes_No_Dialogue(activity_context, message, "", cancelable);
        alert_Dialog.show();

    }

    public void titled_Yes_No_Dialogue(Context activity_context, String message, String title, boolean cancelable) {

        AlertDialog alert_Dialog = get_Yes_No_Dialogue(activity_context, message, title, cancelable);
        alert_Dialog.show();

    }

    private AlertDialog get_OK_Dialogue(Context activity_context, String message, String title, boolean cancelable) {

        return get_Dialogue(activity_context, message, "OK", "", title, cancelable);

    }

    private AlertDialog get_Yes_No_Dialogue(Context activity_context, String message, String title, boolean cancelable) {

        return get_Dialogue(activity_context, message, "Yes", "No", title, cancelable);

    }

    private AlertDialog get_Dialogue(Context activity_context, String message, String positive_button_text, String negative_button_text, String title, boolean cancelable) {

        AlertDialog.Builder alert_dialogue = new AlertDialog.Builder(activity_context);
        alert_dialogue
                .setMessage(message)
                .setPositiveButton(positive_button_text, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yes_button_actions.configure_yes_button_actions(dialog, which);
                    }

                })
                .setCancelable(cancelable);

        if (!negative_button_text.isEmpty()) {
            alert_dialogue.setNegativeButton(negative_button_text, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    no_button_actions.configure_no_button_actions(dialog, which);
                }

            });
        }

        if (!title.isEmpty()) {
            alert_dialogue.setTitle(title);
        }

        return alert_dialogue.create();
    }

    public interface Yes_Button_Actions {
        void configure_yes_button_actions(DialogInterface dialog, int which);
    }

    public interface No_Button_Actions {
        void configure_no_button_actions(DialogInterface dialog, int which);
    }

}
