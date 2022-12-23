package mdse.jtexrer.model.repository;

import mdse.jtexrer.model.exchange.ExchangeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRecordRepository extends JpaRepository<ExchangeRecord, Long> {

    List<ExchangeRecord> findByCompositeIdIn(List<ExchangeRecord.CompositeId> ids);

    @Modifying
    @Query(value = "update exchange_record set fetch_counter=? where currency_code=? and date=?", nativeQuery = true)
    void updateCounter(int fetchCounter, String currencyCode, LocalDate date);
}