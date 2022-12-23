package mdse.jtexrer.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeEvaluator {

  BigDecimal evaluateExchange(String from, String to, LocalDate date);
}
