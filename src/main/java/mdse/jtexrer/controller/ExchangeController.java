package mdse.jtexrer.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import mdse.jtexrer.fetching.DataFetcher;
import mdse.jtexrer.model.exchange.ExchangeResponseBody;
import mdse.jtexrer.service.ExchangeEvaluator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@AllArgsConstructor
@Validated
@Slf4j
public class ExchangeController {

    private ExchangeEvaluator calculator;
    private DataFetcher dataFetcher;

    @Synchronized
    @Transactional
    @GetMapping("/exchange/")
    public ExchangeResponseBody getExchangeRate(@RequestParam @Pattern(regexp = "^[a-zA-Z]{3}$") String from,
                                                @RequestParam @Pattern(regexp = "^[a-zA-Z]{3}$") String to,
                                                @RequestParam(required = false) LocalDate date) {
        log.debug("Requested exchange from {} to {}, date: {}", from, to, date);
        return new ExchangeResponseBody(from, to, calculator.evaluateExchange(from, to, date));
    }

    @Synchronized
    @Transactional
    @PostMapping("/exchange/")
    public void fetch() {
        log.debug("Fetch exchange data triggered by request.");
        dataFetcher.fetchExchangeData();
    }
}
