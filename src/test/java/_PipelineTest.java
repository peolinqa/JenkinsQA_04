import model.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static runner.ProjectUtils.ProjectType.Pipeline;

public class _PipelineTest extends BaseTest {
    private static final By SUBMIT_BUTTON = By.cssSelector("[type='submit']");
    private static final By APPLY_BUTTON = By.xpath("//button[contains(text(), 'Apply')]");
    private static final By ADD_COLUMN_BUTTON = By.xpath("//button[contains(text(), 'Add column')]");
    private static final By DELETE_BUTTON = By.cssSelector("[title='Delete View']");
    private static final By RENAME_BUTTON = By.xpath("//button[text()='Rename']");
    private static final By H1 = By.xpath("//h1");
    private static final By PIPELINE_ITEM_CONFIGURATION = By.cssSelector(".config-section-activators .config_pipeline");
    private static final By NEW_NAME = By.xpath("//div[@class='setting-main']/input[@name='newName']");
    private static final By WARNING_MESSAGE = By.className("error");

    private static final String JENKINS_HEADER = "Welcome to Jenkins!";
    private static final String DESCRIPTION_OF_PARAMETER = "//div[contains(text(),'Description of parameter')]";
    private static final String CHOICE_PARAMETER_NAME = "//div[contains(text(),'Name of the Choice Parameter')]";

    private JavascriptExecutor javascriptExecutor;
    private SoftAssert asserts;


