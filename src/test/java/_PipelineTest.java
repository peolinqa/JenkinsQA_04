import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;
import java.util.List;

public class _PipelineTest extends BaseTest {
    private static final By NEW_ITEM = By.cssSelector("[title='New Item']");
    private static final By INPUT_LINE = By.id("name");
    private static final By PIPELINE = By.xpath("//span[text()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By ADVANCED_BUTTON = By.xpath("//span[@id='yui-gen4']");
    private static final By PIPELINE_ITEM_CONFIGURATION = By.xpath(
            "//div[@class='tab config-section-activator config_pipeline']");
    private static final String NAME_INPUT = "test123";
    private static final String PIPELINE_PROJECT_NAME = "Ruslan Gudenko Pipeline Project+";

    private JavascriptExecutor javascriptExecutor;
    private Date date;

    @BeforeMethod
    public void setUp() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
        date = new Date();
    }

    private void fillNameAndClick() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys(NAME_INPUT);
        getDriver().findElement(PIPELINE).click();
    }

    private void fillNameAndClick(Long date) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE)
                .sendKeys(NAME_INPUT + date);
        getDriver().findElement(PIPELINE).click();
    }

    @Test
    public void testCheckValidationItemName() {
        fillNameAndClick();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
        getDriver().findElement(By.xpath("//li//a[@href='/']")).click();
        fillNameAndClick();
        String errorMessage = getDriver().findElement(
                By.id("itemname-invalid")).getText();

        getDriver().findElement(OK_BUTTON).click();
        String errorMessageTwo = getDriver().findElement(
                By.xpath("//h1")).getText();

        Assert.assertEquals(errorMessage,
                "» A job already exists with the name ‘test123’");
        Assert.assertEquals(errorMessageTwo, "Error");
    }

    @Test
    public void testCheckDropDownMenuPipeline() {
        fillNameAndClick(date.getTime());
        getDriver().findElement(OK_BUTTON).click();

        WebElement dropDownMenu = getDriver().findElement(By.className("samples"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",
                dropDownMenu);

        List<WebElement> optionsDropDown = getDriver().findElements(
                By.xpath("//div[1][@class='samples']//select/option"));

        Assert.assertEquals(optionsDropDown.size(), 4);
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        fillNameAndClick(date.getTime());
        getDriver().findElement(OK_BUTTON).click();

        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",
                getDriver().findElement(ADVANCED_BUTTON));

        getDriver().findElement(ADVANCED_BUTTON).click();
        getDriver().findElement(By.cssSelector("a[tooltip$='Display Name']"))
                .click();
        String urlAttribute = getDriver().findElement(By.cssSelector(
                        "[href='https://plugins.jenkins.io/workflow-job']"))
                .getAttribute("href");

        getDriver().navigate().to(urlAttribute);
        String url = getDriver().getCurrentUrl();
        getDriver().navigate().back();

        Assert.assertTrue(url.contains("plugins.jenkins.io/workflow-job"));
    }

    private void createPipelineProject() {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(
                PIPELINE_PROJECT_NAME + date.getTime());
        getDriver().findElement(By.xpath(
                "//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
    }

    private void closeJenkinsCredProvWindowMethod() {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",
                getDriver().findElement(By.xpath("//button[@id='credentials-add-abort-button']")));
        getDriver().navigate().back();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testJenkinsCredentialsProviderWindow() {
        createPipelineProject();

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

        Assert.assertEquals(titleOfJenkinsCredentialsProviderWindow.getText(), "Jenkins Credentials Provider: Jenkins");

        closeJenkinsCredProvWindowMethod();
    }

    @Test
    public void testPipelineSyntaxPageOpening() {
        createPipelineProject();

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String pipelineSyntaxLink = getDriver().findElement(By.xpath("//a[@href='pipeline-syntax']"))
                .getAttribute("href");
        getDriver().get(pipelineSyntaxLink);

        List<WebElement> breadcrumbsOfLinksMenu = getDriver().findElements(By.xpath("//li[@class='item']/a"));
        String breadcrumbsPipelineSyntaxPageItem = breadcrumbsOfLinksMenu.get(2).getAttribute("href");

        Assert.assertTrue(breadcrumbsPipelineSyntaxPageItem.contains("pipeline-syntax"));
    }

    @Test
    public void testPipelineGroovyPageOpening() {
        createPipelineProject();

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String useGroovySandboxCheckbox = getDriver().findElement(By.xpath(
                "//input[@name='_.sandbox']")).getAttribute("checked");

        Assert.assertEquals(useGroovySandboxCheckbox, "true");
    }

    @Test
    public void testTitleConfigPageContainsProjectTitle() {
        createPipelineProject();

        String titleOfConfigurationPipelinePage = getDriver().getTitle();

        Assert.assertTrue(titleOfConfigurationPipelinePage.contains(PIPELINE_PROJECT_NAME + date.getTime()));
    }

    private void createPipeline(String namePipeline) {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(namePipeline);
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private List<WebElement> getActualDashboardProject() {
        return getDriver().findElements
                (By.xpath("//a[@class='jenkins-table__link model-link inside']"));
    }

    private void saveButtonPipelineClick() {
        getDriver().findElement(By.id("yui-gen6-button")).click();
    }

    private void buttonBackToDashboard() {
        getDriver().findElement
                (By.xpath("//a[@title='Back to Dashboard']")).click();
    }

    private void checkProjectAfterDelete(String projectName) {

        List<WebElement> actual = getDriver().findElements(By.xpath("//h1"));
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
            Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText()
                    , "Welcome to Jenkins!");
        }
    }

    @Test
    public void testCreatePipelineAndCheckOnDashboard() {

        final String namePipeline = "NewPipeline";

        createPipeline(namePipeline);
        saveButtonPipelineClick();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1"))
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
                            .replace("Help for feature: ", "")
                    , listOfCheckBoxWithHelps.get(i).getText());
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
        saveButtonPipelineClick();

        getDriver().findElement
                (By.xpath("//a[@class='task-link  confirmation-link']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(namePipeline);
    }

    @Test
    public void testDeletePipelineFromDashboard() {

        final String namePipeline = "DeletePipelineFromDashboard";

        createPipeline(namePipeline);
        saveButtonPipelineClick();
        buttonBackToDashboard();

        new Actions(getDriver()).moveToElement(getDriver().findElement
                (By.xpath("//a[text()='DeletePipelineFromDashboard']"))).build().perform();
        getDriver().findElement(By.id("menuSelector")).click();

        getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(namePipeline);
    }

    private void homePageClick(){
        getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']")).click();
    }

    private void saveButtonClick(){
        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
    }

    @Test(description = "TC_017.011")
    public void testCreatePipeline(){

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys("testJob-" + date.getTime());
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        Actions actions = new Actions(getDriver());
        actions
                .moveToElement(getDriver().findElement(By.xpath("//input[@name='hasCustomQuietPeriod']")))
                .click()
                .moveToElement(getDriver().findElement(By.name("quiet_period")))
                .click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys("-1")
                .sendKeys(Keys.TAB)
                .build()
                .perform();

        String actualResult = getDriver().findElement(By.xpath(
                "//input[@name='quiet_period']/../following-sibling::div[contains(@class, 'validation-error-area')]/div/div[@class='error']")).getText();

        Assert.assertEquals(actualResult, "This value should be larger than 0");

        saveButtonClick();
    }

    @Test(description = "TC_021.003")
    public void testDeleteAllPipelinesFromScriptConsole() {

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys("testJob-" + date.getTime());
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        final String allJobsDeleteScript = "for(j in jenkins.model.Jenkins.theInstance.getAllItems()) {j.delete()}";

        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
        homePageClick();
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        getDriver().findElement(By.xpath("//a[@href='script']")).click();

        Actions actions = new Actions(getDriver());
        actions
                .moveToElement(getDriver().findElement(By.xpath("//div[@class='CodeMirror-scroll cm-s-default']")))
                .click()
                .sendKeys(allJobsDeleteScript)
                .moveToElement(getDriver().findElement(By.xpath("//button[@type='submit']")))
                .click()
                .build()
                .perform();

        homePageClick();
        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(actualResult, "Welcome to Jenkins!");
    }

}
