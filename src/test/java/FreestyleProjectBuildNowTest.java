import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class FreestyleProjectBuildNowTest extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(3, 10);
    private static final String[] PAGE_NAMES = {"Changes", "Workspace", "Rename"};

    private boolean isSuccessfulBuildDisplayed() {
        return getDriver().findElement(
                By.xpath("//td[@class='build-row-cell']//span/span/*[name()='svg' and (contains(@tooltip,'Success'))]")).
                isDisplayed();
    }

    private boolean isBuildOnTopOfBuildHistory() {
        List<WebElement> finishedBuilds = getWait20()
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.cssSelector("td.build-row-cell a.display-name"), 3));

        return finishedBuilds.get(2).getText().equals("#1") && finishedBuilds.get(0).getText().equals("#3");
    }

    private void clickLinkInPipelinePage(String pageName) {
        getDriver().findElement(By.linkText(pageName)).click();
    }

    @Test(priority = 1)
    public void testBuildNow_TC_006_001() {
        getDriver().findElement(By.className("breadcrumbBarAnchor")).click();
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(
                By.xpath("//span[contains(text(),'Freestyle project')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")).click();

        Assert.assertEquals(getDriver().findElement(
                By.className("page-headline")).getText(),
                "Project " + PROJECT_NAME);
        Assert.assertEquals(getDriver().findElement(
                By.className("task-link--active")).getAttribute("title"),
                "Status");

        clickLinkInPipelinePage("Build Now");

        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        for (String name : PAGE_NAMES) {
            clickLinkInPipelinePage(name);
            assertTrue(isSuccessfulBuildDisplayed());
        }

        getDriver().findElement(
                By.xpath("//a[contains(text(), 'trend')]")).click();

        assertTrue(getDriver().findElement(
                By.className("inside")).isDisplayed());
    }

    @Ignore
    @Test(priority = 2)
    public void testBuildIsOnTheTopOfTheBuildHistory_TC_006_002() {
        getDriver().findElement(By.xpath("//a[text()='" + PROJECT_NAME + "']")).click();

        for (int i = 0; i < 2; i++) {
            clickLinkInPipelinePage("Build Now");
        }

        for (String name : PAGE_NAMES) {
            clickLinkInPipelinePage(name);
            assertTrue(isBuildOnTopOfBuildHistory());
        }
    }
}