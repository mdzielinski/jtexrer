package mdse.jtexrer.fetching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final CurrencyExchangeFetcher fetcher;

    @Async
    @Scheduled(cron = "${schedule}", zone = "GMT")
    public void asyncFetch() {
        log.info("Starting scheduled fetch task.");
        fetcher.fetchExchangeData();
    }

}

