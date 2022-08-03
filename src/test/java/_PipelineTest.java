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
    private static final String NEW_PIPELINE_NAME = String.format("New%s", PIPELINE_NAME);

    @Test
    public void testCheckValidationItemName() {
        final String displayDuplicatedJobName = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .getSideMenu()
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypePipeline()
                .getNameErrorText();

        Assert.assertEquals(displayDuplicatedJobName, String.format("» A job already exists with the name ‘%s’", PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCheckValidationItemName")
    public void testCheckTransitionToPageWithError() {
        final boolean isErrorHeaderDisplayed = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypePipeline()
                .createAndGoToErrorPage()
                .isDisplayedErrorHeader();

        Assert.assertTrue(isErrorHeaderDisplayed);
    }

    @Test(dependsOnMethods = "testCheckTransitionToPageWithError")
    public void testCheckDropDownMenuPipeline() {
        final int checkDisplayedDropDownList = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .collectDropDownMenu();

        Assert.assertEquals(checkDisplayedDropDownList, 4);
    }

    @Test(dependsOnMethods = "testCheckDropDownMenuPipeline")
    public void testJenkinsCredentialsProviderWindow() {
        final String titleOfJenkinsCredentialsProviderWindow = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .selectConfigurationMenuDefinition("Pipeline")
                .collectPipelineScriptDropDownMenu()
                .collectPipelineScriptScmDropDownMenu()
                .clickCredentialsAddButton()
                .clickJenkinsProviderButton()
                .openJenkinsCredentialsProviderWindow();

        Assert.assertEquals(titleOfJenkinsCredentialsProviderWindow, "Jenkins Credentials Provider: Jenkins");
    }

    @Test(dependsOnMethods = "testJenkinsCredentialsProviderWindow")
    public void testPipelineSyntaxPageOpening() {
        final String hrefAttOfPipelineSyntaxLink = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .selectConfigurationMenuDefinition("Pipeline")
                .getHrefAndGoToPipelineSyntaxPage()
                .getPipelineSyntaxHrefAtt();

        Assert.assertTrue(hrefAttOfPipelineSyntaxLink.contains("pipeline-syntax"));
    }

    @Test(dependsOnMethods = "testPipelineSyntaxPageOpening")
    public void testPipelineGroovyPageOpening() {
        final String useGroovySandBoxCheckboxAtt = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .getUseGroovySandBoxCheckboxAtt();

        Assert.assertEquals(useGroovySandBoxCheckboxAtt, "true");
    }

    @Test(dependsOnMethods = "testPipelineGroovyPageOpening")
    public void testTitleConfigPageContainsProjectTitle() {
        final String titleConfigPage = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .getTitleConfigPage();

        Assert.assertTrue(titleConfigPage.contains(PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testTitleConfigPageContainsProjectTitle")
    public void testCreatePipelineAndCheckOnDashboard() {
        final List<String> actualDashboardProject = new HomePage(getDriver())
                .clickDashboardButton()
                .getActualDashboardProject();

        Assert.assertTrue(actualDashboardProject.contains(PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCreatePipelineAndCheckOnDashboard")
    public void testHelpTooltipsText() {
        final boolean check = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .checkHelpTooltipsTextCheckBoxHelpText();

        Assert.assertTrue(check);
    }

    @Test(dependsOnMethods = "testHelpTooltipsText")
    public void testApplyButtonNotificationAlert() {
        final String notificationSave = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .applyButtonClick()
                .notification();

        Assert.assertEquals(notificationSave, "Saved");
    }

    @Test(dependsOnMethods = "testApplyButtonNotificationAlert")
    public void testCreatePipelineWithNegativeValueQuietPeriod() {
        final String checkForValueErrorMessage = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .jsCheckboxProjectParameterized()
                .enteringDataIntoLineQuietPeriod()
                .verificationPeriodErrorMessage();

        Assert.assertEquals(checkForValueErrorMessage, "This value should be larger than 0");
    }

    @Test(dependsOnMethods = "testCreatePipelineWithNegativeValueQuietPeriod")
    public void testPermalinksTextAfterPipelineBuildNow() {
        final String[] permalinksText = new HomePage(getDriver())
                .clickPipelineName(PIPELINE_NAME)
                .clickBuildButtonWait()
                .refreshPage()
                .permalinksText();

        Assert.assertEquals(permalinksText, new String[]{"Last build", "Last stable build",
                "Last successful build", "Last completed build"});
    }

    @Test(dependsOnMethods = "testPermalinksTextAfterPipelineBuildNow")
    public void testDragAndDropProjectParameters() {
        final List<String> locationProjectParameterized = new HomePage(getDriver())
                .projectMenuSelector(PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
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
    @Test(dependsOnMethods = "testDragAndDropProjectParameters")
    public void testDeletePipelineDescription() {
        final boolean check = new HomePage(getDriver())
                .clickPipelineName(PIPELINE_NAME)
                .clickAddDescription()
                .addTextDescriptionAndSave("Text Description")
                .clearUserDescription()
                .checkDescriptionValue();

        Assert.assertTrue(check);
    }

    @Test(dependsOnMethods = "testDeletePipelineDescription")
    public void testRenamePipelineWithInvalidName() {
        final List<String> invalidCharacters = Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\",
                "/", ">", "<", "|", "?");

        final List<String> listErrorMessages = new HomePage(getDriver())
                .clickPipelineName(PIPELINE_NAME)
                .clickRenameAndGoToRenamePage()
                .getListErrorMessages(invalidCharacters);

        List<String> listExpectedResult = invalidCharacters
                .stream()
                .map(el -> String.format("‘%s’ is an unsafe character", el))
                .collect(Collectors.toList());

        Assert.assertEquals(listErrorMessages, listExpectedResult);
    }

    @Test(dependsOnMethods = "testRenamePipelineWithInvalidName")
    public void testRenamePipelineWithValidName() {
        final String newProjectName = new HomePage(getDriver())
                .clickPipelineName(PIPELINE_NAME)
                .clickRenameAndGoToRenamePage()
                .setNewProjectName(NEW_PIPELINE_NAME)
                .clickRenameAndGoToProjectPage()
                .getProjectName();

        Assert.assertEquals(newProjectName, NEW_PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testRenamePipelineWithValidName")
    public void testRenamedProjectIsOnDashboard() {
        final boolean projectIsDisplayed = new HomePage(getDriver())
                .checkProjectNameIsPresent(NEW_PIPELINE_NAME);

        Assert.assertTrue(projectIsDisplayed);
    }

    @Test(dependsOnMethods = "testRenamedProjectIsOnDashboard")
    public void testRenamePipelineTheSameNameWithAllCapitalLetters() {
        final String errorText = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .clickRenameAndGoToRenamePage()
                .setNewProjectName(NEW_PIPELINE_NAME.toUpperCase())
                .clickRenameAndGoToErrorPage()
                .getErrorMessage();

        Assert.assertEquals(errorText, String.format("The name “%s” is already in use.", NEW_PIPELINE_NAME.toUpperCase()));
    }

    @Test(dependsOnMethods = "testRenamePipelineTheSameNameWithAllCapitalLetters")
    public void testRenamePipelineWithTheSameName() {
        final String errorText = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .clickRenameAndGoToRenamePage()
                .clickRenameAndGoToErrorPage()
                .getErrorMessage();

        Assert.assertEquals(errorText, "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenamePipelineWithTheSameName")
    public void testDeletePipelineFromSideMenu() {
        final boolean check = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .clickDeleteProjectAndConfirm()
                .clickDashboardButton()
                .checkProjectAfterDelete(NEW_PIPELINE_NAME);

        Assert.assertTrue(check);
    }

    @Test(dependsOnMethods = {"testCheckValidationItemName", "testDeletePipelineFromSideMenu"})
    public void testCheckScheduledBuildInBuildHistory() {
        final List<String> checkBuildHistoryByName = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(NEW_PIPELINE_NAME)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .buildSelectPipeline(NEW_PIPELINE_NAME, true)
                .getSideMenu()
                .clickBuildHistory()
                .collectListBuildHistory(NEW_PIPELINE_NAME);

        Assert.assertEquals(checkBuildHistoryByName, List.of(NEW_PIPELINE_NAME, NEW_PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCheckScheduledBuildInBuildHistory")
    public void testCheckPositiveBuildIcon() {
        final boolean isStatus = new HomePage(getDriver())
                .clickDashboardButton()
                .buildSelectPipeline(NEW_PIPELINE_NAME, false)
                .clickRefreshPage()
                .clickPipelineName(NEW_PIPELINE_NAME)
                .refreshPage()
                .isProjectStatus("job SUCCESS");

        Assert.assertTrue(isStatus);
    }

    @Test(dependsOnMethods = "testCheckPositiveBuildIcon")
    public void testCheckSequenceInParameters() {
        final List<String> CurrentLocationItemsInDropDownMenu = new HomePage(getDriver())
                .projectMenuSelector(NEW_PIPELINE_NAME)
                .clickConfigureFromDropdownMenuAndGoToPipelineConfigPage()
                .clickCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickChoiceParameterButton()
                .enteringParametersIntoProject()
                .saveConfigAndGoToProject()
                .clickBuildWithParameters()
                .collectDropDownMenu();

        Assert.assertEquals(CurrentLocationItemsInDropDownMenu, List.of("This Is The Default Value", "2", "3"));
    }

    @Test(dependsOnMethods = "testCheckSequenceInParameters")
    public void testBuildPipelineWithParameters() {
        final List<String> checkNameAndDescriptionParametersBuild = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .clickBuildWithParameters()
                .clickBuildButton()
                .refreshPage()
                .clickLastBuildButton()
                .clickParametersButton()
                .collectChoiceAndDescriptionParameterName();

        Assert.assertEquals(checkNameAndDescriptionParametersBuild,
                List.of("Checking Name Display\nChecking Description Display"));
    }

    @Test(dependsOnMethods = "testDeletePipelineFromSideMenu")
    public void testPipelineCheckDiscardOld30builds() {
        final List<Integer> checkingDisplayLast30Builds = new HomePage(getDriver())
                .getSideMenu()
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
                .getSideMenu()
                .clickMyView()
                .moveToElement(PIPELINE_NAME)
                .clickMenuSelector()
                .clickInMenuSelectorConfigure()
                .enteringParametersToDisplayLatestBuilds("3", "1")
                .saveConfigAndGoToProject()
                .clickBuildPipelineButton()
                .waitForBuildNumber(32)
                .refreshPage()
                .getNumbersBuildsList();

        Assert.assertEquals(checkingDisplayLast3Builds, List.of(32, 31, 30));
    }

    @Test
    public void testAddAllColumnsFromDashboardInOwnWatchlist() {
        final String name = TestUtils.getRandomStr(7);
        final String viewName = "AchViewName";

        final int countColumns = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .getSideMenu()
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
                .getSideMenu()
                .clickEditView()
                .scrollPageDown()
                .removeColumns()
                .clickApplyAndOkAndGoToMyViewPage()
                .getCountOfColumns();

        Assert.assertEquals(countColumnsAfterDelete, 1);
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        final String checkTransitionPluginPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .scrollAndClickAdvancedButton()
                .clickHelpForFeatureDisplayName()
                .transitionToCorrectPage()
                .checkRedirectionPage();

        Assert.assertEquals(checkTransitionPluginPage, "Pipeline: Job");
    }

    @Test(dependsOnMethods = "testCheckLinkHelpMenuAdvancedProjectOptions")
    public void testDeletePipelineFromDashboard() {
        final boolean check = new HomePage(getDriver())
                .clickDashboardButton()
                .projectMenuSelector(PIPELINE_NAME)
                .clickDeleteProjectMenuSelector()
                .checkProjectAfterDelete(PIPELINE_NAME);

        Assert.assertTrue(check);
    }

    @Test
    public void test404PageAfterDeletedPipeline() {
        final String titleOfPage404 = new HomePage(getDriver())
                .getSideMenu()
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
    public void testCreateAndCheckNewMyView() {
        final int countJobs = 2;

        final List<String> listJobsInMyViewName = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(PIPELINE_NAME.concat("1"))
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .getSideMenu()
                .clickNewItem()
                .setProjectName(PIPELINE_NAME.concat("2"))
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .getSideMenu()
                .clickNewItem()
                .setProjectName(PIPELINE_NAME.concat("3"))
                .setProjectTypePipeline()
                .clickOkAndGoToConfig()
                .clickDashboardButton()
                .getSideMenu()
                .clickNewView()
                .setViewName(PIPELINE_NAME)
                .selectListViewType()
                .createViewAndGoConfig()
                .chooseJobs(countJobs)
                .clickApplyAndOkAndGoToMyViewPage()
                .getListJobsName();

        Assert.assertEquals(listJobsInMyViewName.size(), countJobs);
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
                .getSideMenu()
                .clickNewItem()
                .setProjectName(name)
                .getErrorMessage();

        Assert.assertEquals(errorText, String.format("» ‘%s’ is an unsafe character", name));
    }

    @Ignore
    @Test
    public void testDeleteAllPipelinesFromScriptConsole() {
        final String name = TestUtils.getRandomStr(7);

        final boolean check = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectTypePipeline()
                .setProjectName(name)
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .getSideMenu()
                .clickManageJenkins()
                .clickScriptConsole()
                .useDeleteAllProjectScript()
                .clickRunButton()
                .goHome()
                .checkProjectAfterDelete(name);

        Assert.assertTrue(check);
    }
}
