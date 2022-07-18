import model.HomePage;
import model.PipelineConfigPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static runner.ProjectUtils.ProjectType.Pipeline;


public class _PipelineTest extends BaseTest {
    private static final By RENAME_BUTTON = By.xpath("//button[text()='Rename']");
    private static final By H1 = By.xpath("//h1");
    private static final By NEW_NAME = By.xpath("//div[@class='setting-main']/input[@name='newName']");
    private static final String PIPELINE_NAME = TestUtils.getRandomStr(7);
    private static final String NEW_PIPELINE_NAME = String.format("New %s", PIPELINE_NAME);
    private final String namePipeline = pipelineName();
    private JavascriptExecutor javascriptExecutor;

    @BeforeMethod
    public void setUp() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
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

    @Deprecated
    private void createNewPipeline(String pipelineName) {
        createPipeline(pipelineName, Boolean.TRUE);
        getDriver().findElement(By.name("description")).sendKeys("Test pipeline");
        getDriver().findElement(By.id("yui-gen6-button")).click();
    }

    @Deprecated
    private void goToPipelinePage(String pipelineName) {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[@href='/']")).click();
        getDriver().findElement(By.xpath("//a[@href='job/" + pipelineName + "/']")).click();
    }

    private void click(By button) {
        getDriver().findElement(button).click();
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
        final List<String> actualDashboardProject = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .getActualDashboardProject();

        Assert.assertTrue(actualDashboardProject.contains(PIPELINE_NAME));
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

    @Ignore
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

    @Test
    public void testCreatePipelineWithNegativeValueQuietPeriod() {
        final String checkForValueErrorMessage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(pipelineName())
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .jsCheckboxProjectParameterized()
                .enteringDataIntoLineQuietPeriod()
                .verificationPeriodErrorMessage();

        Assert.assertEquals(checkForValueErrorMessage, "This value should be larger than 0");
    }

    @Ignore
    @Test
    public void testDeleteAllPipelinesFromScriptConsole() {
        final String name = pipelineName();

        final boolean check = new HomePage(getDriver())
                .clickNewItem()
                .setProjectTypePipeline()
                .setProjectName(name)
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .clickManageJenkins()
                .clickScriptConsole()
                .useDeleteAllProjectScript()
                .clickRunButton()
                .goHome()
                .checkProjectAfterDelete(name);

        Assert.assertTrue(check);
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

        final List<String> checkNameAndDescriptionParametersBuild = new HomePage(getDriver())
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
    public void testPermalinksTextAfterPipelineBuildNow() {
        final String name = pipelineName();

        String[] permalinksText = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickBuildButtonWait()
                .refreshPage()
                .permalinksText();

        Assert.assertEquals(permalinksText, new String[]{"Last build", "Last stable build",
                "Last successful build", "Last completed build"});
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
                .chooseJobs(1)
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
        final String name = pipelineName();
        final int countJobs = 2;

        List<String> listJobsInMyViewName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name.concat("1"))
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .clickNewItem()
                .setProjectName(name.concat("2"))
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .clickNewItem()
                .setProjectName(name.concat("3"))
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .clickNewView()
                .setViewName(name)
                .selectListViewType()
                .createViewAndGoConfig()
                .chooseJobs(countJobs)
                .clickApplyAndOkAndGoToMyViewPage()
                .getListJobsName();

        Assert.assertEquals(listJobsInMyViewName.size(), countJobs);
    }

    @Test(dependsOnMethods = "testCreatePipelineAndCheckOnDashboard")
    public void testRenamePipelineTheSameNameWithAllCapitalLetters() {
        final String errorText = new HomePage(getDriver())
                .clickProjectName(PIPELINE_NAME)
                .clickRenameButton()
                .setNewProjectName(PIPELINE_NAME.toUpperCase())
                .clickRenameAndGoToErrorPage()
                .getErrorMessage();

        Assert.assertEquals(errorText, String.format("The name “%s” is already in use.", PIPELINE_NAME.toUpperCase()));
    }

    @Test(dependsOnMethods = "testRenamePipelineWithTheSameName")
    public void testRenamePipelineWithValidName() {
        String newProjectName = new HomePage(getDriver())
                .clickProjectName(PIPELINE_NAME)
                .clickRenameButton()
                .setNewProjectName(NEW_PIPELINE_NAME)
                .clickRenameAndGoToProjectPage()
                .getPipelineProjectName();

        Assert.assertEquals(newProjectName, NEW_PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testRenamePipelineWithValidName")
    public void testRenamedProjectIsOnDashboard() {
        boolean projectIsDisplayed = new HomePage(getDriver())
                .checkProjectNameIsPresent(NEW_PIPELINE_NAME);

        Assert.assertTrue(projectIsDisplayed);
    }

    @Test(dependsOnMethods = "testRenamePipelineTheSameNameWithAllCapitalLetters")
    public void testRenamePipelineWithTheSameName() {
        final String errorText = new HomePage(getDriver())
                .clickProjectName(PIPELINE_NAME)
                .clickRenameButton()
                .clickRenameAndGoToErrorPage()
                .getErrorMessage();

        Assert.assertEquals(errorText, "The new name is the same as the current name.");
    }


    @Test
    public void testRenamePipelineWithInvalidName() {
        final String name = pipelineName();
        final List<String> invalidCharacters = Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", ">", "<", "|", "?");

        final List<String> listErrorMessages = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickRenameButton()
                .getListErrorMessages(invalidCharacters);

        List<String> listExpectedResult = invalidCharacters
                .stream()
                .map(el -> String.format("‘%s’ is an unsafe character", el))
                .collect(Collectors.toList());

        Assert.assertEquals(listErrorMessages, listExpectedResult);
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

        final List<String> checkBuildHistoryByName = new HomePage(getDriver())
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
    public void testPipelineCheckDiscardOld30builds() {
        final List<Integer> checkingDisplayLast30Builds =  new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(namePipeline)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickCheckBoxDiscardOldBuilds()
                .saveConfigAndGoToProject()
                .clickMultipleTimesBuildButton(31)
                .waitForBuildNumber(31)
                .refreshPage()
                .getNumbersBuildsList();

        List<Integer> expectedLast30BuildsNumbers = IntStream.range(2, 32).map(i -> 32 - i + 2 - 1).boxed()
                .collect(Collectors.toList());

        Assert.assertEquals(checkingDisplayLast30Builds, expectedLast30BuildsNumbers);
    }

    @Ignore
    @Test(dependsOnMethods = "testPipelineCheckDiscardOld30builds")
    public void testPipelineCheckDiscardOld3builds() {
       final List<Integer> checkingDisplayLast3Builds = new HomePage(getDriver())
                .clickDashboardButton()
                .clickMyView()
                .moveToElement(namePipeline)
                .clickMenuSelector()
                .clickInMenuSelectorConfigure()
                .enteringParametersToDisplayLatestBuilds("3", "1")
                .saveConfigAndGoToProject()
                .clickBuildButton()
                .waitForBuildNumber(32)
                .refreshPage()
                .getNumbersBuildsList();

        Assert.assertEquals(checkingDisplayLast3Builds, List.of(32, 31, 30));
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
                .addTextDescriptionAndSave(name)
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
