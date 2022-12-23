package mdse.jtexrer.model.spread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@Slf4j
@Service
@Profile("zerospread")
public class ZeroSpreadProvider implements SpreadProvider {

    @Override
    public BigDecimal spreadFor(String fromCurrencyCode, String toCurrencyCode, String baseCurrencyCode) {
        double zero = 0;
        log.info("Spread: zero {}% spread!", zero);
        return valueOf(zero);
    }
}
