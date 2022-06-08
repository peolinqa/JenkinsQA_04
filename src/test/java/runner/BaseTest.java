package runner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        driver = BaseUtils.createDriver();
        ProjectUtils.login(driver);
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {
        if (!result.isSuccess() && BaseUtils.isServerRun()) {
            BaseUtils.captureScreenFile(driver, method.getName(), this.getClass().getName());
        }

        ProjectUtils.logout(driver);
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
