package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import static runner.BaseUtils.isServerRun;

public class CucumberDriver {

    private static WebDriver driver;

    @Before
    public static void before(Scenario scenario) {
        driver = BaseUtils.createDriver();

        ProjectUtils.get(driver);
        ProjectUtils.login(driver);
    }

    @After
    public static void after(Scenario scenario) {
        if(scenario.isFailed() && isServerRun()) {
            BaseUtils.captureScreenFile(driver, scenario.getName(), "CucumberTest");
        }

        ProjectUtils.logout(driver);
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
