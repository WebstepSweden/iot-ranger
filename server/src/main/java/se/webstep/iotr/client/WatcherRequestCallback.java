package se.webstep.iotr.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;
import java.io.IOException;
import static org.springframework.http.HttpHeaders.*;

@SuppressWarnings("WeakerAccess")
class WatcherRequestCallback implements RequestCallback {

    private String apiKey = "6310afd33c5a4c7fac0d30b51a5df1d5";


    public WatcherRequestCallback(String apiKey) {
        this.apiKey = apiKey;
    }


    @Override
    public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
        HttpHeaders h = clientHttpRequest.getHeaders();
        h.set(AUTHORIZATION, String.format("ApiKey %s", apiKey));
        h.set(CACHE_CONTROL, "no-cache");
        h.set(ACCEPT, "text/event-stream");
    }
}
