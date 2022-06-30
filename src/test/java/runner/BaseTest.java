package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import runner.order.FilterForTests;
import runner.order.OrderForTests;
import runner.order.OrderUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Listeners({FilterForTests.class, OrderForTests.class})
public abstract class BaseTest {

    private WebDriver driver;

    private WebDriverWait wait20;
    private WebDriverWait wait5;
    private Actions actions;

    private List<List<Method>> methodList;

    @BeforeClass
    protected void beforeClass() {
        methodList = OrderUtils.orderMethods(
                Arrays.stream(this.getClass().getMethods())
                        .filter(m -> m.getAnnotation(Test.class) != null && m.getAnnotation(Ignore.class) == null)
                        .collect(Collectors.toList()),
                m -> m.getName(),
                m -> m.getAnnotation(Test.class).dependsOnMethods());
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        BaseUtils.logf("Run %s.%s", this.getClass().getName(), method.getName());
        try {
            if (method.getAnnotation(Test.class).dependsOnMethods().length == 0) {
                BaseUtils.log("Browser open, get web page and login");
                startDriver();
                loginWeb();
            } else {
                BaseUtils.log("Get web page");
                getWeb();
            }
        } catch (Exception e) {
            stopDriver();
            throw e;
        }
    }

    protected void loginWeb() {
        ProjectUtils.login(driver);
    }

    protected void getWeb() {
        ProjectUtils.get(getDriver());
    }

    protected void startDriver() {
        driver = BaseUtils.createDriver();
    }

    protected void stopDriver() {
        ProjectUtils.logout(driver);

        driver.quit();
        wait20 = null;
        wait5 = null;
        actions = null;

        BaseUtils.log("Browser closed");
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) throws IOException {
        if (!testResult.isSuccess() && BaseUtils.isServerRun()) {
            BaseUtils.captureScreenFile(driver, method.getName(), this.getClass().getName());
        }

        List<Method> list = OrderUtils.find(methodList, method).orElse(null);
        if (!testResult.isSuccess() || list == null || (list.remove(method) && list.isEmpty())) {
            stopDriver();
        }

        BaseUtils.logf("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected Actions getActions() {
        if(actions == null) {
            actions = new Actions(getDriver());
        }
        return actions;
    }

    protected WebDriverWait getWait20() {
        if (wait20 == null) {
            wait20 = new WebDriverWait(getDriver(), 20);
        }

        return wait20;
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), 5);
        }

        return wait5;
    }
}