    @BeforeMethod
    public void setUp() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
        asserts = new SoftAssert();
        getActions();
    }


    private String pipelineName() {
        return TestUtils.getRandomStr(7);
    }

    private void createPipeline(String name, boolean buttonOk) {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(By.id("name")).sendKeys(name);
        Pipeline.click(getDriver());
        if (buttonOk) {
            ProjectUtils.clickOKButton(getDriver());
        }
    }

    private void scrollPageDown() {
        javascriptExecutor.executeScript("window.scrollBy(0, 500)");
    }

    private void homePageClick() {
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
    }

    private WebElement $(String locator) {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(locator))));
    }

    @Deprecated
    private List<WebElement> getActualDashboardProject() {
        return getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link inside']"));
    }

    private void checkProjectAfterDelete(String projectName) {

        List<WebElement> actual = getDriver().findElements(H1);
        if (actual.size() == 0) {
            for (WebElement webElement : getActualDashboardProject()) {
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
        homePageClick();
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        getDriver().findElement(By.xpath("//a[@href='script']")).click();
        getActions().moveToElement(getDriver().findElement(
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

    private void createNewPipeline(String pipelineName) {
        createPipeline(pipelineName, Boolean.TRUE);
        getDriver().findElement(By.name("description")).sendKeys("Test pipeline");
        getDriver().findElement(By.id("yui-gen6-button")).click();
    }

    private void deleteCreatedPipeline(String pipelineName) {
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();
        getActions().moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + pipelineName + "/']"))).build().perform();
        getActions().moveToElement(getDriver().findElement(
                By.xpath("//div[@id='menuSelector']"))).click().build().perform();
        getActions().moveToElement(getDriver().findElement(
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

    private void createFewPipelines(int countPipelines, boolean buttonOk) {
        for (int i = 0; i < countPipelines; i++) {
            getDriver().findElement(By.cssSelector("[title='New Item']")).click();
            getDriver().findElement(By.id("name")).sendKeys(pipelineName());
            getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
            if (buttonOk) {
                ProjectUtils.clickOKButton(getDriver());
            }
            homePageClick();
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

    private void chooseJobsOnCreateViewPage(List<String> jobsNames, int indexRequiredJobs) {
        getDriver().findElement(By.xpath(String.format("//input[@name = '%s']", jobsNames.get(indexRequiredJobs)))).click();
    }

    private void createNewView() {
        String myViewName = "PipelineAC";

        ProjectUtils.Dashboard.Main.NewView.click(getDriver());
        getDriver().findElement(By.xpath("//input[@id = 'name']")).sendKeys(myViewName);
        getDriver().findElement(By.xpath("//label[@for = 'hudson.model.ListView']")).click();
        click(SUBMIT_BUTTON);
    }

    private void clickMenuSelectorLink(String pipelineName, String linkName) {
        getActions().moveToElement(getPipelineOnTheDashboard(pipelineName)).build().perform();
        getActions().moveToElement(getDriver().findElement(
                By.xpath("//div[@id='menuSelector']"))).click().build().perform();
        getActions().moveToElement(getDriver().findElement(
                By.xpath("//span[text()='" + linkName + "']/../../a"))).click().build().perform();
    }

    private WebElement getPipelineOnTheDashboard(String pipelineName) {
        return getDriver().findElement(
                By.xpath("//tr[@id='job_" + pipelineName + "']//a[contains(@class,'jenkins-table__link')]"));
    }

    @Test
    public void testCheckValidationItemName() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .clickDashboardButton()
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .checkPresenceErrorMessageAndAssert(name)
                .createAndGoToErrorPage()
                .checkHeaderWithErrorAndAssert("Error");
    }

    @Test
    public void testCheckDropDownMenuPipeline() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .jsDropDownMenuPipelineTab()
                .collectAndAssertDropDownMenu(4);
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .scrollAndClickAdvancedButton()
                .clickHelpForFeatureDisplayName()
                .transitionToCorrectPage()
                .checkRedirectionPageAndAssert("Pipeline: Job");
    }

    @Test
    public void testJenkinsCredentialsProviderWindow() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .selectConfigurationMenuDefinition("Pipeline")
                .collectPipelineScriptDropDownMenu()
                .collectPipelineScriptScmDropDownMenu()
                .clickCredentialsAddButton()
                .clickJenkinsProviderButton()
                .assertTitleOfJenkinsCredentialsProviderWindow("Jenkins Credentials Provider: Jenkins");
    }

    @Test
    public void testPipelineSyntaxPageOpening() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .selectConfigurationMenuDefinition("Pipeline")
                .getHrefAndGoToPipelineSyntaxPage()
                .assertPipelineSyntaxHrefAtt("pipeline-syntax");
    }

    @Test
    public void testPipelineGroovyPageOpening() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .assertUseGroovySandBoxCheckboxAtt();
    }

    @Test
    public void testTitleConfigPageContainsProjectTitle() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .assertPipelineConfigUrlContainsPipelineName(name);
    }

    @Test
    public void test404PageAfterDeletedPipeline() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .clickDashboardButton()
                .navigateToPreviousCreatedPipeline(name)
                .deletePipelineProject()
                .switchToPage404()
                .assertTitleOfPage404();
    }

    @Test
    public void testCreatePipelineAndCheckOnDashboard() {
        final String name = pipelineName();

        final List<String> actualDashboardProject = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .getActualDashboardProject();

        Assert.assertTrue(actualDashboardProject.contains(name));
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
        ProjectUtils.clickSaveButton(getDriver());

        getDriver().findElement(
                By.xpath("//a[@class='task-link  confirmation-link']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(name);
    }

    @Test
    public void testDeletePipelineFromDashboard() {
        final String name = pipelineName();

        createPipeline(name, Boolean.TRUE);
        ProjectUtils.clickSaveButton(getDriver());
        homePageClick();

        getActions().moveToElement(getDriver().findElement(
                By.xpath(String.format("//a[text()='%s']", name)))).build().perform();
        getDriver().findElement(By.id("menuSelector")).click();

        ProjectUtils.Dashboard.Pipeline.DeletePipeline.click(getDriver());
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(name);
    }

    @Ignore
    @Test
    public void testCreatePipeline() {

        createPipeline(pipelineName(), Boolean.TRUE);

        getActions().moveToElement(getDriver().findElement(
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

        ProjectUtils.clickSaveButton(getDriver());
    }

    @Test
    public void testDeleteAllPipelinesFromScriptConsole() {

        createPipeline(pipelineName(), Boolean.TRUE);

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
                {"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"},
                {":"}, {";"}, {"<"}, {">"}, {"?"}, {"/"}, {"\\"}
        };
    }

    @Test(dataProvider = "errorMessageData")
    public void testInvalidName(String name) {

        final String errorText = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .getErrorMessage();

        Assert.assertEquals(errorText, String.format("» ‘%s’ is an unsafe character", name));
    }

    @Ignore
    @Test
    public void testBuildPipelineWithParameters() {

        ProjectUtils.createProject(getDriver(), Pipeline, "First Pipeline Project");

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
        ProjectUtils.clickSaveButton(getDriver());
        getDriver().findElement(By.xpath("//span[contains(text(),'Build with Parameters')]")).click();

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

        asserts.assertEquals(getDriver().findElement(By.xpath(DESCRIPTION_OF_PARAMETER))
                .getText(), "Description of parameter");

        clickAddParameterOrBuildButton();

        if ("expand".equals((getDriver().findElement(By.cssSelector(".collapse"))
                .getAttribute("title")))) {
            getDriver().findElement(By
                    .xpath("//div[@id='buildHistory']/div[1]/div/a")).click();
        }

        WebElement buildOne = getWait20()
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='#1']//ancestor::tr")));
        buildOne.click();

        getDriver().findElement(By.xpath("//span[contains(text(),'Parameters')]")).click();

        asserts.assertEquals(getDriver().findElement(By.xpath(CHOICE_PARAMETER_NAME))
                .getText(), "Name of the Choice Parameter");

        asserts.assertEquals(getDriver().findElement(By
                .xpath("//input[@value='First Choice']")).getAttribute("value"), "First Choice");

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

        String pipelineName = pipelineName();
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

        createPipeline(name, Boolean.TRUE);
        click(SUBMIT_BUTTON, By.id("jenkins-name-icon"));

        createNewView();
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//input[@name = '%s']", name)))).click();
        scrollPageDown();
        List<String> existingColumnsNames = getTextFromListWebElements(getDriver().findElements(
                By.xpath("//div[@descriptorid]//b")));
        scrollPageDown();
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

    @Test(dependsOnMethods = "testAddAllColumnsFromDashboardInOwnWatchlist")
    public void testRemoveAllColumnsFromDashboardInOwnWatchlist() {

        getDriver().findElement(By.xpath("//a[contains(text(),'PipelineAC')]")).click();
        getDriver().findElement(By.linkText("Edit View")).click();
        scrollPageDown();

        List<WebElement> countDeleteButtons = getDriver().findElements(DELETE_BUTTON);
        for (int i = 0; i < countDeleteButtons.size(); i++) {
            getWait5().until(ExpectedConditions.elementToBeClickable(
                    getDriver().findElement(
                            By.xpath(String.format("//button[@id = 'yui-gen%s-button']", (i + 6)))))).click();
        }

        click(APPLY_BUTTON, SUBMIT_BUTTON);

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//table[@id = 'projectstatus']//td")).getText().isEmpty());

        click(DELETE_BUTTON, SUBMIT_BUTTON);
    }

    @Test
    public void testDragAndDropProjectParameters() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .clickCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickBooleanParameterButton()
                .jsCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickChoiceParameterButton()
                .jsCheckboxDoNotAllowConcurrentBuilds()
                .menuChoiceParameterDragAndDrop()
                .checkLocationProjectParameterizedAndAssert(Arrays.asList("Choice Parameter", "Boolean Parameter"))
                .saveConfigAndGoToProject();
    }

    @Test
    public void testCreateAndCheckNewMyView() {
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

    @Test
    public void testRenamePipelineTheSameNameWithAllCapitalLetters() {
        createNewPipeline("General");
        ProjectUtils.Dashboard.Pipeline.Rename.click(getDriver());
        TestUtils.clearAndSend(getDriver(), By.xpath("//input[@checkdependson='newName']"), "GENERAL");
        getDriver().findElement(By.id("main-panel")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE));

        String actualWarning = getDriver().findElement(WARNING_MESSAGE).getText();
        getDriver().findElement(By.id("yui-gen1-button")).click();
        String actualError = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();

        Assert.assertEquals(actualWarning, "The name “GENERAL” is already in use.");
        Assert.assertEquals(actualError, "Error");
    }

    @Test
    public void testRenamePipelineWithValidName() {
        final String pipelineName = pipelineName();
        final String newPipelineName = "NEW" + pipelineName;

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        createPipeline(pipelineName, Boolean.TRUE);
        ProjectUtils.clickSaveButton(getDriver());
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        clickMenuSelectorLink(pipelineName, "Rename");
        TestUtils.clearAndSend(getDriver(), NEW_NAME, newPipelineName);
        click(RENAME_BUTTON);

        Assert.assertTrue(getDriver().findElement(H1).getText().contains(newPipelineName));

        ProjectUtils.Dashboard.Pipeline.BackToDashboard.click(getDriver());

        Assert.assertTrue(getPipelineOnTheDashboard(newPipelineName).isDisplayed());
    }

    @Test
    public void testRenamePipelineWithTheSameName() {
        final String pipelineName = pipelineName();

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        createPipeline(pipelineName, Boolean.TRUE);
        ProjectUtils.clickSaveButton(getDriver());
        goToPipelinePage(pipelineName);
        ProjectUtils.Dashboard.Pipeline.Rename.click(getDriver());

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//div[@class='warning']")).getText(),
                "The new name is the same as the current name.");

        click(RENAME_BUTTON);

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");
        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//div[@id='main-panel']/p")).getText(),
                "The new name is the same as the current name.");
    }

    @Test
    public void testRenamePipelineWithInvalidName() {
        final String pipelineName = pipelineName();
        final String[] invalidCharacters = {"!", "@", "#", "$", "%", "^", "*", ":", ";", "\\", "|", "?"};

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        createPipeline(pipelineName, Boolean.TRUE);
        ProjectUtils.clickSaveButton(getDriver());
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        clickMenuSelectorLink(pipelineName, "Rename");

        for (String str : invalidCharacters) {
            getDriver().findElement(NEW_NAME).sendKeys(str);
            click(RENAME_BUTTON);

            Assert.assertEquals(getDriver().findElement(
                    By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");
            Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText(),
                    String.format("‘%s’ is an unsafe character", str));

            getDriver().navigate().back();
        }

        Assert.assertTrue(getDriver().findElement(H1).getText().contains(pipelineName));
    }

    @Test
    public void testCheckPositiveBuildIcon() {
        final String name = pipelineName();

        final boolean isStatus = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .selectScriptByValue("hello")
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .buildSelectPipeline(name, false)
                .clickRefreshPage()
                .clickProjectName(name)
                .isProjectStatus("job SUCCESS");

        Assert.assertTrue(isStatus);
    }

    @Test
    public void testCheckScheduledBuildInBuildHistory() {
        final String name = pipelineName();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectType(Pipeline)
                .createAndGoToPipelineConfigure()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .buildSelectPipeline(name, true)
                .clickAndGoToBuildHistoryPage()
                .collectListBuildHistoryAndAssert(Arrays.asList(name, name));
    }
}