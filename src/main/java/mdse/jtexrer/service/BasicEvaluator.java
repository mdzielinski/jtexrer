package mdse.jtexrer.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import mdse.jtexrer.model.exceptions.IncorrectCurrencyCodeException;
import mdse.jtexrer.model.exceptions.IncorrectDateException;
import mdse.jtexrer.model.exchange.ExchangeRateAsFetched;
import mdse.jtexrer.model.exchange.ExchangeRecord;
import mdse.jtexrer.model.repository.ExchangeBulkDataRepository;
import mdse.jtexrer.model.repository.ExchangeRecordRepository;
import mdse.jtexrer.model.spread.SpreadProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static mdse.jtexrer.model.exchange.ExchangeRecord.CompositeId.compositeIdOf;

@Slf4j
@Service
@AllArgsConstructor
public class BasicEvaluator implements ExchangeEvaluator {

    private SpreadProvider spreadProvider;
    private ExchangeBulkDataRepository exchangeBulkDataRepository;
    private ExchangeRecordRepository recordRepository;

    @Override
    @Synchronized
    public BigDecimal evaluateExchange(String fromCode, String toCode, LocalDate date) {
        var metadata = getForAppropriateDate(date);
        log.info("Retrieving buffered exchange data from {} to {} with base currency {} for date {}",
                fromCode, toCode, metadata.getBaseCurrencyCode(), metadata.getDate());

        var rates = getBufferedRatesAndIterateReads(fromCode, toCode, metadata.getDate());

        var fromRate = getRate(fromCode, rates);
        var toRate = getRate(toCode, rates);
        log.info("Retrieved buffered rates: from:{}={}, to:{}={}]", fromCode, fromRate, toCode, toRate);
        var spread = spreadProvider.spreadFor(fromCode, toCode, metadata.getBaseCurrencyCode());
        return calculateExchange(fromRate, toRate, spread);
    }

    private BigDecimal calculateExchange(BigDecimal fromRate, BigDecimal toRate, BigDecimal spread) {
        var rateProportion = toRate.divide(fromRate, HALF_EVEN);
        var spreadCorrection = BigDecimal.valueOf(100).subtract(spread).divide(BigDecimal.valueOf(100), HALF_EVEN);
        return rateProportion.multiply(spreadCorrection);
    }

    private ExchangeRateAsFetched getForAppropriateDate(LocalDate date) {
        var rates = isNull(date) ? getForLastDate() : getForDate(date);
        return rates;
    }

    private List<ExchangeRecord> getBufferedRatesAndIterateReads(String codeFrom, String codeTo, LocalDate date) {
        var fromId = compositeIdOf(codeFrom, date);
        var toId = compositeIdOf(codeTo, date);
        var records = recordRepository.findByCompositeIdIn(List.of(fromId, toId));

        var iteratedRecords = records
                .stream()
                .map(ExchangeRecord::newWithIteratedReads)
                .collect(toList());

        iteratedRecords.forEach(r -> recordRepository.updateCounter(r.getFetchCounter(), r.getCode(), date));
        return iteratedRecords;
    }

    @SneakyThrows
    private static BigDecimal getRate(String code, List<ExchangeRecord> records) {
        return records
                .stream()
                .filter(o -> code.equals(o.getCode()))
                .findFirst().orElseThrow(() -> new IncorrectCurrencyCodeException(code))
                .getRate();
    }

    @SneakyThrows
    private ExchangeRateAsFetched getForDate(LocalDate date) {
        var firstByOrderByDate = exchangeBulkDataRepository
                .findByDate(date)
                .orElseThrow(() -> new IncorrectDateException(date));
        log.info("Obtained exchange data for date: {}", firstByOrderByDate.getDate());
        return firstByOrderByDate;
    }

    private ExchangeRateAsFetched getForLastDate() {
        var firstByOrderByDate = exchangeBulkDataRepository.findFirstByOrderByDateDesc();
        log.info("Obtained exchange data for latest date: {}", firstByOrderByDate.getDate());
        return firstByOrderByDate;
    }
}
