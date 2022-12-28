package mdse.jtexrer.fetcher;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mdse.jtexrer.fetcher.cient.RestClient;
import mdse.jtexrer.model.exchange.ExchangeRateAsFetched;
import mdse.jtexrer.model.repository.FetchedExchangeDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static mdse.jtexrer.util.ExchangeRateDeserializeUtil.deserialize;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyDataFetcher implements DataFetcher {
    private final FetchedExchangeDataRepository fetchedExchangeDataRepository;
    private final RestClient client;

    @Value("${currency_exchange_api_url}")
    private String API_URL;
    @Value("${currency_exchange_api_key_name}")
    private String API_KEY_NAME;
    @Value("${currency_exchange_api_key_value}")
    private String API_KEY_VALUE;

    @Override
    @Transactional
    public void fetchExchangeData() {
        save(deserialize(get(API_URL, API_KEY_NAME, API_KEY_VALUE, client)));
    }

    private JsonNode get(String apiUrl, String apiKeyName, String apiKeyValue, RestClient client) {
        return client.sendGet(apiUrl, apiKeyName, apiKeyValue).getBody();
    }

    private void save(ExchangeRateAsFetched data) {
        fetchedExchangeDataRepository.save(data);
        log.info("Saved currency exchange data for {}", data.getDate());

    }
}

