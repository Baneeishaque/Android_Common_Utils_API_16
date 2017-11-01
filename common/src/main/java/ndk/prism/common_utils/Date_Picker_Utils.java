package ndk.prism.common_utils;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;

public class Date_Picker_Utils {

    public static void show_date_picker_upto_today(Context context, DatePickerDialog.OnDateSetListener date, Calendar calendar)
    {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(new Date().getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker(Context context, DatePickerDialog.OnDateSetListener date, Calendar calendar)
    {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.show();
    }

    public static void show_date_picker_from_today(Context context, DatePickerDialog.OnDateSetListener date, Calendar calendar)
    {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMinDate(new Date().getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker_upto_today_plus(Context context, DatePickerDialog.OnDateSetListener date, Calendar calendar)
    {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(Date_Utils.addDays(new Date(),1).getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker_from_selected_date_to_upto_today(Context context, DatePickerDialog.OnDateSetListener date, Calendar calendar)
    {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(new Date().getTime());
        date_Picker_Dialog.getDatePicker().setMinDate(calendar.getTime().getTime());
        date_Picker_Dialog.show();

    }

    public static void show_date_picker_from_selected_date_to_upto_today_plus(Context context, DatePickerDialog.OnDateSetListener date, Calendar calendar)
    {
        DatePickerDialog date_Picker_Dialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        date_Picker_Dialog.getDatePicker().setMaxDate(Date_Utils.addDays(new Date(),1).getTime());
        date_Picker_Dialog.getDatePicker().setMinDate(calendar.getTime().getTime());
        date_Picker_Dialog.show();

    }
}
