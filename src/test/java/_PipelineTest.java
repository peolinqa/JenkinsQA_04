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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class _PipelineTest extends BaseTest {
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SUBMIT_BUTTON = By.cssSelector("[type='submit']");
    private static final By ADVANCED_BUTTON = By.xpath("//button[@id='yui-gen4-button']");
    private static final By H1 = By.xpath("//h1");
    private static final By PIPELINE_ITEM_CONFIGURATION =
            By.cssSelector(".config-section-activators .config_pipeline");
    private static final By LINK_JENKINS_HOMEPAGE = By.id("jenkins-name-icon");
    private static final String PIPELINE_NAME = RandomStringUtils.randomAlphanumeric(4, 8);
    private static final String JENKINS_HEADER = "Welcome to Jenkins!";
    private static final String DESCRIPTION_OF_PARAMETER = "//div[contains(text(),'Description of parameter')]";
    private static final String BUILD_WITH_PARAMETERS_BUTTON = "//span[contains(text(),'Build with Parameters')]";
    private static final String CHOICE_PARAMETER_NAME = "//div[contains(text(),'Name of the Choice Parameter')]";

    private JavascriptExecutor javascriptExecutor;
    SoftAssert asserts;

    @BeforeMethod
    public void setUp() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
        asserts = new SoftAssert();
        cleanAllPipelines();
    }

    private void createPipeline(String name, boolean buttonOk) {
        getDriver().findElement(By.cssSelector("[title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        if (buttonOk) {
            getDriver().findElement(OK_BUTTON).click();
        }
    }

    private void js(WebElement webElement) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    private void saveButtonClick() {
        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
    }

    private void homePageClick() {
        getDriver().findElement(LINK_JENKINS_HOMEPAGE).click();
    }

    private WebElement $(String locator) {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(locator))));
    }

    private WebElement $x(String locator) {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(locator))));
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

    private void cleanAllPipelines() {
        getDriver().findElement(LINK_JENKINS_HOMEPAGE).click();
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        getDriver().findElement(By.xpath("//a[@href='script']")).click();
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(
                        By.xpath("//div[@class='CodeMirror-scroll cm-s-default']")))
                .click()
                .sendKeys("for(j in jenkins.model.Jenkins.theInstance.getAllItems()) {j.delete()}")
                .moveToElement(getDriver().findElement(SUBMIT_BUTTON))
                .click()
                .build()
                .perform();
        homePageClick();
    }

    private void clickAddParameterOrBuildButton() {
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private void clickOnParameters() {
        getDriver().findElement(By.xpath("//span[contains(text(),'Parameters')]")).click();
    }

    @Test
    public void testCheckValidationItemName() {
        createPipeline(PIPELINE_NAME, Boolean.TRUE);
        saveButtonClick();
        getDriver().findElement(By.xpath("//li//a[@href='/']")).click();
        createPipeline(PIPELINE_NAME, Boolean.FALSE);
        String errorMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        getDriver().findElement(OK_BUTTON).click();
        String errorMessageTwo = getDriver().findElement(H1).getText();

        Assert.assertEquals(errorMessage, "» A job already exists with the name ‘" + PIPELINE_NAME + "’");
        Assert.assertEquals(errorMessageTwo, "Error");
    }

    @Test
    public void testCheckDropDownMenuPipeline() {
        createPipeline(PIPELINE_NAME, Boolean.TRUE);

        js(getDriver().findElement(By.cssSelector(".samples")));

        List<WebElement> optionsDropDown = getDriver().findElements(
                By.xpath("//div[1][@class='samples']//select/option"));

        Assert.assertEquals(optionsDropDown.size(), 4);
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        createPipeline(PIPELINE_NAME, Boolean.TRUE);

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
        createPipeline(PIPELINE_NAME, Boolean.TRUE);

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        Select pipelineScriptDropDownList = new Select(getDriver()
                .findElement(By.xpath("//div[@class='jenkins-form-item config_pipeline active']//select")));
        pipelineScriptDropDownList.selectByIndex(1);
        Select scmDropDownList = new Select(getDriver()
                .findElement(By.xpath("//div[@class='jenkins-form-item has-help']//select")));
        scmDropDownList.selectByIndex(1);

        getDriver().findElement(By.xpath("//button[@id='yui-gen15-button']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(
                By.xpath("//li[@id='yui-gen17']")))).click();

        WebElement titleOfJenkinsCredentialsProviderWindow = getDriver().findElement(By.xpath("//h2"));

        Assert.assertEquals(titleOfJenkinsCredentialsProviderWindow.getText(),
                "Jenkins Credentials Provider: Jenkins");

        js(getDriver().findElement(By.xpath("//button[@id='credentials-add-abort-button']")));

        getDriver().navigate().back();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testPipelineSyntaxPageOpening() {
        createPipeline(PIPELINE_NAME, Boolean.TRUE);

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
        createPipeline(PIPELINE_NAME, Boolean.TRUE);

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String useGroovySandboxCheckbox = getDriver().findElement(
                By.xpath("//input[@name='_.sandbox']")).getAttribute("checked");

        Assert.assertEquals(useGroovySandboxCheckbox, "true");
    }

    @Test
    public void testTitleConfigPageContainsProjectTitle() {
        final String name = PIPELINE_NAME;
        createPipeline(name, Boolean.TRUE);

        Assert.assertTrue(getDriver().getTitle().contains(name));
    }

    @Test
    public void test404PageAfterDeletedPipeline() {

        final String name = PIPELINE_NAME;
        createPipeline(name, Boolean.TRUE);

        getDriver().findElement(SUBMIT_BUTTON).click();
        buttonBackToDashboard();

        List<WebElement> pipelineProjects = getDriver().findElements(
                By.xpath(String.format("//a[contains(@href, 'job/%s')]", name)));

        getDriver().navigate().to(pipelineProjects.get(pipelineProjects.size() - 2).getAttribute("href"));
        getDriver().findElement(By.xpath("//a[contains(@data-message, 'Delete the Pipeline ')]")).click();
        getDriver().switchTo().alert().accept();

        getDriver().navigate().back();
        String titleOf404Page = getDriver().getTitle();

        Assert.assertTrue(titleOf404Page.contains("Error 404 Not Found"));
    }

    @Test
    public void testCreatePipelineAndCheckOnDashboard() {

        final String name = PIPELINE_NAME;
        createPipeline(name, Boolean.TRUE);
        saveButtonClick();

        Assert.assertTrue(getDriver().findElement(H1)
                .getText().contains(name));

        buttonBackToDashboard();

        List<WebElement> actualDashboardProject = getActualDashboardProject();
        for (WebElement webElement : actualDashboardProject) {
            if (webElement.getText().contains(name)) {
                Assert.assertTrue(true);
                break;
            }
        }
    }

    @Test
    public void testHelpTooltipsText() {

        createPipeline(PIPELINE_NAME, Boolean.TRUE);

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

        createPipeline(PIPELINE_NAME, Boolean.TRUE);
        getDriver().findElement(By.cssSelector("#yui-gen5-button")).click();

        Assert.assertEquals(getDriver().findElement(By.id("notification-bar"))
                .getText(), "Saved");
    }

    @Test
    public void testDeletePipelineFromSideMenu() {

        final String name = PIPELINE_NAME;
        createPipeline(name, Boolean.TRUE);
        saveButtonClick();

        getDriver().findElement(
                By.xpath("//a[@class='task-link  confirmation-link']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(name);
    }

    @Test
    public void testDeletePipelineFromDashboard() {

        final String name = PIPELINE_NAME;

        createPipeline(name, Boolean.TRUE);
        saveButtonClick();
        buttonBackToDashboard();

        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath(String.format("//a[text()='%s']", name)))).build().perform();
        getDriver().findElement(By.id("menuSelector")).click();

        getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(name);
    }

    @Test
    public void testCreatePipeline() {

        createPipeline(PIPELINE_NAME, Boolean.TRUE);

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

    @Test
    public void testDeleteAllPipelinesFromScriptConsole() {

        createPipeline(PIPELINE_NAME, Boolean.TRUE);

        homePageClick();
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        getDriver().findElement(By.xpath("//a[@href='script']")).click();

        cleanAllPipelines();

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

        createPipeline(name, Boolean.FALSE);
        WebElement errorMessage = $("#itemname-invalid");

        Assert.assertEquals(errorMessage.getText(), expectedMessage + " is an unsafe character");
    }

    @Test
    public void testCheckIcon() {
        int count = 25;
        final String name = PIPELINE_NAME;

        createPipeline(name, Boolean.TRUE);
        $("#cb16 ~ label").click();
        new Select($(".samples select")).selectByValue("hello");
        $("[type='submit']").click();
        $("#jenkins-home-link").click();
        $x(String.format("//span[contains(text(), '%s')]/following-sibling::*[name()='svg']", name)).click();
        String iconLocator = String.format("//tr[@id='job_%s']/td/div/span/*[@tooltip]", name);
        while ($x(iconLocator).getAttribute("tooltip").equals("In progress")
                || $x(iconLocator).getAttribute("tooltip").equals("Not built")
                && count > 0) {
            getDriver().navigate().refresh();
            count--;
        }
        Assert.assertEquals($x(iconLocator).getAttribute("tooltip"), "Success");
    }

    @Test
    public void testBuildPipelineWithParameters() {
        createPipeline("First Pipeline Project", Boolean.TRUE);
        getDriver().findElement(By
                .xpath("//label[contains(text(),'This project is parameterized')]")).click();
        clickAddParameterOrBuildButton();
        getDriver().findElement(By.xpath("//li[@id='yui-gen9']/a")).click();
        getDriver().findElement(By.name("parameter.name"))
                .sendKeys("Name of the Choice Parameter");
        getDriver().findElement(By.name("parameter.choices"))
                .sendKeys("First Choice" + '\n' + "Second Choice" + '\n' + "Third Choice");
        getDriver().findElement(By.name("parameter.description"))
                .sendKeys("Description of parameter");
        saveButtonClick();
        getDriver().findElement(By.xpath(BUILD_WITH_PARAMETERS_BUTTON)).click();

        asserts.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))
                .getText(), "Pipeline First Pipeline Project");

        asserts.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p"))
                .getText(), "This build requires parameters:");

        asserts.assertEquals(getDriver().findElement(By.xpath(CHOICE_PARAMETER_NAME))
                .getText(), "Name of the Choice Parameter");

        List<WebElement> option = getDriver().findElements(By.xpath("//select/option"));
        List<String> actualRes = new ArrayList<>();

        for (WebElement dropDown : option) {
            actualRes.add(dropDown.getText());
        }

        List<String> expectedRes = new ArrayList<>();
        expectedRes.add("First Choice");
        expectedRes.add("Second Choice");
        expectedRes.add("Third Choice");

        asserts.assertEquals(actualRes, expectedRes);

        asserts.assertEquals(getDriver().findElement(By
                        .xpath(DESCRIPTION_OF_PARAMETER))
                .getText(), "Description of parameter");

        clickAddParameterOrBuildButton();

        if ("expand".equals((getDriver().findElement(By.cssSelector(".collapse"))
                .getAttribute("title")))) {getDriver().findElement(By
                .xpath("//div[@id='buildHistory']/div[1]/div/a")).click();
        }

        WebElement buildOne = getWait5()
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='#1']//ancestor::tr")));
        buildOne.click();

        clickOnParameters();

        asserts.assertEquals(getDriver().findElement(By.xpath(CHOICE_PARAMETER_NAME))
                .getText(), "Name of the Choice Parameter");

        asserts.assertEquals(getDriver().findElement(By
                        .xpath("//input[@name='value']"))
                .getAttribute("value"), "First Choice");

        asserts.assertEquals(getDriver().findElement(By
                        .xpath(DESCRIPTION_OF_PARAMETER))
                .getText(), "Description of parameter");

        asserts.assertAll();
    }
}
