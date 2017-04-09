package se.webstep.iotr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import se.webstep.iotr.client.DtClient;


@SuppressWarnings("unused")
@Configuration
@EnableAutoConfiguration
@ComponentScan
@Profile("application")
@SpringBootApplication
public class Application {

    public static void main(String... args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        (new DtClient()).start();
    }

}
