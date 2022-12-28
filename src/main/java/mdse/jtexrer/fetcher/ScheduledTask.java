package mdse.jtexrer.fetcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final CurrencyDataFetcher fetcher;

    @Async
    @Scheduled(cron = "${fetchschedule}", zone = "GMT")
    public void asyncFetch() {
        log.info("Starting scheduled fetch task.");
        fetcher.fetchExchangeData();
    }

}

