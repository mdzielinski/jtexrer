package mdse.jtexrer.model.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
public class ExchangeResponseBody {
    private String from;
    private String to;
    private BigDecimal rate;
}
