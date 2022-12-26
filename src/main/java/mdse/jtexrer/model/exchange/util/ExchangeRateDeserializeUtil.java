package mdse.jtexrer.model.exchange.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import mdse.jtexrer.model.exchange.ExchangeRateAsFetched;
import mdse.jtexrer.model.exchange.ExchangeRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

@Slf4j
public class ExchangeRateDeserializeUtil {

    public static ExchangeRateAsFetched deserialize(JsonNode responseBody) {
        var timestamp = parseTimestamp(responseBody);
        var base = parseCurrencyCode(responseBody);
        var date = parseIsoDate(responseBody);
        var exchangeRates = parseRates(responseBody, date);
        return ExchangeRateAsFetched.of(timestamp, base, date, exchangeRates);
    }

    private static long parseTimestamp(JsonNode response) {
        long timestamp = response.get("timestamp").asLong();
        log.debug("Parsed timestamp: {}.", timestamp);
        return timestamp;
    }

    private static String parseCurrencyCode(JsonNode response) {
        String base = response.get("base").asText();
        log.debug("Parsed base: {}.", base);
        return base;
    }

    private static LocalDate parseIsoDate(JsonNode response) {
        LocalDate date = LocalDate.parse(response.get("date").asText(), DateTimeFormatter.ISO_LOCAL_DATE);
        log.debug("Parsed date: {}.", date);
        return date;
    }

    private static List<ExchangeRecord> parseRates(JsonNode responseBody, LocalDate date) {
        JsonNode rawRates = responseBody.get("rates");
        var parsedRates = synchronizedList(new ArrayList<ExchangeRecord>());
        rawRates.fields()
                .forEachRemaining(
                        node -> {
                            var record = new ExchangeRecord();
                            var id = new ExchangeRecord.CompositeId();
                            record.setExchangeRate(node.getValue().doubleValue());
                            id.setCurrencyCode(node.getKey());
                            id.setDate(date);
                            record.setCompositeId(id);
                            parsedRates.add(record);
                        });

        log.debug("Parsed rates: {}.", parsedRates);
        return parsedRates;
    }
}
