import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by frunn on 08/04/2017.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/feature/ranger.feature"
)

public class TestRunner {
}
