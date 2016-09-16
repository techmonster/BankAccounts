package holloway.nate.bankaccounts;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nathanielholloway on 9/14/16.
 */
public class BankAccount {
    private static int accountNumber;
    private final int customerAccountNumber;
    private double balance;
    //private String customerName;
    private double interestRate;
   // private int accountHolderId;
    private Date now;

    private String operationCannotBePerformed = "This operation cannot be performed.";
    private String approved = "Approved";
    private String denied = "Denied";
    private String approvalStatus;
    public enum Status{OPEN, CLOSED, OFAC_FREEZE,}
    private Status status;
    public enum OverdraftPrevention{ENABLE, DISABLE, AUTO_ACCOUNT_TRANSFER}
    private OverdraftPrevention overdraftPreventionStatus;
    public enum Type{SAVINGS, CHECKING, INVESTMENT}
    private Type accountType;
    static ArrayList<Integer> frozenAccountHolderIDs = new ArrayList<Integer>();
    AccountHolder accountHolder;


    private Transactions transactions;


    public BankAccount(Type bankAccountType, AccountHolder accountHolder) {
        customerAccountNumber = accountNumber++;
        this.accountType = bankAccountType;
        transactions = new Transactions();
        status = Status.OPEN;
        this.accountHolder = accountHolder;
        overdraftPreventionStatus = OverdraftPrevention.ENABLE;
    }

    public Type getBankAccountType() {
        return accountType;
    }

    public int getCustomerAccountNumber() {
        return customerAccountNumber;
    }

     double getBalance() {
        if( retrieveFrozenIds()){
            this.transactions.logTransaction("Transaction Type: Get account balance\nResult: Denied\nStatus: Frozen");
            return 0;
        }else {
            return balance;
        }
    }

    private String setBalance(double balance) {
        if(this.getStatus().equals("OPEN")){
        this.balance = balance;
        return approved;}else{
            return denied;
        }
    }

    public String getCustomerName() {
        return this.accountHolder.getName();
    }

    public void setCustomerName(String customerName) {
        this.transactions.logTransaction("Transaction Type: Set customer name\nNew name: "+customerName);
        this.accountHolder.setName(customerName);
    }

    public double getInterestRate() {
        this.transactions.logTransaction("Transaction Type: Get interest rate");
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.transactions.logTransaction("Transaction Type: Interest rate changed\nNew Interest rate: "+interestRate);
        this.interestRate = interestRate;
    }

    public void getTransactions() {
         this.transactions.printLog();
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {

        boolean success =false;
        if(getBalance()<0 || status.equals(Status.CLOSED)){
            this.print(operationCannotBePerformed);
        }
        if(status.equals(Status.OFAC_FREEZE)){
            storeFrozenId();
        }
        else {
            success = true;
            this.status = status;
        }
        this.transactions.logTransaction("Transaction Type: Set new Status\nResult: "+success);

    }
    public String getOverdraftPreventionStatus() {
        this.transactions.logTransaction("Transaction Type: Get Over Draft Prevention Status");
        return overdraftPreventionStatus.toString();
    }

    public void setOverdraftPreventionStatus(OverdraftPrevention overdraftPreventionStatus) {
        this.transactions.logTransaction("Transaction Type: Set Over Draft Prevention Status");
        this.overdraftPreventionStatus = overdraftPreventionStatus;
    }

    public void print(String msg){
        System.out.println(msg);
    }

    public void print(int msg){

        System.out.println(msg + "");
    }

    public void print(Double msg){

        System.out.println(msg + "");
    }

    public String debit(double amount){
        if (getBalance() < amount || retrieveFrozenIds()){
                approvalStatus = denied;
        }else {
            approvalStatus = setBalance(getBalance() - amount);
        }
        this.transactions.logTransaction("Transaction Type: Debit:\nAmount: "+amount+" Status: " +approvalStatus);
        return approvalStatus;
    }

    public String credit(double amount){
        if(retrieveFrozenIds()) {
            this.transactions.logTransaction("Transaction Type: credit\nAmount: "+amount+" Status: " +denied);
            return denied;
        }
          else{  approvalStatus = setBalance(getBalance() + amount);
        this.transactions.logTransaction("Transaction Type: credit\nAmount: "+amount+" Status: " +approvalStatus);
        return approvalStatus;}
    }

    public double checkBalance(){
        balance = getBalance();
        if(retrieveFrozenIds()) {
            this.transactions.logTransaction("Transaction Type: Get account balance\nResult: Denied");
            return 0;
        }else{
        return balance;}
    }

    private void storeFrozenId(){
        System.out.println(accountHolder.getId());
        frozenAccountHolderIDs.add(accountHolder.getId());
    }

    private boolean retrieveFrozenIds(){
        for (int i = 0; i < frozenAccountHolderIDs.size(); i++) {
            int id = frozenAccountHolderIDs.get(i);
            System.out.println("Frozen account holder id: "+id);
            System.out.println("This account holder: "+accountHolder.getId());
            if(id == accountHolder.getId()){
                return true;
            }
        }
        return false;

    }
}
