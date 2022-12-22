package mdse.jtexrer.model.exceptions;

import java.time.LocalDate;

public class IncorrectDateException extends Exception {
    public IncorrectDateException(LocalDate date) {
        super(date + " was not found in the buffer.");
    }
}
