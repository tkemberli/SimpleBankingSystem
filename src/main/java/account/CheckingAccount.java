package account;

import controller.AccountController;
import exception.InsufficientFundsException;
import lombok.val;

import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;

public class CheckingAccount implements Account{
    private final String code;
    private double funds;
    private final TreeMap<Instant, String[]> transactionHistory;

    public CheckingAccount(String code, double funds){
        this.code = code;
        this.funds = funds;
        this.transactionHistory = new TreeMap<>();
        addTransaction(String.valueOf(funds));

    }

    public CheckingAccount(String code){
            this.code = code;
            this.funds = 0;
            this.transactionHistory = new TreeMap<>();

    }

    @Override
    public void deposit(double value) {
        funds += value;
        addTransaction("+ " + value);
    }

    @Override
    public void withdraw(double value) throws InsufficientFundsException {
        assertFundsAreEnough(value);

        funds -= value;
        addTransaction("-" + value);
    }

    @Override
    public Map<Instant, String[]> getStatement() {
        return transactionHistory;
    }


    private void addTransaction(String amount){
        val date = java.time.Clock.systemUTC().instant();
        String[] transaction = {amount, String.valueOf(funds)};

        transactionHistory.put(date, transaction);
    }

    private void assertFundsAreEnough(double value) throws InsufficientFundsException{
        if(funds < value) {
            throw new InsufficientFundsException(funds, value);
        }
    }
}
