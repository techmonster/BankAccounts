package holloway.nate.bankaccounts;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nathanielholloway on 9/14/16.
 */
public class BankAccountTest {
    AccountHolder newarkAccountHolder = new AccountHolder("Tony Davis");
    AccountHolder wilmingtonAccountHolder = new AccountHolder("Nate Holloway");

    BankAccount firstBankAccount= new BankAccount(BankAccount.Type.INVESTMENT, newarkAccountHolder);
    Checking firstCheckingAccount = new Checking(BankAccount.Type.CHECKING,wilmingtonAccountHolder);
    Investing firstInvestmentAccount = new Investing(BankAccount.Type.INVESTMENT, wilmingtonAccountHolder);



    @Test
    public void getBankAccountTypeTest(){
        BankAccount.Type accountType = firstBankAccount.getBankAccountType();
        Assert.assertSame(accountType,firstBankAccount.getBankAccountType());
    }
    @Test
    public void getBankAccountNumberTest(){
        int customerAccountNumber = firstBankAccount.getCustomerAccountNumber();
        Assert.assertEquals(customerAccountNumber,firstBankAccount.getCustomerAccountNumber());
    }

    @Test
    public void getBalanceTest(){
        firstBankAccount.credit(12345678.98);
        double customerAccountBalance = firstBankAccount.getBalance();
        Assert.assertTrue(customerAccountBalance==firstBankAccount.getBalance());
    }

    @Test
    public void getBalanceTestOnFrozenAccount(){
        firstCheckingAccount.credit(12345678.98);
        firstCheckingAccount.setStatus(BankAccount.Status.OFAC_FREEZE);
        double customerAccountBalance = firstCheckingAccount.getBalance();
        Assert.assertTrue(customerAccountBalance==0);
    }

    @Test
    public void getCustomerName(){
        firstBankAccount.setCustomerName("Nate Holloway");
        String customerName = "Nate Holloway";
        Assert.assertEquals(customerName, firstBankAccount.getCustomerName());
    }

    @Test
    public void getStatusTest(){
        firstBankAccount.setStatus(BankAccount.Status.CLOSED);
        String currentAccountStatus = firstBankAccount.getStatus();
        Assert.assertEquals(currentAccountStatus, firstBankAccount.getStatus());
    }


    @Test
    public void getOverDraftPreventionStatusTest(){
        firstBankAccount.setOverdraftPreventionStatus(BankAccount.OverdraftPrevention.DISABLE);
        Assert.assertEquals(BankAccount.OverdraftPrevention.DISABLE.toString(),firstBankAccount.getOverdraftPreventionStatus());
    }

    @Test
    public void creditTestStatusOPEN(){
        String approvalStatus;
        approvalStatus = firstBankAccount.credit(12345678.98);
        Assert.assertEquals(approvalStatus,"Approved");
    }

    @Test
    public void creditTestStatusOFAC_FREEZE(){
        firstBankAccount.setStatus(BankAccount.Status.OFAC_FREEZE);
        String approvalStatus;
        approvalStatus = firstBankAccount.credit(12345678.98);
        Assert.assertEquals("Denied", approvalStatus);
    }


}
