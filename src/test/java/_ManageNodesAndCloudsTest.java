import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _ManageNodesAndCloudsTest extends BaseTest {

    private WebElement findBuildQueueBtn() {
        return getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='/toggleCollapse?paneId=buildQueue']")));
    }

    private String getTitleValueBuildQueueBtn() {
        return findBuildQueueBtn().getAttribute("title");
    }

    private WebElement findBuildExecutorStatusBtn() {
        return getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='/toggleCollapse?paneId=executors']")));
    }

    private String getTitleValueBuildExecutorStatusBtn() {
        return findBuildExecutorStatusBtn().getAttribute("title");
    }

    @Test
    public void testCheckBuildQueue() {
        if (getDriver().findElements(By.xpath("//div[@id='buildQueue']//table")).size() > 0) {
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
    public void testCheckBuildExecutorStatus() {
        if (getDriver().findElements(By.xpath("//div[@id='executors']//table")).size() > 0) {
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
    public void testCheckManageJenkins() {
        final String expectedResultURL = getDriver().getCurrentUrl();

        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/manage"));

        String headerActualResult = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(headerActualResult, "Manage Jenkins");

        getDriver().navigate().back();
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultURL);
    }
}
