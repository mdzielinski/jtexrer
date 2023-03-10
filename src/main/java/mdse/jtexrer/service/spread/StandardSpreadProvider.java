package mdse.jtexrer.service.spread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@Slf4j
@Service
@Profile("standardspread")
public class StandardSpreadProvider implements SpreadProvider {

    private static double getSpread(String currencyCode, String baseCurrencyCode) {
        if (currencyCode.equals(baseCurrencyCode)) return 0;
        return switch (currencyCode) {
            case "JPY", "HKD", "KRW" -> 3.25;
            case "MYR", "INR", "MXN" -> 4.50;
            case "RUB", "CNY", "ZAR" -> 6.00;
            default -> 2.75;
        };
    }

    @Override
    public BigDecimal spreadFor(String fromCurrencyCode, String toCurrencyCode, String baseCurrencyCode) {
        double fromSpread = getSpread(fromCurrencyCode, baseCurrencyCode);
        double toSpread = getSpread(toCurrencyCode, baseCurrencyCode);
        double effectiveSpread = Double.max(fromSpread, toSpread);
        log.info("Spread: {}% (max from: [{}:{}%, {}:{}%])",
                effectiveSpread, fromCurrencyCode, fromSpread, toCurrencyCode, toSpread);
        return valueOf(effectiveSpread);
    }
}
