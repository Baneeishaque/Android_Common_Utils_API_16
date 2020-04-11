package ndk.utils_android16.models.sortable_tableView.pass_book;

import java.util.Date;

public class PassBookEntryV2 {

    private Date insertionDate;
    private String particulars;
    private String firstAccountName;
    private String secondAccountName;
    private double creditAmount;
    private double debitAmount;
    private double balance;
    private int fromAccountId;
    private int toAccountId;
    private int id;
    private String fromAccountFullName;
    private String toAccountFullName;

    public PassBookEntryV2(Date insertionDate, String particulars, String firstAccountName, String secondAccountName, double creditAmount, double debitAmount, double balance, int fromAccountId, int toAccountId, int id, String fromAccountFullName, String toAccountFullName) {
        this.insertionDate = insertionDate;
        this.particulars = particulars;
        this.firstAccountName = firstAccountName;
        this.secondAccountName = secondAccountName;
        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
        this.balance = balance;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.id = id;
        this.fromAccountFullName = fromAccountFullName;
        this.toAccountFullName = toAccountFullName;
    }

    public String getFirstAccountName() {
        return firstAccountName;
    }

    public void setFirstAccountName(String firstAccountName) {
        this.firstAccountName = firstAccountName;
    }

    public String getSecondAccountName() {
        return secondAccountName;
    }

    public void setSecondAccountName(String secondAccountName) {
        this.secondAccountName = secondAccountName;
    }

    public String getFromAccountFullName() {
        return fromAccountFullName;
    }

    public void setFromAccountFullName(String fromAccountFullName) {
        this.fromAccountFullName = fromAccountFullName;
    }

    public String getToAccountFullName() {
        return toAccountFullName;
    }

    public void setToAccountFullName(String toAccountFullName) {
        this.toAccountFullName = toAccountFullName;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
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

    //TODO : Check warning
    @Override
    public String toString() {
        return "PassBookEntryV2{" +
                "insertionDate=" + insertionDate +
                ", particulars='" + particulars + '\'' +
                ", firstAccountName='" + firstAccountName + '\'' +
                ", secondAccountName='" + secondAccountName + '\'' +
                ", creditAmount=" + creditAmount +
                ", debitAmount=" + debitAmount +
                ", balance=" + balance +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", id=" + id +
                ", fromAccountFullName='" + fromAccountFullName + '\'' +
                ", toAccountFullName='" + toAccountFullName + '\'' +
                '}';
    }
}
