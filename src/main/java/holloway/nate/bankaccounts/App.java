package holloway.nate.bankaccounts;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * Created by nathanielholloway on 9/14/16.
 */
public class App {

    public static void main(String[] args) {

        AccountHolder newarkAccountHolder = new AccountHolder("Tony Davis");
        AccountHolder wilmingtonAccountHolder = new AccountHolder("Nate Holloway");

        Checking firstCheckingAccount = new Checking(BankAccount.Type.CHECKING,wilmingtonAccountHolder);
        Investing firstInvestmentAccount = new Investing(BankAccount.Type.INVESTMENT, wilmingtonAccountHolder);

        BankAccount firstBankAccount= new BankAccount(BankAccount.Type.INVESTMENT, newarkAccountHolder);

        firstBankAccount.credit(450000);
        firstBankAccount.debit(100);
        double mybalance =firstBankAccount.checkBalance();
        System.out.println(("Balance is: "+mybalance));
        System.out.println("First Bank account");
        System.out.println(firstBankAccount.getCustomerName());
        firstBankAccount.getTransactions();

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Second Bank account");
        System.out.println(firstInvestmentAccount.getCustomerName());
        System.out.println("Investment account number: "+firstInvestmentAccount.getCustomerAccountNumber());

        firstInvestmentAccount.credit(10000000);
        firstCheckingAccount.credit(250000);

        firstCheckingAccount.setStatus(BankAccount.Status.OFAC_FREEZE);

        System.out.println("Checking account number: "+firstCheckingAccount.getCustomerAccountNumber());

        firstInvestmentAccount.debit(15000);
        firstCheckingAccount.debit(5000);


        firstCheckingAccount.getTransactions();
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Third bank account");
        System.out.println(firstCheckingAccount.getCustomerName());
        firstInvestmentAccount.getTransactions();
        System.out.println("-----------------------------------------------------------------------------------------");







    }


}
