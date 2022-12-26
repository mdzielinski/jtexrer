package mdse.jtexrer.model.exceptions;

public class LatestDataNotFundException extends Exception {
    public LatestDataNotFundException() {
        super("Buffer found no data for latest date. Probable cause is that buffer fetched no data for any date yet.");
    }
}
