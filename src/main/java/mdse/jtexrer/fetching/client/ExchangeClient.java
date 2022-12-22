package mdse.jtexrer.fetching.client;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
@Profile("default")
@AllArgsConstructor
public class ExchangeClient implements RestClient {

    @Override
    public ResponseEntity<JsonNode> sendGet(String url, String apiKeyName, String apiKeyValue) {
        HttpEntity<Object> requestEntity = getRequestWithHeader(apiKeyName, apiKeyValue);
        RestTemplate restTemplate = new RestTemplate();

        try {
            log.info("Attempting to send GET:{} with {}:{}", url, apiKeyName, getMasked(apiKeyValue));
            var response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, JsonNode.class);
            log.info("Attempt finished with result {}", response.getStatusCode());
            log.debug("Response body: {}", requireNonNull(response.getBody()));
            return response;
        } catch (RestClientException e) {
            log.error("Failed to obtain data form {}. Client received message: {}", url, e.getMessage());
            //todo handling when e was thrown is needed.
            throw e;
        }
    }

    private static HttpEntity<Object> getRequestWithHeader(String apiKeyName, String apiKeyValue) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.add(apiKeyName, apiKeyValue);
        return new HttpEntity<>(httpHeaders);
    }

    private static String getMasked(String string) {
        return string.substring(string.length() / 3).concat("*");
    }
}
