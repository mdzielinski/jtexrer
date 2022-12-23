package mdse.jtexrer.fetching.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface RestClient {

    ResponseEntity<JsonNode> sendGet(String url, String apiKeyName, String apiKeyValue);
}