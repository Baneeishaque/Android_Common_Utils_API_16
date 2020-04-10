package ndk.utils_android16.widgets.pass_book;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.core.content.ContextCompat;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.listeners.TableHeaderClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.providers.TableDataRowBackgroundProvider;
import de.codecrafters.tableview.toolkit.EndlessOnScrollListener;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import ndk.utils_android16.R;
import ndk.utils_android16.ToastUtils;
import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntryV2;


/**
 * An extension of the {@link SortableTableView} that handles {@link PassBookEntryV2}s.
 *
 * @author ISchwarz
 */
public class Pass_Book_TableView_v2 extends SortableTableView<PassBookEntryV2> {

    private OnRowLongClickListener rowLongClickListener;

    public Pass_Book_TableView_v2(final Context context) {
        this(context, null);
    }

    public Pass_Book_TableView_v2(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public Pass_Book_TableView_v2(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);

        final SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, "#", "Par.", "Tra.", "Cr.", "De.", "Ba.");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.table_header_text));
        setHeaderAdapter(simpleTableHeaderAdapter);

        final int rowColorEven = ContextCompat.getColor(context, R.color.table_data_row_even);
        final int rowColorOdd = ContextCompat.getColor(context, R.color.table_data_row_odd);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        final TableColumnWeightModel tableColumnWeightModel = new TableColumnWeightModel(6);
        tableColumnWeightModel.setColumnWeight(0, 2);
        tableColumnWeightModel.setColumnWeight(1, 3);
        tableColumnWeightModel.setColumnWeight(2, 3);
        tableColumnWeightModel.setColumnWeight(3, 2);
        tableColumnWeightModel.setColumnWeight(4, 2);
        tableColumnWeightModel.setColumnWeight(5, 2);
        setColumnModel(tableColumnWeightModel);

        setColumnComparator(0, Pass_Book_TableView_Comparators_v2.get_Insertion_Date_Comparator());
        setColumnComparator(1, Pass_Book_TableView_Comparators_v2.get_Particulars_Comparator());
        setColumnComparator(2, Pass_Book_TableView_Comparators_v2.get_Second_Account_Comparator());
        setColumnComparator(3, Pass_Book_TableView_Comparators_v2.get_Credit_Amount_Comparator());
        setColumnComparator(4, Pass_Book_TableView_Comparators_v2.get_Debit_Amount_Comparator());
        setColumnComparator(5, Pass_Book_TableView_Comparators_v2.get_Balance_Comparator());

        setHeaderElevation(10);

//        setHeaderSortStateViewProvider(new SortStateViewProvider() {
//
//            private static final int NO_IMAGE_RES = -1;
//
//            @Override
//            public int getSortStateViewResource(SortState state) {
//                switch (state) {
//                    case SORTABLE:
//                        return R.mipmap.ic_sortable;
//                    case SORTED_ASC:
//                        return R.mipmap.ic_sorted_asc;
//                    case SORTED_DESC:
//                        return R.mipmap.ic_sorted_desc;
//                    default:
//                        return NO_IMAGE_RES;
//                }
//            }
//        });

//        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(ContextCompat.getColor(context, R.color.table_data_row_even), ContextCompat.getColor(context, R.color.table_data_row_odd)));

        setDataRowBackgroundProvider(new TableDataRowBackgroundProvider<PassBookEntryV2>() {
            @Override
            public Drawable getRowBackground(int rowIndex, PassBookEntryV2 rowData) {

                int rowColor;

                if ((rowData.getCreditAmount() >= 100) && (rowData.getCreditAmount() < 500)) {
                    rowColor = Color.WHITE;
                } else if ((rowData.getCreditAmount() >= 500) && (rowData.getCreditAmount() < 1000)) {
                    rowColor = Color.CYAN;
                } else if (rowData.getCreditAmount() >= 1000) {
                    rowColor = Color.GRAY;
                } else if ((rowData.getDebitAmount() >= 100) && (rowData.getDebitAmount() < 500)) {
                    rowColor = Color.MAGENTA;
                } else if ((rowData.getDebitAmount() >= 500) && (rowData.getDebitAmount() < 1000)) {
                    rowColor = Color.YELLOW;
                } else if (rowData.getDebitAmount() >= 1000) {
                    rowColor = Color.GREEN;
                } else if ((rowIndex == 0) || (rowIndex % 2 == 0)) {
                    rowColor = ContextCompat.getColor(context, R.color.table_data_row_even);
                } else {
                    rowColor = ContextCompat.getColor(context, R.color.table_data_row_odd);
                }

                return new ColorDrawable(rowColor);
            }
        });

        setSwipeToRefreshEnabled(true);
        setSwipeToRefreshListener(new SwipeToRefreshListener() {
            @Override
            public void onRefresh(RefreshIndicator refreshIndicator) {
                ToastUtils.longToast(context, "Refresh View...");
            }
        });

//        setHeaderVisible( false );
//        setHeaderVisible( false,100 );

//        setHeaderVisible( true );
//        setHeaderVisible( true,100 );

        addDataClickListener(new TableDataClickListener<PassBookEntryV2>() {
            @Override
            public void onDataClicked(int rowIndex, PassBookEntryV2 clickedData) {

                Log.d("Clicked On : ", clickedData.toString());
                ToastUtils.longToast(context, clickedData.toString());

            }
        });

        addDataLongClickListener(new TableDataLongClickListener<PassBookEntryV2>() {
            @Override
            public boolean onDataLongClicked(int rowIndex, PassBookEntryV2 clickedData) {

                Log.d("Clicked On : ", clickedData.toString());
                rowLongClickListener.onRowLongClick(clickedData);
                return true;
            }
        });

        addHeaderClickListener(new TableHeaderClickListener() {
            @Override
            public void onHeaderClicked(int columnIndex) {
                ToastUtils.longToast(context, "Column : " + columnIndex);
            }
        });

        addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onReloadingTriggered(int firstRowItem, int visibleRowCount, int totalRowCount) {
                ToastUtils.longToast(context, "Endless");
            }
        });
    }

    public void SetOnRowLongClickListener(final OnRowLongClickListener rowLongClickListener) {
        this.rowLongClickListener = rowLongClickListener;
    }

    public interface OnRowLongClickListener {
        void onRowLongClick(PassBookEntryV2 clickedData);
    }

}
