package exception;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(double currentValue, double valueRequested) {
        super(String.format(
                "Insufficient funds. The value requested was $%s, but the amount requested was $%s",
                currentValue, valueRequested));
    }
}
