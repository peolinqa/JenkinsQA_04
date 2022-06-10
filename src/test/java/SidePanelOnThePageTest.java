import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SidePanelOnThePageTest extends BaseTest {
    private static final By XPATH_BUILD_QUEUE = By.xpath("//a[@href='/toggleCollapse?paneId=buildQueue']");
    private static final By XPATH_BUILD_EXECUTOR_STATUS = By.xpath("//a[@href='/toggleCollapse?paneId=executors']");

    @Test
    public void testCheckBuildQueue115001() {
        if (getDriver().findElements(By
                .xpath("//td[text()='No builds in the queue.']")).size() > 0) {
            Assert.assertEquals(getTitleValueBuildQueueBtn(), "collapse");

            findBuildQueueBtn().click();
            Assert.assertEquals(getTitleValueBuildQueueBtn(), "expand");
        } else {

            Assert.assertEquals(getTitleValueBuildQueueBtn(), "expand");

            findBuildQueueBtn().click();
            Assert.assertEquals(getTitleValueBuildQueueBtn(), "collapse");
        }
    }

    @Test
    public void testCheckBuildExecutorStatus115002() {
        if (getDriver().findElements(By
                .xpath("//td[contains(text(), 'Idle')]")).size() > 0) {
            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "collapse");

            findBuildExecutorStatusBtn().click();
            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "expand");
        } else {

            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "expand");

            findBuildExecutorStatusBtn().click();
            Assert.assertEquals(getTitleValueBuildExecutorStatusBtn(), "collapse");
        }
    }

    @Test
    public void testCheckManageJenkins115003() {
        String expectedResultURL = getDriver().getCurrentUrl();

        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/manage"));

        String headerActualResult = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(headerActualResult, "Manage Jenkins");

        getDriver().navigate().back();
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultURL);
    }

    private WebElement findBuildQueueBtn() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_BUILD_QUEUE));
        return getDriver()
                .findElement(XPATH_BUILD_QUEUE);
    }

    private String getTitleValueBuildQueueBtn() {
        return findBuildQueueBtn().getAttribute("title");
    }

    private WebElement findBuildExecutorStatusBtn() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_BUILD_EXECUTOR_STATUS));
        return getDriver()
                .findElement(XPATH_BUILD_EXECUTOR_STATUS);
    }

    private String getTitleValueBuildExecutorStatusBtn() {
        return findBuildExecutorStatusBtn().getAttribute("title");
    }
}
