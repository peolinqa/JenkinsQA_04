import model.HomePage;
import model.PipelineConfigPage;
import model.ProjectPage;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static runner.ProjectUtils.ProjectType.Pipeline;


public class _PipelineTest extends BaseTest {
    private static final By SUBMIT_BUTTON = By.cssSelector("[type='submit']");
    private static final By APPLY_BUTTON = By.xpath("//button[contains(text(), 'Apply')]");
    private static final By RENAME_BUTTON = By.xpath("//button[text()='Rename']");
    private static final By H1 = By.xpath("//h1");
    private static final By NEW_NAME = By.xpath("//div[@class='setting-main']/input[@name='newName']");
    private static final By WARNING_MESSAGE = By.className("error");

    private static final String NEW_USER_DESCRIPTION = TestUtils.getRandomStr();
    private final String namePipeline = pipelineName();

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
            (final String attributeName,
             List<WebElement> listWebElements,
             final String convertFrom,
             final String convertTo) {

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

        final String displayDuplicatedJobName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .getNameErrorText();

        Assert.assertEquals(displayDuplicatedJobName, "» A job already exists with the name ‘" + name + "’");
    }

    @Test
    public void testCheckTransitionToPageWithError() {
        final String name = pipelineName();

        final boolean isErrorHeaderDisplayed = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .createAndGoToErrorPage()
                .isDisplayedErrorHeader();

        Assert.assertTrue(isErrorHeaderDisplayed);
    }

    @Test
    public void testCheckDropDownMenuPipeline() {
        final String name = pipelineName();

        final int checkDisplayedDropDownList = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .collectDropDownMenu();

        Assert.assertEquals(checkDisplayedDropDownList, 4);
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        final String name = pipelineName();

        final String checkTransitionPluginPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .scrollAndClickAdvancedButton()
                .clickHelpForFeatureDisplayName()
                .transitionToCorrectPage()
                .checkRedirectionPage();

        Assert.assertEquals(checkTransitionPluginPage, "Pipeline: Job");
    }

    @Test
    public void testJenkinsCredentialsProviderWindow() {
        final String name = pipelineName();

        final String titleOfJenkinsCredentialsProviderWindow = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .selectConfigurationMenuDefinition("Pipeline")
                .collectPipelineScriptDropDownMenu()
                .collectPipelineScriptScmDropDownMenu()
                .clickCredentialsAddButton()
                .clickJenkinsProviderButton()
                .openJenkinsCredentialsProviderWindow();

        Assert.assertEquals(titleOfJenkinsCredentialsProviderWindow, "Jenkins Credentials Provider: Jenkins");

        new PipelineConfigPage(getDriver()).closeJenkinsCredentialsProviderWindowAfterAssert();
    }

    @Test
    public void testPipelineSyntaxPageOpening() {
        final String name = pipelineName();

        final String hrefAttOfPipelineSyntaxLink = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .selectConfigurationMenuDefinition("Pipeline")
                .getHrefAndGoToPipelineSyntaxPage()
                .getPipelineSyntaxHrefAtt();

        Assert.assertTrue(hrefAttOfPipelineSyntaxLink.contains("pipeline-syntax"));
    }

    @Test
    public void testPipelineGroovyPageOpening() {
        final String name = pipelineName();

        final String useGroovySandBoxCheckboxAtt = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .getUseGroovySandBoxCheckboxAtt();

        Assert.assertEquals(useGroovySandBoxCheckboxAtt, "true");
    }

    @Test
    public void testTitleConfigPageContainsProjectTitle() {
        final String name = pipelineName();

        final String titleConfigPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .getTitleConfigPage();

        Assert.assertTrue(titleConfigPage.contains(name));
    }

    @Test
    public void test404PageAfterDeletedPipeline() {
        final String name = pipelineName();

        final String titleOfPage404 = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .navigateToPreviousCreatedPipeline(name)
                .deletePipelineProject()
                .switchToPage404()
                .getTitleOfPage404();

        Assert.assertEquals(titleOfPage404, "Error 404 Not Found");
    }

    @Test
    public void testCreatePipelineAndCheckOnDashboard() {
        final String name = pipelineName();

        final List<String> actualDashboardProject = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .getActualDashboardProject();

        Assert.assertTrue(actualDashboardProject.contains(name));
    }

    @Test
    public void testHelpTooltipsText() {
        final String name = pipelineName();

        final boolean check = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .checkHelpTooltipsTextCheckBoxHelpText();

        Assert.assertTrue(check);
    }

    @Test
    public void testApplyButtonNotificationAlert() {
        final String name = pipelineName();

        final String notificationSave = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .applyButtonClick()
                .notification();

        Assert.assertEquals(notificationSave, "Saved");
    }

    @Test
    public void testDeletePipelineFromSideMenu() {
        final String name = pipelineName();

        final boolean check = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickDeletePipelineButton()
                .clickDashboardButton()
                .checkProjectAfterDelete(name);

        Assert.assertTrue(check);
    }

