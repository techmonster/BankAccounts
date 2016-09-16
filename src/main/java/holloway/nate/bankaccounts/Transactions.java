package holloway.nate.bankaccounts;

import java.util.ArrayList;

/**
 * Created by nathanielholloway on 9/14/16.
 * This class will keep track of the transactions made by the bank accounts.
 */
public class Transactions {
    private static boolean isTrue = false;
    protected ArrayList<String> log;

    public Transactions(){
        log = new ArrayList<String>();
    }

    protected boolean logTransaction(String transaction){
        isTrue = log.add(transaction);
        return isTrue;
    }

    protected void printLog(){
        for (String logFile: log) {
            System.out.println(logFile);
            System.out.println("");
        }
    }
}
