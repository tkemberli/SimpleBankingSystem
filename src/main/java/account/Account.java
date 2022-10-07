package account;

import exception.InsufficientFundsException;

import java.time.Instant;
import java.util.Map;

public interface Account {
    public void deposit(double value);
    public void withdraw(double value) throws InsufficientFundsException;
    public Map<Instant, String[] > getStatement();
}
