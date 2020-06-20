package ndk.utils_android16.widgets.pass_book;

import java.util.Comparator;

import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntryV2;

/**
 * A collection of {@link Comparator}s for {@link PassBookEntryV2} objects.
 *
 * @author ISchwarz
 */
final class Pass_Book_TableView_Comparators_v2 {

    private Pass_Book_TableView_Comparators_v2() {
        //no instance
    }

    static Comparator<PassBookEntryV2> get_Insertion_Date_Comparator() {

        return new Insertion_Date_Comparator();
    }

    static Comparator<PassBookEntryV2> get_Particulars_Comparator() {

        return new Particulars_Comparator();
    }

    static Comparator<PassBookEntryV2> get_Second_Account_Comparator() {

        return new Second_Account_Comparator();
    }

    static Comparator<PassBookEntryV2> get_Credit_Amount_Comparator() {

        return new Credit_Amount_Comparator();
    }

    static Comparator<PassBookEntryV2> get_Debit_Amount_Comparator() {

        return new Debit_Amount_Comparator();
    }

    static Comparator<PassBookEntryV2> get_Balance_Comparator() {
        return new Balance_Comparator();
    }

    private static class Insertion_Date_Comparator implements Comparator<PassBookEntryV2> {

        @Override
        public int compare(final PassBookEntryV2 pass_book_entry1, final PassBookEntryV2 pass_book_entry2) {

            return pass_book_entry1.getInsertionDate().compareTo(pass_book_entry2.getInsertionDate());
        }
    }


    private static class Second_Account_Comparator implements Comparator<PassBookEntryV2> {

        @Override
        public int compare(final PassBookEntryV2 pass_book_entry1, final PassBookEntryV2 pass_book_entry2) {

            return pass_book_entry1.getSecondAccountName().compareTo(pass_book_entry2.getSecondAccountName());
        }
    }

    private static class Credit_Amount_Comparator implements Comparator<PassBookEntryV2> {

        @Override
        public int compare(final PassBookEntryV2 pass_book_entry1, final PassBookEntryV2 pass_book_entry2) {

            return Double.compare(pass_book_entry1.getCreditAmount(), pass_book_entry2.getCreditAmount());
        }
    }

    private static class Debit_Amount_Comparator implements Comparator<PassBookEntryV2> {

        @Override
        public int compare(final PassBookEntryV2 pass_book_entry1, final PassBookEntryV2 pass_book_entry2) {

            return Double.compare(pass_book_entry1.getDebitAmount(), pass_book_entry2.getDebitAmount());
        }
    }

    private static class Balance_Comparator implements Comparator<PassBookEntryV2> {

        @Override
        public int compare(final PassBookEntryV2 pass_book_entry1, final PassBookEntryV2 pass_book_entry2) {

            return Double.compare(pass_book_entry1.getBalance(), pass_book_entry2.getBalance());
        }
    }

    private static class Particulars_Comparator implements Comparator<PassBookEntryV2> {

        @Override
        public int compare(final PassBookEntryV2 pass_book_entry1, final PassBookEntryV2 pass_book_entry2) {

            return pass_book_entry1.getParticulars().compareTo(pass_book_entry2.getParticulars());
        }
    }
}
