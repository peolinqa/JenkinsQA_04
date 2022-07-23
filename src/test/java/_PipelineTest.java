import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _PipelineTest extends BaseTest {

    private static final String PIPELINE_NAME = TestUtils.getRandomStr(7);
    private static final String NEW_PIPELINE_NAME = String.format("New %s", PIPELINE_NAME);

    @Test
    public void testCheckValidationItemName() {
        final String name = TestUtils.getRandomStr(7);

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

        Assert.assertEquals(displayDuplicatedJobName, String.format("» A job already exists with the name ‘%s’", name));
    }

    @Test
    public void testCheckTransitionToPageWithError() {
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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
    }

    @Test
    public void testPipelineSyntaxPageOpening() {
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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
        final String titleOfPage404 = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .navigateToPreviousCreatedPipeline(PIPELINE_NAME)
                .clickDeleteProjectAndConfirm()
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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

        final boolean check = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickDeleteProjectAndConfirm()
                .clickDashboardButton()
                .checkProjectAfterDelete(name);

        Assert.assertTrue(check);
    }

    @Ignore
    @Test
    public void testDeletePipelineFromDashboard() {
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

        final String checkForValueErrorMessage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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

        Assert.assertEquals(checkNameAndDescriptionParametersBuild,
                List.of("Checking Name Display\nChecking Description Display"));
    }

    @Test
    public void testPermalinksTextAfterPipelineBuildNow() {
        final String name = TestUtils.getRandomStr(7);

        final String[] permalinksText = new HomePage(getDriver())
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
        final String name = TestUtils.getRandomStr(7);
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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);
        final int countJobs = 2;

        final List<String> listJobsInMyViewName = new HomePage(getDriver())
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
                .clickRenameAndGoToRenamePage()
                .setNewProjectName(PIPELINE_NAME.toUpperCase())
                .clickRenameAndGoToErrorPage()
                .getErrorMessage();

        Assert.assertEquals(errorText, String.format("The name “%s” is already in use.", PIPELINE_NAME.toUpperCase()));
    }

    @Test(dependsOnMethods = "testRenamePipelineWithTheSameName")
    public void testRenamePipelineWithValidName() {
        final String newProjectName = new HomePage(getDriver())
                .clickProjectName(PIPELINE_NAME)
                .clickRenameAndGoToRenamePage()
                .setNewProjectName(NEW_PIPELINE_NAME)
                .clickRenameAndGoToProjectPage()
                .getPipelineProjectName();

        Assert.assertEquals(newProjectName, NEW_PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testRenamePipelineWithValidName")
    public void testRenamedProjectIsOnDashboard() {
        final boolean projectIsDisplayed = new HomePage(getDriver())
                .checkProjectNameIsPresent(NEW_PIPELINE_NAME);

        Assert.assertTrue(projectIsDisplayed);
    }

    @Test(dependsOnMethods = "testRenamePipelineTheSameNameWithAllCapitalLetters")
    public void testRenamePipelineWithTheSameName() {
        final String errorText = new HomePage(getDriver())
                .clickProjectName(PIPELINE_NAME)
                .clickRenameAndGoToRenamePage()
                .clickRenameAndGoToErrorPage()
                .getErrorMessage();

        Assert.assertEquals(errorText, "The new name is the same as the current name.");
    }

    @Test
    public void testRenamePipelineWithInvalidName() {
        final String name = TestUtils.getRandomStr(7);
        final List<String> invalidCharacters = Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\",
                "/", ">", "<", "|", "?");

        final List<String> listErrorMessages = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickRenameAndGoToRenamePage()
                .getListErrorMessages(invalidCharacters);

        List<String> listExpectedResult = invalidCharacters
                .stream()
                .map(el -> String.format("‘%s’ is an unsafe character", el))
                .collect(Collectors.toList());

        Assert.assertEquals(listErrorMessages, listExpectedResult);
    }

    @Test
    public void testCheckPositiveBuildIcon() {
        final String name = TestUtils.getRandomStr(7);

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

    @Test(dependsOnMethods = "testCreatePipelineAndCheckOnDashboard")
    public void testCheckScheduledBuildInBuildHistory() {
        final List<String> checkBuildHistoryByName = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .buildSelectPipeline(PIPELINE_NAME, true)
                .clickAndGoToBuildHistoryPage()
                .collectListBuildHistory(PIPELINE_NAME);

        Assert.assertTrue(checkBuildHistoryByName.containsAll(List.of(PIPELINE_NAME, PIPELINE_NAME)));
    }

    @Test
    public void testPipelineCheckDiscardOld30builds() {
        final List<Integer> checkingDisplayLast30Builds = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickCheckBoxDiscardOldBuilds()
                .saveConfigAndGoToProject()
                .clickMultipleTimesBuildButton(31)
                .waitForBuildNumber(31)
                .refreshPage()
                .getNumbersBuildsList();

        List<Integer> expectedLast30BuildsNumbers = IntStream
                .range(2, 32)
                .map(i -> 32 - i + 2 - 1)
                .boxed()
                .collect(Collectors.toList());

        Assert.assertEquals(checkingDisplayLast30Builds, expectedLast30BuildsNumbers);
    }

    @Test(dependsOnMethods = "testPipelineCheckDiscardOld30builds")
    public void testPipelineCheckDiscardOld3builds() {
        final List<Integer> checkingDisplayLast3Builds = new HomePage(getDriver())
                .clickDashboardButton()
                .clickMyView()
                .moveToElement(PIPELINE_NAME)
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
        final String name = TestUtils.getRandomStr(7);

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
        final String name = TestUtils.getRandomStr(7);

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
