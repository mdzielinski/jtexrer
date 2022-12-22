package mdse.jtexrer.model.exceptions;

public class IncorrectCurrencyCodeException extends Exception {
    public IncorrectCurrencyCodeException(String currencyCode) {
        super(currencyCode + " was not found in the buffer.");
    }
}
