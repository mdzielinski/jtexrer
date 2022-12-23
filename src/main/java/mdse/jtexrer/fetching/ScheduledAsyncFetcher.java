package mdse.jtexrer.fetching;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import mdse.jtexrer.fetching.client.RestClient;
import mdse.jtexrer.model.exchange.ExchangeRateAsFetched;
import mdse.jtexrer.model.repository.ExchangeBulkDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static mdse.jtexrer.model.exchange.util.ExchangeRateDeserializeUtil.deserialize;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledAsyncFetcher implements DataFetcher {
    private final ExchangeBulkDataRepository exchangeBulkDataRepository;
    private final RestClient client;
    @Value("${exchange_api_url}")
    private String API_URL;
    @Value("${exchange_api_key_name}")
    private String API_KEY_NAME;
    @Value("${exchange_api_key_value}")
    private String API_KEY_VALUE;

    @Async
    @Scheduled(cron = "${schedule}", zone = "GMT")
    public void asyncFetch() {
        log.info("Starting scheduled fetch task.");
        fetchExchangeData();
    }

    @Override
    @Synchronized
    public void fetchExchangeData() {
        save(deserialize(get(API_URL, API_KEY_NAME, API_KEY_VALUE, client)));
    }

    private JsonNode get(String apiUrl, String apiKeyName, String apiKeyValue, RestClient client) {
        return client.sendGet(apiUrl, apiKeyName, apiKeyValue).getBody();
    }

    private void save(ExchangeRateAsFetched data) {
        exchangeBulkDataRepository.save(data);
        log.info("Saved currency exchange data for {}", data.getDate());

    }
}

