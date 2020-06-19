package ndk.utils_android16.widgets.pass_book;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;
import ndk.utils_android16.DateUtils;
import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntry;

import static android.graphics.Color.BLACK;

public class Pass_Book_TableView_Data_Adapter extends LongPressAwareTableDataAdapter<PassBookEntry> {

    private static final int TEXT_SIZE = 14;

    public Pass_Book_TableView_Data_Adapter(final Context context, final List<PassBookEntry> data, final TableView<PassBookEntry> tableView) {
        super(context, data, tableView);
    }

    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final PassBookEntry pass_book_entry = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(DateUtils.normalDateTimeShortYearFormat.format(pass_book_entry.getInsertionDate()));
                break;
            case 1:
                renderedView = renderString(pass_book_entry.getParticulars());
                break;
            case 2:
                renderedView = renderString(String.valueOf(pass_book_entry.getDebitAmount()));
                break;
            case 3:
                renderedView = renderString(String.valueOf(pass_book_entry.getCreditAmount()));
                break;
            case 4:
                renderedView = renderString(String.valueOf(pass_book_entry.getBalance()));
                break;
        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        return getDefaultCellView(rowIndex, columnIndex, parentView);
    }

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setTextColor(BLACK);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

}
