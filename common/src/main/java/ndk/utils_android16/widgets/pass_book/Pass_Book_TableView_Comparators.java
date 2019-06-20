package ndk.utils_android16.widgets.pass_book;

import java.util.Comparator;

import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntry;

/**
 * A collection of {@link Comparator}s for {@link PassBookEntry} objects.
 *
 * @author ISchwarz
 */
final class Pass_Book_TableView_Comparators {

    private Pass_Book_TableView_Comparators() {
        //no instance
    }

    static Comparator<PassBookEntry> get_Insertion_Date_Comparator() {
        return new Insertion_Date_Comparator();
    }

    static Comparator<PassBookEntry> get_Particulars_Comparator() {
        return new Particulars_Comparator();
    }

    static Comparator<PassBookEntry> get_Debit_Amount_Comparator() {
        return new Debit_Amount_Comparator();
    }

    static Comparator<PassBookEntry> get_Credit_Amount_Comparator() {
        return new Credit_Amount_Comparator();
    }

    static Comparator<PassBookEntry> get_Balance_Comparator() {
        return new Balance_Comparator();
    }

    private static class Insertion_Date_Comparator implements Comparator<PassBookEntry> {

        @Override
        public int compare(final PassBookEntry pass_book_entry1, final PassBookEntry pass_book_entry2) {
            if (pass_book_entry1.getInsertionDate().before(pass_book_entry2.getInsertionDate()))
                return -1;
            if (pass_book_entry1.getInsertionDate().after(pass_book_entry2.getInsertionDate()))
                return 1;
            return 0;
        }
    }


    private static class Debit_Amount_Comparator implements Comparator<PassBookEntry> {

        @Override
        public int compare(final PassBookEntry pass_book_entry1, final PassBookEntry pass_book_entry2) {
            if (pass_book_entry1.getDebitAmount() < pass_book_entry2.getDebitAmount())
                return -1;
            if (pass_book_entry1.getDebitAmount() > pass_book_entry2.getDebitAmount())
                return 1;
            return 0;
        }
    }

    private static class Credit_Amount_Comparator implements Comparator<PassBookEntry> {

        @Override
        public int compare(final PassBookEntry pass_book_entry1, final PassBookEntry pass_book_entry2) {
            if (pass_book_entry1.getCreditAmount() < pass_book_entry2.getCreditAmount())
                return -1;
            if (pass_book_entry1.getCreditAmount() > pass_book_entry2.getCreditAmount())
                return 1;
            return 0;
        }
    }

    private static class Balance_Comparator implements Comparator<PassBookEntry> {

        @Override
        public int compare(final PassBookEntry pass_book_entry1, final PassBookEntry pass_book_entry2) {
            if (pass_book_entry1.getBalance() < pass_book_entry2.getBalance())
                return -1;
            if (pass_book_entry1.getBalance() > pass_book_entry2.getBalance())
                return 1;
            return 0;
        }
    }

    private static class Particulars_Comparator implements Comparator<PassBookEntry> {

        @Override
        public int compare(final PassBookEntry pass_book_entry1, final PassBookEntry pass_book_entry2) {
            return pass_book_entry1.getParticulars().compareTo(pass_book_entry2.getParticulars());
        }
    }


}
