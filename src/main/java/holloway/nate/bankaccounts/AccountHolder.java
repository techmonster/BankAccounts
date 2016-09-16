package holloway.nate.bankaccounts;

/**
 * Created by nathanielholloway on 9/15/16.
 */


public class AccountHolder {

    private String name;

    private static int startingId = 100000 ;
    protected final int id;

    public AccountHolder(String name){
        this.name = name;
        id = startingId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }



}
