package holloway.nate.bankaccounts;

/**
 * Created by nathanielholloway on 9/14/16.
 */
public class Checking extends BankAccount {

    public Checking(Type bankAccount, AccountHolder person){
        super(bankAccount,person);
    }

    @Override
    public String credit(double amount) {
        return super.credit(amount);
    }

    @Override
    public String debit(double amount){
        return super.debit(amount);
    }

    @Override
    public double getBalance() {
        return super.getBalance();
    }

    @Override
    public void setCustomerName(String customerName) {
        super.setCustomerName(customerName);
    }

    @Override
    public String getCustomerName() {
        return super.getCustomerName();
    }

    @Override
    public int getCustomerAccountNumber() {
        return super.getCustomerAccountNumber();
    }


    @Override
    public String getStatus() {
        return super.getStatus();
    }


    public String getOverdraftPreventionStatus() {
        return super.getOverdraftPreventionStatus();
    }

    public void setOverdraftPreventionStatus(OverdraftPrevention overdraftPreventionStatus) {
        super.setOverdraftPreventionStatus(overdraftPreventionStatus);
    }

    @Override
    public double getInterestRate() {
        return super.getInterestRate();
    }

    @Override
    public void setInterestRate(double interestRate) {
        super.setInterestRate(interestRate);
    }



}