    @Test
    public void testDeletePipelineFromDashboard() {
        final String name = pipelineName();

        final boolean check = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .projectMenuSelector(name)
                .clickDeleteProjectMenuSelector()
                .checkProjectAfterDelete(name);

        Assert.assertTrue(check);
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

    @Ignore
    @Test
    public void testDeleteAllPipelinesFromScriptConsole() {

        createPipeline(pipelineName(), Boolean.TRUE);

        homePageClick();
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        getDriver().findElement(By.xpath("//a[@href='script']")).click();

        cleanAllPipelines();

        String actualResult = getDriver().findElement(H1).getText();

        Assert.assertEquals(actualResult, "Welcome to Jenkins!");
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

    @Test
    public void testBuildPipelineWithParameters() {
        final String name = pipelineName();

        List<String> checkNameAndDescriptionParametersBuild = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickChoiceParameterButton()
                .enteringParametersIntoProject()
                .saveConfigAndGoToProject()
                .clickBuildWithParameters()
                .clickBuildButton()
                .refreshPage()
                .clickLastBuildButton()
                .clickParametersButton()
                .collectChoiceAndDescriptionParameterName();

        Assert.assertEquals(checkNameAndDescriptionParametersBuild, List.of("Checking Name Display\n"
                + "Checking Description Display"));
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
        final String viewName = "AchViewName";

        final int countColumns = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .clickNewView()
                .setViewName(viewName)
                .selectListViewType()
                .createViewAndGoConfig()
                .scrollAndClickJob()
                .addAllUniqueColumns()
                .clickApplyAndOkAndGoToMyViewPage()
                .getCountOfColumns();

        Assert.assertEquals(countColumns, 11);
    }

    @Test(dependsOnMethods = "testAddAllColumnsFromDashboardInOwnWatchlist")
    public void testRemoveColumnsFromDashboardInOwnWatchlist() {

        final int countColumnsAfterDelete = new HomePage(getDriver())
                .clickMyViewNameButton()
                .clickEditView()
                .scrollPageDown()
                .removeColumns()
                .clickApplyAndOkAndGoToMyViewPage()
                .getCountOfColumns();

        Assert.assertEquals(countColumnsAfterDelete, 1);
    }

    @Test
    public void testDragAndDropProjectParameters() {
        final String name = pipelineName();

        final List<String> locationProjectParameterized = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickBooleanParameterButton()
                .jsCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickChoiceParameterButton()
                .jsCheckboxDoNotAllowConcurrentBuilds()
                .menuChoiceParameterDragAndDrop()
                .collectLocationProjectParameterized();

        Assert.assertEquals(locationProjectParameterized, List.of("Choice Parameter", "Boolean Parameter"));
    }

    @Test
    public void testCreateAndCheckNewMyView() {
        final int countCreatedNewPipelines = 3;

        createFewPipelines(countCreatedNewPipelines, Boolean.TRUE);

        List<String> listExistingJobsOnDashboard = getTextFromAttributeAndConvertIt(
                "id",
                getDriver().findElements(By.xpath("//table[@id = 'projectstatus']/tbody/tr")),
                "job_",
                "");

        createNewView();

        chooseJobsOnCreateViewPage(listExistingJobsOnDashboard, 0);
        chooseJobsOnCreateViewPage(listExistingJobsOnDashboard, 2);

        scrollPageDown();
        click(APPLY_BUTTON, SUBMIT_BUTTON);

        List<String> listExistingJobsOnMyWathlist = getTextFromAttributeAndConvertIt("id",
                getDriver().findElements(By.xpath("//table[@id = 'projectstatus']/tbody/tr")),
                "job_",
                "");

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
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
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

        List<String> checkBuildHistoryByName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .buildSelectPipeline(name, true)
                .clickAndGoToBuildHistoryPage()
                .collectListBuildHistory();

        Assert.assertTrue(checkBuildHistoryByName.containsAll(List.of(name, name)));
    }

    @Test
    public void testPipelineCheckDiscardOld30builds() throws InterruptedException {
        ProjectPage projectPage = new ProjectPage(getDriver());
        List<Integer> expectedBuildNumbers = IntStream.range(2, 32).map(i -> 32 - i + 2 - 1).boxed().collect(Collectors.toList());

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(namePipeline)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickCheckboxDiscardOldBuilds()
                .saveConfigAndGoToProject()
                .clickMultipleTimesBuildButton(31)
                .waitForBuildNumber(31)
                .refreshPage();

        Assert.assertEquals(projectPage.getBuildsRowList().size(), 30);
        Assert.assertEquals(projectPage.getNumbersBuildsList(), expectedBuildNumbers);
    }

    @Test(dependsOnMethods = "testPipelineCheckDiscardOld30builds")
    public void testPipelineCheckDiscardOld3builds() {
        ProjectPage projectPage = new ProjectPage(getDriver());
        Integer[] expectedBuildNumbers = {32, 31, 30};

        projectPage
                .clickDashboardButton()
                .clickMyView()
                .moveToElement(namePipeline)
                .selectOptionInMenuSelector("Configure")
                .fillDiscardOldItems("3", "1")
                .saveConfigAndGoToProject()
                .clickBuildButton()
                .waitForBuildNumber(32)
                .refreshPage();

        Assert.assertEquals(projectPage.getBuildsRowList().size(), 3);
        Assert.assertEquals(projectPage.getNumbersBuildsList(), Arrays.asList(expectedBuildNumbers));
    }

    @Test
    public void testDeletePipelineDescription() {
        final String name = pipelineName();

        final boolean check = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickAddDescription()
                .addTextDescriptionAndSave(NEW_USER_DESCRIPTION)
                .clearUserDescription()
                .checkDescriptionValue();

        Assert.assertTrue(check);
    }

    @Test
    public void testCheckSequenceInParameters() {
        final String name = pipelineName();

        final List<String> CurrentLocationItemsInDropDownMenu = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickChoiceParameterButton()
                .enteringParametersIntoProject()
                .saveConfigAndGoToProject()
                .clickBuildWithParameters()
                .collectDropDownMenu();

        Assert.assertEquals(CurrentLocationItemsInDropDownMenu, List.of("This Is The Default Value", "2", "3"));
    }
}
