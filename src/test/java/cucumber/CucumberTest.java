package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue = {"cucumber", "runner"},
        plugin = {"pretty"},
        tags = "not @ignore")
public class CucumberTest extends AbstractTestNGCucumberTests {
}

