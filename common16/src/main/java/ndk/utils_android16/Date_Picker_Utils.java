package ndk.utils_android16;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class Date_Picker_Utils {

    public static void show_date_picker_up_to_today(Context context, DatePickerDialog.OnDateSetListener date_choose_listener, Calendar calendar) {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_choose_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(new Date().getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker(Context context, DatePickerDialog.OnDateSetListener date_chooser_listener, Calendar calendar) {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_chooser_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.show();
    }

    public static void show_date_picker_from_today(Context context, DatePickerDialog.OnDateSetListener date_chooser_listener, Calendar calendar) {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_chooser_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMinDate(new Date().getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker_up_to_today_plus(Context context, DatePickerDialog.OnDateSetListener date_chooser_listener, Calendar calendar) {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_chooser_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(DateUtils.addDays(new Date(), 1).getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker_from_selected_date_to_up_to_today(Context context, DatePickerDialog.OnDateSetListener date_chooser_listener, Calendar calendar) {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_chooser_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(new Date().getTime());
        date_Picker_Dialog.getDatePicker().setMinDate(calendar.getTime().getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker_from_selected_date_to_up_to_today_plus(Context context, DatePickerDialog.OnDateSetListener date_chooser_listener, Calendar calendar) {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_chooser_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(DateUtils.addDays(new Date(), 1).getTime());
        date_Picker_Dialog.getDatePicker().setMinDate(calendar.getTime().getTime());
        date_Picker_Dialog.show();

    }

    public static String get_Title_from_mysql_date_strings(String operation, String start_date, String end_date, String APPLICATION_NAME) {
        if (start_date.equals(end_date)) {
            return operation + " : " + DateUtils.mysqlDateStringToNormalDateString(start_date, APPLICATION_NAME);
        } else {
            return operation + " : " + DateUtils.mysqlDateStringToNormalDateString(start_date, APPLICATION_NAME) + " - " + DateUtils.mysqlDateStringToNormalDateString(end_date, APPLICATION_NAME);
        }
    }

    public static void show_date_picker_up_to_today_plus_2_days(Context context, DatePickerDialog.OnDateSetListener date_chooser_listener, String APPLICATION_NAME) {

        Calendar calendar = Calendar.getInstance();

        Log.d(APPLICATION_NAME, "Date : " + DateUtils.dateToNormalDateString(calendar.getTime()));
        Log.d(APPLICATION_NAME, "Year : " + calendar.get(Calendar.YEAR));
        Log.d(APPLICATION_NAME, "Month : " + calendar.get(Calendar.MONTH));
        Log.d(APPLICATION_NAME, "Day : " + calendar.get(Calendar.DAY_OF_MONTH));

        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_chooser_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        date_Picker_Dialog.getDatePicker().setMaxDate(DateUtils.addDays(new Date(), 2).getTime());

        date_Picker_Dialog.show();
    }

    public static void show_date_picker_from_selected_date_to_up_to_today_plus_2_days(Context context, DatePickerDialog.OnDateSetListener date_chooser_listener, Calendar calendar, String APPLICATION_NAME) {

        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date_chooser_listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        Log.d(APPLICATION_NAME, "Date : " + DateUtils.dateToNormalDateString(calendar.getTime()));
        Log.d(APPLICATION_NAME, "Year : " + calendar.get(Calendar.YEAR));
        Log.d(APPLICATION_NAME, "Month : " + calendar.get(Calendar.MONTH));
        Log.d(APPLICATION_NAME, "Day : " + calendar.get(Calendar.DAY_OF_MONTH));

        date_Picker_Dialog.getDatePicker().setMaxDate(DateUtils.addDays(new Date(), 2).getTime());

        date_Picker_Dialog.getDatePicker().setMinDate(calendar.getTime().getTime());

        date_Picker_Dialog.show();

    }
}
