package mdse.jtexrer.model.repository;

import mdse.jtexrer.model.exchange.ExchangeRateAsFetched;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FetchedExchangeDataRepository extends JpaRepository<ExchangeRateAsFetched, LocalDate> {


    ExchangeRateAsFetched findFirstByOrderByDateDesc();

    Optional<ExchangeRateAsFetched> findByDate(LocalDate date);
}