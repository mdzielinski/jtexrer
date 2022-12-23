package mdse.jtexrer.model.exchange;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Table(name = "exchange_data")
public class ExchangeRateAsFetched {

    @Id
    private LocalDate date;
    private long timestamp;
    @Pattern(regexp = "^[a-zA-Z]{3}$")
    private String baseCurrencyCode;

    @ElementCollection
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<ExchangeRecord> exchangeRecords;


    public static ExchangeRateAsFetched of(long timestamp, String base, LocalDate date, List<ExchangeRecord> exchangeRates) {
        ExchangeRateAsFetched exchangeRateAsFetched = new ExchangeRateAsFetched();
        exchangeRateAsFetched.timestamp = timestamp;
        exchangeRateAsFetched.baseCurrencyCode = base;
        exchangeRateAsFetched.date = date;
        exchangeRateAsFetched.exchangeRecords = exchangeRates;
        return exchangeRateAsFetched;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date, timestamp, baseCurrencyCode, exchangeRecords);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeRateAsFetched that)) return false;
        return timestamp == that.timestamp && Objects.equal(date, that.date) && Objects.equal(baseCurrencyCode, that.baseCurrencyCode) && Objects.equal(exchangeRecords, that.exchangeRecords);
    }

    @Override
    public String toString() {
        return "ExchangeRateMetaData{" +
                "date=" + date +
                ", timestamp=" + timestamp +
                ", baseCurrency='" + baseCurrencyCode + '\'' +
                ", exchangeRecords=" + exchangeRecords +
                '}';
    }
}
