package se.webstep.iotr.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseExtractor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
class ContinuousResponseExtractor implements ResponseExtractor<JsonNode> {

    private Consumer<JsonNode> consumer;

    private boolean active = true;


    public ContinuousResponseExtractor(Consumer<JsonNode> initialConsumer) {
        consumer = initialConsumer;
    }


    public void stop() {
        this.active = false;
    }


    @Override
    public JsonNode extractData(ClientHttpResponse clientHttpResponse) throws IOException {

//        System.out.println(String.format("%s %s", clientHttpResponse.getStatusCode(), clientHttpResponse.getStatusText()));
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream is = clientHttpResponse.getBody();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while (active) {
            String s = br.readLine();
            if (StringUtils.startsWithIgnoreCase(s, "data:")) {
                s = s.replaceFirst("data: ", "");
            }

            if (!StringUtils.isEmpty(s)) {
                JsonNode node = objectMapper.readValue(s, JsonNode.class);
                consumer.accept(node);
            }
        }

        return null;
    }


}
