import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.UUID;

public class _MultibranchPipelineTest extends BaseTest {

    private static final String PROJECT_NAME = UUID.randomUUID().toString();
    private static final String ITEM_LOCATOR = String.format("//tr[@id='job_%s']//a[@href='job/%s/']", PROJECT_NAME, PROJECT_NAME);
    private static final String URL_GITHUB = "https://github.com/GitForProjects/javaJenkins";
    private static final String PIPELINE_NAME = "MultiPipeline";

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

    private void createMultibranchPipeline(){
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void deleteMultibranchPipeline() {
        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'job/MultiPipeline/')]")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Multibranch Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[text()='Yes']")).click();
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

        waitPresenceOfElement(getWait5(), By.xpath("//a[@href='./configure']"))
                .click();

        waitPresenceOfElement(getWait5(), By.id("yui-gen1-button"))
                .click();
        waitPresenceOfElement(getWait5(),By.xpath("//div[@class='yui-module yui-overlay yuimenu yui-button-menu yui-menu-button-menu visible']"));
        findElementId("yui-gen10").click();

        final String urlField = "//input[@name='_.repositoryUrl']";
        waitPresenceOfElement(getWait5(), By.xpath(urlField))
                .sendKeys(URL_GITHUB);
        findElementId("yui-gen20-button").click();
        final String validationStatus = waitPresenceOfElement(getWait20(), By.className("ok")).getText();

        Assert.assertEquals(validationStatus, String.format("Credentials ok. Connected to %s.", URL_GITHUB));

        clickSaveButton();
        goToDashboard();

        clickOnJob(ITEM_LOCATOR);
        findElementXpath("//span[@class='task-link-text' and contains(text(), 'Configure')]").click();

        final String textField = waitPresenceOfElement(getWait5(), By.xpath(urlField)).getAttribute("value");

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

        createMultibranchPipeline();

        getDriver().findElement(By.xpath("//button[@id='yui-gen8-button']")).click();

        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        String newName = getDriver().findElement(By.xpath("//a[@href='job/MultiPipeline/']")).getText();
        Assert.assertEquals(newName, "MultiPipeline");
    }

    @Test (dependsOnMethods = "testCreateMultibranchPipelineWithValidData")
    public void testDeleteMultibranchPipelineProject() {
        String headerEmptyDashboard = "Welcome to Jenkins!";

        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href,'" + PIPELINE_NAME + "')]")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Multibranch Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[text()='Yes']")).click();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), headerEmptyDashboard);
    }

    @Test
    public void testCreateMultibranchWithInvalidData() {

        char[] unsafeCharacters = {'!', '@', '#', '$', '%', '^', '&', '*', '[', ']', ';', ':'};

        getDriver().findElement(By.linkText("New Item")).click();

        WebElement projectNameField = getDriver().findElement(By.name("name"));

        getDriver().findElement(
                By.xpath("//div[@id='j-add-item-type-nested-projects']//li[2]")
        ).click();

        for (char unsafeChar : unsafeCharacters) {
            String expectedError = String.format("» ‘%s’ is an unsafe character", unsafeChar);

            projectNameField.sendKeys(String.format("%s%s", unsafeChar, PROJECT_NAME));
            String actualError = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='itemname-invalid'][@class='input-validation-message']"))
            ).getText();

            Assert.assertEquals(actualError, expectedError);

            projectNameField.clear();
        }
    }
}