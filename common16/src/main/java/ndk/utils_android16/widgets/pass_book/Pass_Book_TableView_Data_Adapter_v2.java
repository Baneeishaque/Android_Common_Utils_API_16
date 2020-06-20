package ndk.utils_android16.widgets.pass_book;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;
import ndk.utils_android16.DateUtils;
import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntryV2;

import static android.graphics.Color.BLACK;

public class Pass_Book_TableView_Data_Adapter_v2 extends LongPressAwareTableDataAdapter<PassBookEntryV2> {

    private static final int TEXT_SIZE = 14;

    public Pass_Book_TableView_Data_Adapter_v2(final Context context, final List<PassBookEntryV2> data, final TableView<PassBookEntryV2> tableView) {

        super(context, data, tableView);
    }

    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {

        final PassBookEntryV2 pass_book_entryv2 = getRowData(rowIndex);

        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(DateUtils.normalDateTimeShortYearFormat.format(pass_book_entryv2.getInsertionDate()));
                break;
            case 1:
                renderedView = renderString(pass_book_entryv2.getParticulars());
                break;
            case 2:
                renderedView = renderString(String.valueOf(pass_book_entryv2.getSecondAccountName()));
                break;
            case 3:
                renderedView = renderString(pass_book_entryv2.getCreditAmount() == 0 ? "" : String.valueOf(pass_book_entryv2.getCreditAmount()));
                break;
            case 4:
                renderedView = renderString(pass_book_entryv2.getDebitAmount() == 0 ? "" : String.valueOf(pass_book_entryv2.getDebitAmount()));
                break;
            case 5:
                renderedView = renderString(String.valueOf(pass_book_entryv2.getBalance()));
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
