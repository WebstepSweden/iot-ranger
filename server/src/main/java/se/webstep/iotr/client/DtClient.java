package se.webstep.iotr.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class DtClient {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String API_KEY = "ApiKey d45a7c14c88f48f5937a8fc3254378ad";
    public static final String BASEPATH = "https://api.disruptive-technologies.com/v1/things";
    public static final String SUBSCRIBEPATH = "https://api.disruptive-technologies.com/v1/subscribe";
    private final CloseableHttpClient httpclient;
    private final ObjectMapper objectMapper;

    public DtClient() {

        objectMapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
        ;
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, (chain, authType) -> true);
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());
            httpclient = HttpClients.custom().setSSLSocketFactory(
                    sslsf).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Sensors getSensors() throws IOException {

        HttpGet httpGet = new HttpGet(BASEPATH);
        httpGet.setHeader(AUTHORIZATION_HEADER, API_KEY);

        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            HttpEntity entity1 = response1.getEntity();
            Sensors sensors = objectMapper.readValue(entity1.getContent(), Sensors.class);
            EntityUtils.consume(entity1);
            return sensors;
        }

    }

    public Sensor getSensor(String id) throws IOException {

        HttpGet httpGet = new HttpGet(BASEPATH + "/" + id);
        httpGet.setHeader(AUTHORIZATION_HEADER, API_KEY);

        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            HttpEntity entity1 = response1.getEntity();
            Sensor sensor = objectMapper.readValue(entity1.getContent(), Sensor.class);
            EntityUtils.consume(entity1);
            return sensor;
        }

    }

    public Sensor watch(String id) throws IOException {

        HttpGet httpGet = new HttpGet(SUBSCRIBEPATH + "?thing_ids=" + id);
        httpGet.setHeader(AUTHORIZATION_HEADER, API_KEY);

        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            HttpEntity entity1 = response1.getEntity();
            Sensor sensor = objectMapper.readValue(entity1.getContent(), Sensor.class);
            EntityUtils.consume(entity1);
            return sensor;
        }

    }

}