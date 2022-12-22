package mdse.jtexrer.service;

import java.time.LocalDate;

public interface ExchangeEvaluator {

  Double evaluateExchange(String from, String to, LocalDate date);
}
