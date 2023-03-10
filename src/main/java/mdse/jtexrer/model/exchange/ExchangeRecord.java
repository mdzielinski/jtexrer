package mdse.jtexrer.model.exchange;


import com.google.common.base.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ExchangeRecord implements Serializable {
    @EmbeddedId
    private CompositeId compositeId = new CompositeId();
    private Double exchangeRate;
    @Column(name = "fetch_counter", updatable = false, columnDefinition = "int default 0")
    private Integer fetchCounter = 0;
    public ExchangeRecord(LocalDate date, String currencyCode, Double exchangeRate) {
        this.compositeId.date = date;
        this.compositeId.currencyCode = currencyCode;
        this.exchangeRate = exchangeRate;
    }

    public static ExchangeRecord newWithIteratedReads(ExchangeRecord record) {
        var iterated = new ExchangeRecord();
        iterated.setFetchCounter(record.getFetchCounter() + 1);
        iterated.setCompositeId(record.getCompositeId());
        iterated.setExchangeRate(record.getExchangeRate());
        return iterated;
    }

    public String getCode() {
        return this.compositeId.getCurrencyCode();
    }

    public BigDecimal getRate() {
        return BigDecimal.valueOf(this.exchangeRate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(compositeId, exchangeRate, fetchCounter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeRecord that)) return false;
        return Objects.equal(compositeId, that.compositeId) && Objects.equal(exchangeRate, that.exchangeRate) && Objects.equal(fetchCounter, that.fetchCounter);
    }

    @Data
    @Embeddable
    @AllArgsConstructor
    public static class CompositeId implements Serializable {

        @Column(name = "date")
        protected LocalDate date;

        @Column(name = "currency_code")
        protected String currencyCode;

        public CompositeId() {
        }

        public static CompositeId compositeIdOf(String currencyCode, LocalDate date) {
            return new CompositeId(date, currencyCode);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(date, currencyCode);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeId that)) return false;
            return Objects.equal(date, that.date) && Objects.equal(currencyCode, that.currencyCode);
        }
    }
}
