package features;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 25.
 * Time: 오전 3:19
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        features = "classpath:features",
        format = {"pretty", "json:target/cucumber.json", "html:target/cucumber"}
)
public class CucumberTests {
}
