package mdse.jtexrer.service.spread;

import java.math.BigDecimal;

public interface SpreadProvider {

    BigDecimal spreadFor(String fromCurrencyCode, String toCurrencyCode, String baseCurrencyCode);
}
