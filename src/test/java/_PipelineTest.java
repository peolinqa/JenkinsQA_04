import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;
import java.util.List;

public class _PipelineTest extends BaseTest {
    private static final By NEW_ITEM = By.cssSelector("[title='New Item']");
    private static final By INPUT_NAME = By.id("name");
    private static final By PIPELINE = By.xpath("//span[text()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SUBMIT_BUTTON = By.cssSelector("[type='submit']");
    private static final By ADVANCED_BUTTON = By.xpath("//span[@id='yui-gen4']");
    private static final By H1 = By.xpath("//h1");
    private static final By PIPELINE_ITEM_CONFIGURATION = By.xpath(
            "//div[@class='tab config-section-activator config_pipeline']");
    private static final String BASE_NAME = "name";
    private static final String NAME = "test123";
    private static final String NAME_FOR_ICON = RandomStringUtils.randomAlphanumeric(4, 8);
    private static final String JENKINS_HEADER = "Welcome to Jenkins!";
    private JavascriptExecutor javascriptExecutor;
    private Date date;

    @BeforeMethod
    public void setUp() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
        date = new Date();
    }

    private void createPipeline() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_NAME).sendKeys(NAME);
        getDriver().findElement(PIPELINE).click();
    }

    private void createPipeline(String namePipeline) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_NAME).sendKeys(namePipeline);
        getDriver().findElement(PIPELINE).click();
        getDriver().findElement(OK_BUTTON).click();
    }

    private void createPipeline(Long date) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_NAME).sendKeys(BASE_NAME + date);
        getDriver().findElement(PIPELINE).click();
        getDriver().findElement(OK_BUTTON).click();
    }

    private void js(WebElement locator) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", locator);
    }

    private void saveButtonClick() {
        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
    }

    private WebElement $(String locator) {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(locator))));
    }

    private WebElement $x(String locator) {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(locator))));
    }

    private void homePageClick() {
        getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']")).click();
    }

    private List<WebElement> getActualDashboardProject() {
        return getDriver().findElements(
                By.xpath("//a[@class='jenkins-table__link model-link inside']"));
    }

    private void buttonBackToDashboard() {
        getDriver().findElement(
                By.xpath("//a[@title='Back to Dashboard']")).click();
    }

    private void checkProjectAfterDelete(String projectName) {

        List<WebElement> actual = getDriver().findElements(H1);
        if (actual.size() == 0) {
            List<WebElement> actualDashboardProject = getActualDashboardProject();
            for (WebElement webElement : actualDashboardProject) {
                if (webElement.getText().contains(projectName)) {
                    Assert.fail();
                    break;
                } else {
                    Assert.assertTrue(true);
                }
            }
        } else {
            Assert.assertEquals(getDriver().findElement(H1).getText(), JENKINS_HEADER);
        }
    }

    @Test
    public void testCheckValidationItemName() {
        createPipeline();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SUBMIT_BUTTON).click();
        getDriver().findElement(By.xpath("//li//a[@href='/']")).click();
        createPipeline();
        String errorMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        getDriver().findElement(OK_BUTTON).click();
        String errorMessageTwo = getDriver().findElement(H1).getText();

        Assert.assertEquals(errorMessage, "» A job already exists with the name ‘test123’");
        Assert.assertEquals(errorMessageTwo, "Error");
    }

    @Test
    public void testCheckDropDownMenuPipeline() {
        createPipeline(date.getTime());

        WebElement dropDownMenu = getDriver().findElement(By.className("samples"));
        js(dropDownMenu);

        List<WebElement> optionsDropDown = getDriver().findElements(
                By.xpath("//div[1][@class='samples']//select/option"));

        Assert.assertEquals(optionsDropDown.size(), 4);
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        createPipeline(date.getTime());

        js(getDriver().findElement(ADVANCED_BUTTON));

        getDriver().findElement(ADVANCED_BUTTON).click();
        getDriver().findElement(By.cssSelector("a[tooltip$='Display Name']")).click();
        String urlAttribute = getDriver().findElement(By.cssSelector(
                "[href='https://plugins.jenkins.io/workflow-job']")).getAttribute("href");

        getDriver().navigate().to(urlAttribute);
        String url = getDriver().getCurrentUrl();
        getDriver().navigate().back();

        Assert.assertTrue(url.contains("plugins.jenkins.io/workflow-job"));
    }

    @Test
    public void testJenkinsCredentialsProviderWindow() {
        createPipeline(date.getTime());

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        Select pipelineScriptDropDownList = new Select(getDriver()
                .findElement(By.xpath("//div[@class='jenkins-form-item config_pipeline active']//select")));
        pipelineScriptDropDownList.selectByIndex(1);
        Select scmDropDownList = new Select(getDriver()
                .findElement(By.xpath("//div[@class='jenkins-form-item has-help']//select")));
        scmDropDownList.selectByIndex(1);

        getDriver().findElement(By.xpath("//button[@id='yui-gen15-button']")).click();
        getDriver().findElement(By.xpath("//li[@id='yui-gen17']")).click();

        WebElement titleOfJenkinsCredentialsProviderWindow = getDriver().findElement(By.xpath("//h2"));

        Assert.assertEquals(titleOfJenkinsCredentialsProviderWindow.getText(),
                "Jenkins Credentials Provider: Jenkins");

        js(getDriver().findElement(By.xpath("//button[@id='credentials-add-abort-button']")));

        getDriver().navigate().back();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testPipelineSyntaxPageOpening() {
        createPipeline(date.getTime());

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String pipelineSyntaxLink = getDriver().findElement(
                By.xpath("//a[@href='pipeline-syntax']")).getAttribute("href");
        getDriver().get(pipelineSyntaxLink);

        List<WebElement> breadcrumbsOfLinksMenu = getDriver().findElements(
                By.xpath("//li[@class='item']/a"));
        String breadcrumbsPipelineSyntaxPageItem = breadcrumbsOfLinksMenu.get(2).getAttribute("href");

        Assert.assertTrue(breadcrumbsPipelineSyntaxPageItem.contains("pipeline-syntax"));
    }

    @Test
    public void testPipelineGroovyPageOpening() {
        createPipeline(date.getTime());

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String useGroovySandboxCheckbox = getDriver().findElement(
                By.xpath("//input[@name='_.sandbox']")).getAttribute("checked");

        Assert.assertEquals(useGroovySandboxCheckbox, "true");
    }

    @Test
    public void testTitleConfigPageContainsProjectTitle() {
        createPipeline(date.getTime());

        String titleOfConfigurationPipelinePage = getDriver().getTitle();

        Assert.assertTrue(titleOfConfigurationPipelinePage.contains(BASE_NAME + date.getTime()));
    }

    @Test
    public void test404PageAfterDeletedPipeline() {
        createPipeline(date.getTime());

        getDriver().findElement(SUBMIT_BUTTON).click();
        buttonBackToDashboard();

        List<WebElement> pipelineProjects = getDriver().findElements(By.xpath(
                "//a[contains(@href, 'job/name')]"));

        getDriver().navigate().to( pipelineProjects.get(pipelineProjects.size() -2).getAttribute("href"));
        getDriver().findElement(By.xpath("//a[contains(@data-message, 'Delete the Pipeline ')]")).click();
        getDriver().switchTo().alert().accept();

        getDriver().navigate().back();
        String titleOf404Page = getDriver().getTitle();

        Assert.assertTrue(titleOf404Page.contains("Error 404 Not Found"));
    }

    @Test
    public void testCreatePipelineAndCheckOnDashboard() {

        final String namePipeline = "NewPipeline";

        createPipeline(namePipeline);
        saveButtonClick();

        Assert.assertTrue(getDriver().findElement(H1)
                .getText().contains(namePipeline));

        buttonBackToDashboard();

        List<WebElement> actualDashboardProject = getActualDashboardProject();
        for (WebElement webElement : actualDashboardProject) {
            if (webElement.getText().contains(namePipeline)) {
                Assert.assertTrue(true);
                break;
            }
        }
    }

    @Test
    public void testHelpTooltipsText() {

        final String namePipeline = "NewPipelineTooltips";

        createPipeline(namePipeline);

        List<WebElement> listOfCheckBoxWithHelps = getDriver()
                .findElements(By.xpath("//div[contains(@hashelp, 'true')]//label"));

        List<WebElement> checkBoxHelpsText = getDriver()
                .findElements(By.xpath("//div[contains(@hashelp, 'true')]//a"));

        for (int i = 0; i < listOfCheckBoxWithHelps.size(); i++) {
            Assert.assertEquals(checkBoxHelpsText.get(i).getAttribute("title")
                            .replace("Help for feature: ", ""),
                    listOfCheckBoxWithHelps.get(i).getText());
        }
    }

    @Test
    public void testApplyButtonNotificationAlert() {

        final String namePipeline = "NewPipelineNotification";

        createPipeline(namePipeline);
        getDriver().findElement(By.id("yui-gen5-button")).click();

        Assert.assertEquals(getDriver().findElement(By.id("notification-bar"))
                .getText(), "Saved");

    }

    @Test
    public void testDeletePipelineFromSideMenu() {

        final String namePipeline = "DeletePipelineFromSideMenu";

        createPipeline(namePipeline);
        saveButtonClick();

        getDriver().findElement(
                By.xpath("//a[@class='task-link  confirmation-link']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(namePipeline);
    }

    @Test
    public void testDeletePipelineFromDashboard() {

        final String namePipeline = "DeletePipelineFromDashboard";

        createPipeline(namePipeline);
        saveButtonClick();
        buttonBackToDashboard();

        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//a[text()='DeletePipelineFromDashboard']"))).build().perform();
        getDriver().findElement(By.id("menuSelector")).click();

        getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(namePipeline);
    }

    @Test
    public void testCreatePipeline() {

        createPipeline(date.getTime());

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(
                        By.xpath("//input[@name='hasCustomQuietPeriod']")))
                .click()
                .moveToElement(getDriver().findElement(By.name("quiet_period")))
                .click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys("-1")
                .sendKeys(Keys.TAB)
                .build()
                .perform();

        String actualResult =
                getDriver().findElement(By.xpath("//input[@name='quiet_period']/.." +
                                "/following-sibling::div[contains(@class, 'validation-error-area')]/div/div[@class='error']"))
                        .getText();

        Assert.assertEquals(actualResult, "This value should be larger than 0");

        saveButtonClick();
    }

    @Ignore
    @Test
    public void testDeleteAllPipelinesFromScriptConsole() {

        createPipeline(date.getTime());

        final String allJobsDeleteScript = "for(j in jenkins.model.Jenkins.theInstance.getAllItems()) {j.delete()}";

        saveButtonClick();
        homePageClick();
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        getDriver().findElement(By.xpath("//a[@href='script']")).click();

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(
                        By.xpath("//div[@class='CodeMirror-scroll cm-s-default']")))
                .click()
                .sendKeys(allJobsDeleteScript)
                .moveToElement(getDriver().findElement(SUBMIT_BUTTON))
                .click()
                .build()
                .perform();

        homePageClick();
        String actualResult = getDriver().findElement(H1).getText();

        Assert.assertEquals(actualResult, JENKINS_HEADER);
    }

    @DataProvider(name = "errorMessageData")
    public Object[][] errorData() {
        return new Object[][]{
                {"!", "» ‘!’"}, {"@", "» ‘@’"}, {"#", "» ‘#’"}, {"$", "» ‘$’"},
                {"%", "» ‘%’"}, {"^", "» ‘^’"}, {"&", "» ‘&’"}, {"*", "» ‘*’"},
                {":", "» ‘:’"}, {";", "» ‘;’"}, {"<", "» ‘<’"}, {">", "» ‘>’"},
                {"?", "» ‘?’"}, {"/", "» ‘/’"}, {"\\\\", "» ‘\\’"}
        };
    }

    @Test(dataProvider = "errorMessageData")
    public void testInvalidName(String name, String expectedMessage) {

        $x("//a[@title='New Item']").click();
        $("#name").sendKeys(name);
        $x("//li[contains(@class,'WorkflowJob')]").click();
        WebElement errorMessage = $("#itemname-invalid");

        Assert.assertEquals(errorMessage.getText(), expectedMessage + " is an unsafe character");
    }

    @Test
    public void testCheckIcon() {
        int count = 25;

        createPipeline(NAME_FOR_ICON);
        $("#cb16 ~ label").click();
        new Select($(".samples select")).selectByValue("hello");
        $("[type='submit']").click();
        $("#jenkins-home-link").click();
        $x(String.format("//span[contains(text(), '%s')]/following-sibling::*[name()='svg']", NAME_FOR_ICON)).click();
        String iconLocator = String.format("//tr[@id='job_%s']/td/div/span/*[@tooltip]", NAME_FOR_ICON);
        while ($x(iconLocator).getAttribute("tooltip").equals("In progress")
                || $x(iconLocator).getAttribute("tooltip").equals("Not built")
                && count > 0) {
            getDriver().navigate().refresh();
            count--;
        }
        Assert.assertEquals($x(iconLocator).getAttribute("tooltip"), "Success");
    }
}
