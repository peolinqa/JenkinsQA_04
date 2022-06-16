import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.UUID;

public class _MultibranchPipelineTest extends BaseTest {

    private static final String PROJECT_NAME = UUID.randomUUID().toString();
    private static final String ITEM_LOCATOR = String.format("//tr[@id='job_%s']//a[@href='job/%s/']", PROJECT_NAME, PROJECT_NAME);
    private static final String URL_GITHUB = "https://github.com/GitForProjects/javaJenkins";

    @Test
    public void testCreateNewItemMultibranch() {
        createNewMultibranchJob();
        goToDashboard();
        String itemListLocator = String.format("//tr[@id='job_%s']", PROJECT_NAME);
        int itemList = findElements(By.xpath(itemListLocator)).size();

        Assert.assertEquals(itemList, 1);
    }

    @Test
    public void testScanResult() {
        WebElement multibranchJob = waitPresenceOfElement(getWait5(), By.xpath(ITEM_LOCATOR));
        multibranchJob.click();

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

        WebElement branchesDropDown = findElementXpath("//div[@descriptorid='org.jenkinsci.plugins.github_branch_source.BranchDiscoveryTrait']//select");
        action.moveToElement(branchesDropDown)
                .click()
                .pause(500)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        WebElement pullRequestsDropdownOrigin = findElementXpath("//div[@descriptorid='org.jenkinsci.plugins.github_branch_source.OriginPullRequestDiscoveryTrait']//select");
        action.moveToElement(pullRequestsDropdownOrigin)
                .click()
                .pause(500)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        WebElement listForksStrategy = findElement(By.xpath("//div[@descriptorid='org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait']" +
                "//div[@class='jenkins-form-item tr has-help config-table-top-row']//select"));
        action.moveToElement(listForksStrategy)
                .click()
                .pause(500)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        WebElement pullRequestsForksTrust = findElementXpath("//div[@descriptorid='org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait']" +
                "//div[@class='jenkins-form-item has-help']//select");
        action.moveToElement(pullRequestsForksTrust)
                .click()
                .pause(500)
                .sendKeys(Keys.ARROW_UP)
                .sendKeys(Keys.ENTER)
                .perform();

        clickSaveButton();

        waitTextToBePresentInElement(getWait20(), findElementXpath("//pre[@id='out']"), "Finished: SUCCESS");

        goToDashboard();

        waitPresenceOfElement(getWait20(), By.xpath(ITEM_LOCATOR));
        findElementXpath(ITEM_LOCATOR).click();
        findElementXpath("//tr[@id='job_main']//a[contains(text(), 'main')]").click();

        Assert.assertNotNull(
                waitPresenceOfElement(
                        new WebDriverWait(getDriver(), 30),
                        By.xpath("//tbody[@class='tobsTable-body']//td[@class='stage-cell stage-cell-0 SUCCESS']")));
    }

    private void createNewMultibranchJob() {
        WebElement newItemButton = waitPresenceOfElement(getWait5(), By.xpath("//span[@class='task-link-text' and contains (text(), 'New Item')]"));
        newItemButton.click();
        findElementId("name").sendKeys(PROJECT_NAME);
        findElementXpath("//span[@class='label' and contains(text(), 'Multibranch Pipeline')]").click();
        findElementId("ok-button").click();
        clickSaveButton();
    }

    private void goToDashboard() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(text(), 'Dashboard')]")).click();
    }

    private void clickSaveButton() {
        findElementId("yui-gen8-button").click();
    }

    private WebElement findElement(By by) {
        return getDriver().findElement(by);
    }

    private List<WebElement> findElements(By by) {
        return getDriver().findElements(by);
    }

    private WebElement findElementXpath(String xPath) {
        return findElement(By.xpath(xPath));
    }

    private WebElement findElementId(String id) {
        return findElement(By.id(id));
    }

    private WebElement waitPresenceOfElement(WebDriverWait wait, By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitTextToBePresentInElement(WebDriverWait wait, WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}