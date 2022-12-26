package mdse.jtexrer.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import mdse.jtexrer.model.exceptions.IncorrectCurrencyCodeException;
import mdse.jtexrer.model.exceptions.IncorrectDateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.google.common.base.Throwables.getRootCause;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IncorrectDateException.class, IncorrectCurrencyCodeException.class, ConstraintViolationException.class})
    public ResponseEntity<String> handleIncorrectParameters(Exception e) {
        log.info("Handled exception {}.", e.getClass());
        log.error("Bad request parameters, responding with {}.", NOT_FOUND, e.getCause());
        return new ResponseEntity<>("Incorrect request parameters: " + getRootCause(e).getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleYourCustomException(RuntimeException e) {
        log.info("Handled exception {}.", e.getClass());
        log.error("Bad request parameters, responding with {}.", NOT_FOUND, e.getCause());
        return new ResponseEntity<>("Data not found: " + getRootCause(e).getMessage(), NOT_FOUND);
    }
}
