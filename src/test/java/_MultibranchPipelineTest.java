import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.UUID;

public class _MultibranchPipelineTest extends BaseTest {

    private static final String PROJECT_NAME = UUID.randomUUID().toString();
    private static final String ITEM_LOCATOR = String.format("//tr[@id='job_%s']//a[@href='job/%s/']", PROJECT_NAME, PROJECT_NAME);
    private static final String URL_GITHUB = "https://github.com/GitForProjects/javaJenkins";

    private WebElement findElementXpath(String xPath) {
        return getDriver().findElement(By.xpath(xPath));
    }

    private WebElement findElementId(String id) {
        return getDriver().findElement(By.id(id));
    }

    private WebElement waitPresenceOfElement(WebDriverWait wait, By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void clickOnJob(String xPath) {
        findElementXpath(xPath).click();
    }

    private void clickSaveButton() {
        findElementId("yui-gen8-button").click();
    }

    private void goToDashboard() {
        findElementXpath("//ul[@id='breadcrumbs']//a[contains(text(), 'Dashboard')]").click();
    }

    @Test
    public void testCreateNewJob() {
        WebElement newItemButton = waitPresenceOfElement(getWait5(), By.xpath("//span[@class='task-link-text' and contains (text(), 'New Item')]"));
        newItemButton.click();

        findElementId("name").sendKeys(PROJECT_NAME);
        findElementXpath("//span[@class='label' and contains(text(), 'Multibranch Pipeline')]").click();
        findElementId("ok-button").click();

        clickSaveButton();

        goToDashboard();
        String itemListLocator = String.format("//tr[@id='job_%s']", PROJECT_NAME);
        int itemList = getDriver().findElements(By.xpath(itemListLocator)).size();

        Assert.assertEquals(itemList, 1);
    }

    @Test (dependsOnMethods = "testCreateNewJob")
    public void testAddLink() {
        waitPresenceOfElement(getWait5(), By.xpath(ITEM_LOCATOR));
        clickOnJob(ITEM_LOCATOR);

        WebElement configureProject = waitPresenceOfElement(getWait5(), By.xpath("//a[@href='./configure']"));
        configureProject.click();

        WebElement addSourceButton = waitPresenceOfElement(getWait5(), By.id("yui-gen1-button"));
        Actions action = new Actions(getDriver());
        action.moveToElement(addSourceButton)
                .click()
                .pause(500)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        waitPresenceOfElement(getWait5(), By.xpath("//input[@name='_.repositoryUrl']"))
                .sendKeys(URL_GITHUB);
        action.moveToElement(findElementId("yui-gen20-button"))
                .click()
                .perform();
        final String validationStatus = waitPresenceOfElement(getWait20(), By.className("ok")).getText();

        Assert.assertEquals(validationStatus, String.format("Credentials ok. Connected to %s.", URL_GITHUB));

        clickSaveButton();
        goToDashboard();

        clickOnJob(ITEM_LOCATOR);
        findElementXpath("//span[@class='task-link-text' and contains(text(), 'Configure')]").click();

        final String textField = waitPresenceOfElement(getWait5(), By.xpath("//input[@name='_.repositoryUrl']")).getAttribute("value");

        Assert.assertEquals(textField, URL_GITHUB);
    }


    @Test (dependsOnMethods = "testAddLink")
    public void testScanResult() {
        waitPresenceOfElement(getWait5(), By.xpath(ITEM_LOCATOR));
        clickOnJob(ITEM_LOCATOR);

        findElementXpath("//span[@class='task-link-text' and contains(text(), 'Scan Repository Log')]").click();
        findElementXpath("//span[@class='task-link-text' and contains(text(), 'View as plain text')]").click();

        WebElement scanLog = waitPresenceOfElement(getWait5(), By.xpath("//pre"));

        Assert.assertTrue(scanLog.getText().contains("Finished: SUCCESS"));
    }

    @Test
    public void testCreateMultibranchPipelineWithValidData() {

        getDriver().findElement(By.className("task-link-text")).click();

        getDriver().findElement(By.id("name")).sendKeys("MultiPipeline");
        getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@id='yui-gen8-button']")).click();

        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        String newName = getDriver().findElement(By.xpath("//a[@href='job/MultiPipeline/']")).getText();
        Assert.assertEquals(newName, "MultiPipeline");

        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'job/MultiPipeline/')]")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Multibranch Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[text()='Yes']")).click();
    }
}