package se.webstep.iotr;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@SuppressWarnings({"SpringJavaAutowiringInspection", "unused"})
@Configuration
@Profile(value = "application")
public class ConfigApplication {


}
