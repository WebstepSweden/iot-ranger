package se.webstep.iotr;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import se.webstep.iotr.client.Watcher;
import javax.inject.Inject;

@SuppressWarnings({"SpringJavaAutowiringInspection", "unused"})
@Configuration
@Profile(value = "application")
public class ConfigApplication {

    @Inject
    private Watcher watcher;


    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        watcher.subscribeAll();
    }

}
