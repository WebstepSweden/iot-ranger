package se.webstep.iotr.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.webstep.iotr.database.Database;
import se.webstep.iotr.database.Sensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DtClient extends Thread {

    private final Logger logger = LoggerFactory.getLogger(DtClient.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String API_KEY = "ApiKey d45a7c14c88f48f5937a8fc3254378ad";
    public static final String BASEPATH = "https://api.disruptive-technologies.com/v1/things";
    public static final String SUBSCRIBEPATH = "https://api.disruptive-technologies.com/v1/subscribe";
    private final CloseableHttpClient httpclient;
    private final ObjectMapper objectMapper;
    private boolean stop;

    public DtClient() {
        stop = false;
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

    public void run() {
        Sensor sensor = null;
        while (!stop) {
            //logger.info("Hello from a DtClient!");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                //logger.info("God Morgon!");
            }
            try {
                Database.instance().addSensorState(getSensor());
            } catch (IOException e) {

            }
        }
    }

    public Sensor getSensor() throws IOException {

        HttpGet httpGet = new HttpGet(BASEPATH + "/206881543");
        httpGet.setHeader(AUTHORIZATION_HEADER, API_KEY);

        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            HttpEntity entity1 = response1.getEntity();
            Sensor sensor = objectMapper.readValue(entity1.getContent(), Sensor.class);
            logger.info("Pollad data: [" + sensor.toString() + "]");
            EntityUtils.consume(entity1);
            return sensor;
        }

    }

    public Sensor watch() throws IOException {

        HttpGet httpGet = new HttpGet(SUBSCRIBEPATH + "?thing_ids=206881543");
        httpGet.setHeader(AUTHORIZATION_HEADER, API_KEY);

        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            logger.info("Watch out!!!!");
            HttpEntity entity1 = response1.getEntity();
            InputStream inputStreamObject = entity1.getContent();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONPObject jsonObject = new JSONPObject(responseStrBuilder.toString(), Sensor.class);

            Sensor sensor = objectMapper.readValue(entity1.getContent(), Sensor.class);
            EntityUtils.consume(entity1);
            return sensor;
        }

    }

}