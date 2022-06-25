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
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _PipelineTest extends BaseTest {
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SUBMIT_BUTTON = By.cssSelector("[type='submit']");
    private static final By APPLY_BUTTON = By.xpath("//button[contains(text(), 'Apply')]");
    private static final By ADD_COLUMN_BUTTON = By.xpath("//button[contains(text(), 'Add column')]");
    private static final By DELETE_BUTTON = By.cssSelector("[title='Delete View']");
    private static final By ADVANCED_BUTTON = By.xpath("//button[@id='yui-gen4-button']");
    private static final By H1 = By.xpath("//h1");
    private static final By PIPELINE_ITEM_CONFIGURATION =
            By.cssSelector(".config-section-activators .config_pipeline");
    private static final By LINK_JENKINS_HOMEPAGE = By.id("jenkins-name-icon");
    private static final By CHECKBOX_PROJECT_PARAMETERIZED =
            By.xpath("//label[text()='This project is parameterized']");
    private static final By ADD_BOOLEAN_PARAMETER = By.xpath("//b[text()='Boolean Parameter']");

    private static final By NEW_VIEW = By.cssSelector("[title='New View']");

    private static final String JENKINS_HEADER = "Welcome to Jenkins!";
    private static final String DESCRIPTION_OF_PARAMETER = "//div[contains(text(),'Description of parameter')]";
    private static final String BUILD_WITH_PARAMETERS_BUTTON = "//span[contains(text(),'Build with Parameters')]";
    private static final String CHOICE_PARAMETER_NAME = "//div[contains(text(),'Name of the Choice Parameter')]";

    private JavascriptExecutor javascriptExecutor;
    private SoftAssert asserts;
    private Actions action;

    @BeforeMethod
    public void setUp() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
        asserts = new SoftAssert();
        action = new Actions(getDriver());
        cleanAllPipelines();
    }

    private String pipelineName() {
        return RandomStringUtils.randomAlphanumeric(4, 8);
    }

    private void createPipeline(String name, boolean buttonOk) {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        if (buttonOk) {
            getDriver().findElement(OK_BUTTON).click();
        }
    }

    private void js(WebElement webElement) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    private void scrollPageDown() {
        javascriptExecutor.executeScript("window.scrollBy(0, 500)");
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
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        getDriver().findElement(By.xpath("//a[@href='script']")).click();
        action.moveToElement(getDriver().findElement(
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

    private String createRandomName() {
        String nameSubstrate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        return RandomStringUtils.random(7, nameSubstrate);
    }

    private void createNewPipeline(String pipelineName) {
        createPipeline(pipelineName, Boolean.TRUE);
        getDriver().findElement(By.name("description")).sendKeys("Test pipeline");
        getDriver().findElement(By.id("yui-gen6-button")).click();
    }

    private void deleteCreatedPipeline(String pipelineName) {
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + pipelineName + "/']"))).build().perform();
        action.moveToElement(getDriver().findElement(
                By.xpath("//div[@id='menuSelector']"))).click().build().perform();
        action.moveToElement(getDriver().findElement(
                By.xpath("//a/span[contains(text(),'Delete Pipeline')]"))).click().build().perform();
        getDriver().switchTo().alert().accept();
    }

    private void goToPipelinePage(String pipelineName) {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[@href='/']")).click();
        getDriver().findElement(By.xpath("//a[@href='job/" + pipelineName + "/']")).click();
    }

    private List<String> getTextFromListWebElements(List<WebElement> listWebElements) {
        List<String> existingColumnsNames = new ArrayList<>();
        for (WebElement existingLisWebElements : listWebElements) {
            existingColumnsNames.add(existingLisWebElements.getText());
        }

        return existingColumnsNames;
    }

    private void click(By button) {
        getDriver().findElement(button).click();
    }

    private void click(By clickFirst, By clickSecond) {
        getDriver().findElement(clickFirst).click();
        getDriver().findElement(clickSecond).click();
    }

    private String myWatchlistName() {
        return createRandomName();
    }

    private void createFewPipelines(int countPipelines, boolean buttonOk) {
        for (int i = 0; i < countPipelines; i++) {
            getDriver().findElement(By.cssSelector("[title='New Item']")).click();
            getDriver().findElement(By.id("name")).sendKeys(pipelineName());
            getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
            if (buttonOk) {
                getDriver().findElement(OK_BUTTON).click();
            }
            click(LINK_JENKINS_HOMEPAGE);
        }
    }

    private List<String> getTextFromAttributeAndConvertIt
            (String attributeName, List<WebElement> listWebElements, String convertFrom, String convertTo) {
        List<String> listString = new ArrayList<>();
        for (WebElement existingListWebElements : listWebElements) {
            listString.add(existingListWebElements.getAttribute(attributeName).replace(convertFrom, convertTo));
        }

        return listString;
    }

    private void chooseJobsOnCreateViewPage(List<String> jobsNames, int indexRequiredJobs){
        getDriver().findElement(By.xpath(String.format("//input[@name = '%s']", jobsNames.get(indexRequiredJobs)))).click();
    }

    private void createNewView(){
        String myViewName = myWatchlistName();

        click(NEW_VIEW);
        getDriver().findElement(By.xpath("//input[@id = 'name']")).sendKeys(myViewName);
        getDriver().findElement(By.xpath("//label[@for = 'hudson.model.ListView']")).click();
        click(SUBMIT_BUTTON);
    }

    @Test
    public void testCheckValidationItemName() {
        final String name = pipelineName();
        createPipeline(name, Boolean.TRUE);
        saveButtonClick();
        getDriver().findElement(By.xpath("//li//a[@href='/']")).click();
        createPipeline(name, Boolean.FALSE);
        String errorMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        getDriver().findElement(OK_BUTTON).click();
        String errorMessageTwo = getDriver().findElement(H1).getText();

        Assert.assertEquals(errorMessage, "» A job already exists with the name ‘" + name + "’");
        Assert.assertEquals(errorMessageTwo, "Error");
    }

    @Test
    public void testCheckDropDownMenuPipeline() {
        createPipeline(pipelineName(), Boolean.TRUE);

        js(getDriver().findElement(By.cssSelector(".samples")));

        List<WebElement> optionsDropDown = getDriver().findElements(
                By.xpath("//div[1][@class='samples']//select/option"));

        Assert.assertEquals(optionsDropDown.size(), 4);
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        createPipeline(pipelineName(), Boolean.TRUE);

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
        createPipeline(pipelineName(), Boolean.TRUE);

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
        createPipeline(pipelineName(), Boolean.TRUE);

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
        createPipeline(pipelineName(), Boolean.TRUE);

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String useGroovySandboxCheckbox = getDriver().findElement(
                By.xpath("//input[@name='_.sandbox']")).getAttribute("checked");

        Assert.assertEquals(useGroovySandboxCheckbox, "true");
    }

    @Test
    public void testTitleConfigPageContainsProjectTitle() {
        final String name = pipelineName();
        createPipeline(name, Boolean.TRUE);

        Assert.assertTrue(getDriver().getTitle().contains(name));
    }

    @Test
    public void test404PageAfterDeletedPipeline() {

        final String name = pipelineName();
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

        final String name = pipelineName();
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

        createPipeline(pipelineName(), Boolean.TRUE);

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

        createPipeline(pipelineName(), Boolean.TRUE);
        getDriver().findElement(By.cssSelector("#yui-gen5-button")).click();

        Assert.assertEquals($("#notification-bar").getText(), "Saved");
    }

    @Test
    public void testDeletePipelineFromSideMenu() {

        final String name = pipelineName();
        createPipeline(name, Boolean.TRUE);
        saveButtonClick();

        getDriver().findElement(
                By.xpath("//a[@class='task-link  confirmation-link']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(name);
    }

    @Test
    public void testDeletePipelineFromDashboard() {

        final String name = pipelineName();

        createPipeline(name, Boolean.TRUE);
        saveButtonClick();
        buttonBackToDashboard();

        action.moveToElement(getDriver().findElement(
                By.xpath(String.format("//a[text()='%s']", name)))).build().perform();
        getDriver().findElement(By.id("menuSelector")).click();

        ProjectUtils.Dashboard.Pipeline.DELETE_PIPELINE.click(getDriver());
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(name);
    }

    @Ignore
    @Test
    public void testCreatePipeline() {

        createPipeline(pipelineName(), Boolean.TRUE);

        action.moveToElement(getDriver().findElement(
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

        createPipeline(pipelineName(), Boolean.TRUE);

        homePageClick();
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
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

    @Ignore
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
                .getAttribute("title")))) {
            getDriver().findElement(By
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

    @Test
    public void testPipelineBuildNow() {
        String[] buildSuccessfulPermalinks = new String[]{"Last build", "Last stable build", "Last successful build",
                "Last completed build"};
        String[] expectedBuildNumbers = new String[]{"#3", "#2", "#1"};

        String pipelineName = createRandomName();
        createNewPipeline(pipelineName);
        goToPipelinePage(pipelineName);

        for (int i = 0; i < 3; i++) {
            getDriver().findElement(By.xpath("//a[@href='/job/" + pipelineName + "/build?delay=0sec']"))
                    .click();
            getWait20().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//td[@class='build-row-cell']//a[contains(text(),'#" + (i + 1) + "')]")));
        }

        getDriver().navigate().refresh();

        List<WebElement> permalinks = getDriver().findElements(
                By.xpath("//ul[@class='permalinks-list']/li"));
        ArrayList<String> permalinksTexts = new ArrayList<>();
        for (int i = 0; i < permalinks.size(); i++) {
            permalinksTexts.add(permalinks.get(i).getText());
            Assert.assertTrue((permalinksTexts.get(i)).contains(buildSuccessfulPermalinks[i]));
        }

        List<WebElement> buildTableLinks = getDriver().findElements(
                By.xpath("//a[contains(@class,'display-name')]"));

        String[] buildNumbers = new String[3];
        for (int j = 0; j < buildTableLinks.size(); j++) {
            buildNumbers[j] = buildTableLinks.get(j).getText();
        }

        asserts.assertTrue(getDriver().findElement(
                By.xpath("//h2[normalize-space(.)='Permalinks']")).isDisplayed());
        asserts.assertTrue(getDriver().findElement(
                By.xpath("//div[contains(@class,'build-history')]")).isDisplayed());
        asserts.assertEquals(3, buildTableLinks.size());
        asserts.assertEquals(buildNumbers, expectedBuildNumbers);
        asserts.assertAll();

        deleteCreatedPipeline(pipelineName);
    }

    @Test
    public void testAddAllColumnsFromDashboardInOwnWatchlist() {
        final String name = pipelineName();
        final String viewName = pipelineName();
        createPipeline(name, Boolean.TRUE);
        click(SUBMIT_BUTTON, LINK_JENKINS_HOMEPAGE);

        createNewView();

        getDriver().findElement(By.xpath("//input[@name = '" + name + "']")).click();
        scrollPageDown();
        List<String> existingColumnsNames = getTextFromListWebElements(getDriver().findElements(
                By.xpath("//div[@descriptorid]//b")));
        click(ADD_COLUMN_BUTTON);
        List<String> columnsCanAddNames = getTextFromListWebElements(getDriver().findElements(
                By.cssSelector("#yui-gen4  li a")));
        columnsCanAddNames.removeAll(existingColumnsNames);

        for (String columnsCanAddName : columnsCanAddNames) {
            getDriver().findElement(
                    By.xpath("//a[contains(text(), '" + columnsCanAddName + "')]")).click();
            scrollPageDown();
            click(ADD_COLUMN_BUTTON);
        }

        click(APPLY_BUTTON, SUBMIT_BUTTON);

        List<WebElement> countColumns = getDriver().findElements(By.xpath("//thead/tr/th/a"));
        Assert.assertEquals(countColumns.size(), 11);
    }

    @Test
    public void testRemoveAllColumnsFromDashboardInOwnWatchlist() {
        final String name = pipelineName();
        final String viewName = pipelineName();
        createPipeline(name, Boolean.TRUE);
        click(SUBMIT_BUTTON, LINK_JENKINS_HOMEPAGE);

        createNewView();

        getDriver().findElement(By.xpath(String.format("//input[@name = '%s']", name))).click();
        scrollPageDown();
        List<String> existingColumnsNames = getTextFromListWebElements(
                getDriver().findElements(By.xpath("//div[@descriptorid]//b")));
        click(ADD_COLUMN_BUTTON);
        List<String> columnsCanAddNames = getTextFromListWebElements(
                getDriver().findElements(By.cssSelector("#yui-gen4  li a")));
        columnsCanAddNames.removeAll(existingColumnsNames);

        for (String columnsCanAddName : columnsCanAddNames) {
            getDriver().findElement(By.xpath(String.format("//a[contains(text(), '%s')]", columnsCanAddName))).click();
            scrollPageDown();
            click(ADD_COLUMN_BUTTON);
        }

        click(APPLY_BUTTON, SUBMIT_BUTTON);

        getDriver().findElement(By.xpath("//a[@title = 'Edit View']")).click();
        scrollPageDown();

        List<WebElement> countDeleteButtons = getDriver().findElements(DELETE_BUTTON);
        for (int i = 0; i < countDeleteButtons.size(); i++) {
            getWait5().until(ExpectedConditions.elementToBeClickable(
                    getDriver().findElement(
                            By.xpath(String.format("//button[@id = 'yui-gen%s-button']",(i + 6)))))).click();
        }

        click(APPLY_BUTTON, SUBMIT_BUTTON);

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//table[@id = 'projectstatus']//td")).getText().isEmpty());

        click(DELETE_BUTTON, SUBMIT_BUTTON);
    }

    @Test
    public void testDragAndDropProjectParameters() {
        final String name = pipelineName();

        List<String> expectedResult = Arrays.asList("Choice Parameter", "Boolean Parameter");

        createPipeline(name, Boolean.TRUE);
        getDriver().findElement(CHECKBOX_PROJECT_PARAMETERIZED).click();
        clickAddParameterOrBuildButton();
        getDriver().findElement(By.id("yui-gen8")).click();

        js(getDriver().findElement(CHECKBOX_PROJECT_PARAMETERIZED));

        clickAddParameterOrBuildButton();
        getDriver().findElement(By.id("yui-gen9")).click();

        js(getDriver().findElement(By.xpath("//label[text()='Do not allow concurrent builds']")));

        action.clickAndHold(getDriver().findElement(By.xpath("//b[text()='Choice Parameter']")))
                .moveToElement(getDriver().findElement(ADD_BOOLEAN_PARAMETER))
                .release(getDriver().findElement(ADD_BOOLEAN_PARAMETER))
                .perform();

        List<WebElement> projectParametersLocation = getDriver().findElements(
                By.xpath("//div[@class='dd-handle']/b"));
        for (int i = 0; i < projectParametersLocation.size(); i++) {

            Assert.assertEquals(projectParametersLocation.get(i).getText(), expectedResult.get(i));
        }
        saveButtonClick();
    }

    @Test
    public void testCreateAndCheckNewMyView(){
        final int countCreatedNewPipelines = 3;

        createFewPipelines(countCreatedNewPipelines, Boolean.TRUE);

        List<String> listExistingJobsOnDashboard = getTextFromAttributeAndConvertIt("id", getDriver().findElements(
                By.xpath("//table[@id = 'projectstatus']/tbody/tr")), "job_", "");

        createNewView();

        chooseJobsOnCreateViewPage(listExistingJobsOnDashboard, 0);
        chooseJobsOnCreateViewPage(listExistingJobsOnDashboard, 2);

        scrollPageDown();
        click(APPLY_BUTTON, SUBMIT_BUTTON);

        List<String> listExistingJobsOnMyWathlist = getTextFromAttributeAndConvertIt("id", getDriver().findElements(
                By.xpath("//table[@id = 'projectstatus']/tbody/tr")), "job_", "");

        for (String s : listExistingJobsOnMyWathlist) {
            Assert.assertTrue(listExistingJobsOnDashboard.contains(s));
        }
    }
}
