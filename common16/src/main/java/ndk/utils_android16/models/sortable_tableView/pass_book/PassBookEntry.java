package ndk.utils_android16.models.sortable_tableView.pass_book;

import java.util.Date;

public class PassBookEntry {

    private Date insertionDate;
    private String particulars;
    private double debitAmount;
    private double creditAmount;
    private double balance;

    public PassBookEntry(Date insertionDate, String particulars, double debitAmount, double creditAmount, double balance) {
        this.insertionDate = insertionDate;
        this.particulars = particulars;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.balance = balance;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    //TODO : Check warning
    @Override
    public String toString() {
        return "PassBookEntry{" +
                "insertionDate=" + insertionDate +
                ", particulars='" + particulars + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                ", balance=" + balance +
                '}';
    }
}
