import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class FreestyleProjectBuildNowTest extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(3, 10);

    protected boolean isBuildDisplayed() {
        return getDriver().findElement(
                        By.xpath("//td[@class='build-row-cell']//span/span/*[name()='svg' and (contains(@tooltip, 'Success'))]")).
                isDisplayed();
    }

    protected void clickBuildNow() {
        getDriver().findElement(By.linkText("Build Now")).click();
    }

    protected void getChangesPage() {
        getDriver().findElement(By.linkText("Changes")).click();
    }

    protected void getWorkspacePage() {
        getDriver().findElement(By.linkText("Workspace")).click();
    }

    protected void getRenamePage() {
        getDriver().findElement(By.linkText("Rename")).click();
    }

    protected boolean isBuildOnTheTopOfTheBuildHistory() {
        List<WebElement> finishedBuilds = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//a[@class='tip model-link inside build-link display-name']"), 3));

        return finishedBuilds.get(2).getText().equals("#1") && finishedBuilds.get(0).getText().equals("#3");
    }

    @Test
    public void testBuildNow_TC_006_001() {
        getDriver().findElement(
                By.xpath("//a[@class='model-link inside breadcrumbBarAnchor']")).click();
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(
                By.xpath("//span[contains(text(),'Freestyle project')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")).click();

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//h1[@class = 'job-index-headline page-headline']")).getText(),
                "Project " + PROJECT_NAME);
        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//a[contains(@class, 'task-link--active')]//span[@class='task-link-text']")).getText(),
                "Status");

        clickBuildNow();

        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        assertTrue(isBuildDisplayed());

        getChangesPage();
        assertTrue(isBuildDisplayed());

        getWorkspacePage();
        assertTrue(isBuildDisplayed());

        getRenamePage();
        assertTrue(isBuildDisplayed());

        getDriver().findElement(
                By.xpath("//a[contains(text(), 'trend')]")).click();

        assertTrue(getDriver().findElement(
                By.xpath("//a[@class='model-link inside']")).isDisplayed());
    }

    @Test(dependsOnMethods = {"testBuildNow_TC_006_001"})
    public void testBuildIsOnTheTopOfTheBuildHistory_TC_006_002() {
        getDriver().findElement(By.xpath("//a[text()='" + PROJECT_NAME + "']")).click();

        for (int i = 0; i < 2; i++) {
            clickBuildNow();
        }

        getChangesPage();
        assertTrue(isBuildOnTheTopOfTheBuildHistory());

        getWorkspacePage();
        assertTrue(isBuildOnTheTopOfTheBuildHistory());

        getRenamePage();
        assertTrue(isBuildOnTheTopOfTheBuildHistory());
    }
}