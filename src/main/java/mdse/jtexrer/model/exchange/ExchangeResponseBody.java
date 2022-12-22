package mdse.jtexrer.model.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ExchangeResponseBody {
    private String from;
    private String to;
    private Double rate;
}
