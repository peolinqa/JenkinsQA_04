import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
                .clickMenuNewItem()
                .setProjectName(PIPELINE_NAME)
                .setPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(PIPELINE_NAME)
                .setPipelineProjectType()
                .getErrorMsgNameInvalidText();

        Assert.assertEquals(displayDuplicatedJobName, String.format("» A job already exists with the name ‘%s’", PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCheckValidationItemName")
    public void testCheckTransitionToPageWithError() {
        final boolean isErrorHeaderDisplayed = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(PIPELINE_NAME)
                .setPipelineProjectType()
                .clickBtnOkAndGoToErrorPage()
                .isDisplayedErrorHeader();

        Assert.assertTrue(isErrorHeaderDisplayed);
    }

    @Test(dependsOnMethods = "testCheckTransitionToPageWithError")
    public void testCheckDropDownMenuPipeline() {
        final int checkDisplayedDropDownList = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .collectDropDownMenu();

        Assert.assertEquals(checkDisplayedDropDownList, 4);
    }

    @Test(dependsOnMethods = "testCheckDropDownMenuPipeline")
    public void testJenkinsCredentialsProviderWindow() {
        final String titleOfJenkinsCredentialsProviderWindow = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
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
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .selectConfigurationMenuDefinition("Pipeline")
                .getHrefAndGoToPipelineSyntaxPage()
                .getPipelineSyntaxHrefAtt();

        Assert.assertTrue(hrefAttOfPipelineSyntaxLink.contains("pipeline-syntax"));
    }

    @Test(dependsOnMethods = "testPipelineSyntaxPageOpening")
    public void testPipelineGroovyPageOpening() {
        final String useGroovySandBoxCheckboxAtt = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .getUseGroovySandBoxCheckboxAtt();

        Assert.assertEquals(useGroovySandBoxCheckboxAtt, "true");
    }

    @Test(dependsOnMethods = "testPipelineGroovyPageOpening")
    public void testTitleConfigPageContainsProjectTitle() {
        final String titleConfigPage = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .getTitleConfigPage();

        Assert.assertTrue(titleConfigPage.contains(PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testTitleConfigPageContainsProjectTitle")
    public void testCreatePipelineAndCheckOnDashboard() {
        final List<String> actualDashboardProject = new HomePage(getDriver())
                .clickLinkDashboard()
                .getProjectsOnDashboardList();

        Assert.assertTrue(actualDashboardProject.contains(PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCreatePipelineAndCheckOnDashboard")
    public void testHelpTooltipsText() {
        final boolean check = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .checkHelpTooltipsTextCheckBoxHelpText();

        Assert.assertTrue(check);
    }

    @Test(dependsOnMethods = "testHelpTooltipsText")
    public void testApplyButtonNotificationAlert() {
        final String notificationSave = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .applyButtonClick()
                .notification();

        Assert.assertEquals(notificationSave, "Saved");
    }

    @Test(dependsOnMethods = "testApplyButtonNotificationAlert")
    public void testCreatePipelineWithNegativeValueQuietPeriod() {
        final String checkForValueErrorMessage = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .jsCheckboxProjectParameterized()
                .enteringDataIntoLineQuietPeriod()
                .verificationPeriodErrorMessage();

        Assert.assertEquals(checkForValueErrorMessage, "This value should be larger than 0");
    }

    @Test(dependsOnMethods = "testCreatePipelineWithNegativeValueQuietPeriod")
    public void testPermalinksTextAfterPipelineBuildNow() {
        final String[] permalinksText = new HomePage(getDriver())
                .clickPipelineName(PIPELINE_NAME)
                .getSideMenu()
                .clickMenuBuildPipelineButton()
                .clickBuildNameWait()
                .refreshPage()
                .getPermalinksTextArray();

        Assert.assertEquals(permalinksText, new String[]{"Last build", "Last stable build",
                "Last successful build", "Last completed build"});
    }

    @Test(dependsOnMethods = "testPermalinksTextAfterPipelineBuildNow")
    public void testDragAndDropProjectParameters() {
        final List<String> locationProjectParameterized = new HomePage(getDriver())
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
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
                .clickAddDescriptionButton()
                .addTextDescriptionAndSave("Text Description")
                .clearProjectDescription()
                .isDescriptionValueEmpty();

        Assert.assertTrue(check);
    }

    @Test(dependsOnMethods = "testDeletePipelineDescription")
    public void testRenamePipelineWithInvalidName() {
        final List<String> invalidCharacters = Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\",
                "/", ">", "<", "|", "?");

        final List<String> listErrorMessages = new HomePage(getDriver())
                .clickPipelineName(PIPELINE_NAME)
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage()
                .getErrorMessagesList(invalidCharacters);

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
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage()
                .setNewProjectName(NEW_PIPELINE_NAME)
                .clickRename()
                .getProjectNameText();

        Assert.assertEquals(newProjectName, NEW_PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testRenamePipelineWithValidName")
    public void testRenamedProjectIsOnDashboard() {
        final boolean projectIsDisplayed = new HomePage(getDriver())
                .isProjectNamePresent(NEW_PIPELINE_NAME);

        Assert.assertTrue(projectIsDisplayed);
    }

    @Test(dependsOnMethods = "testRenamedProjectIsOnDashboard")
    public void testRenamePipelineTheSameNameWithAllCapitalLetters() {
        final String errorText = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage()
                .setNewProjectName(NEW_PIPELINE_NAME.toUpperCase())
                .clickRenameAndGoToErrorPage()
                .getErrorMessageText();

        Assert.assertEquals(errorText, String.format("The name “%s” is already in use.", NEW_PIPELINE_NAME.toUpperCase()));
    }

    @Test(dependsOnMethods = "testRenamePipelineTheSameNameWithAllCapitalLetters")
    public void testRenamePipelineWithTheSameName() {
        final String errorText = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage()
                .clickRenameAndGoToErrorPage()
                .getErrorMessageText();

        Assert.assertEquals(errorText, "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenamePipelineWithTheSameName")
    public void testDeletePipelineFromSideMenu() {
        final boolean check = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .getSideMenu()
                .clickMenuDeleteProjectAndConfirm()
                .clickLinkDashboard()
                .isProjectPresentAfterDelete(NEW_PIPELINE_NAME);

        Assert.assertTrue(check);
    }

    @Test(dependsOnMethods = {"testCheckValidationItemName", "testDeletePipelineFromSideMenu"})
    public void testCheckScheduledBuildInBuildHistory() {
        final List<String> checkBuildHistoryByName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(NEW_PIPELINE_NAME)
                .setPipelineProjectType()
                .clickOkAndGoToConfig()
                .jsDropDownMenuPipelineTab()
                .clickDropDownMenuPipelineTab()
                .saveConfigAndGoToProject()
                .clickLinkDashboard()
                .buildSelectPipeline(NEW_PIPELINE_NAME, true)
                .getSideMenu()
                .clickMenuBuildHistory()
                .collectListBuildHistory(NEW_PIPELINE_NAME);

        Assert.assertEquals(checkBuildHistoryByName, List.of(NEW_PIPELINE_NAME, NEW_PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCheckScheduledBuildInBuildHistory")
    public void testCheckPositiveBuildIcon() {
        final boolean isStatus = new HomePage(getDriver())
                .clickLinkDashboard()
                .buildSelectPipeline(NEW_PIPELINE_NAME, false)
                .clickRefreshPage()
                .clickPipelineName(NEW_PIPELINE_NAME)
                .refreshPage()
                .isProjectStatusSuccess("job SUCCESS");

        Assert.assertTrue(isStatus);
    }

    @Test(dependsOnMethods = "testCheckPositiveBuildIcon")
    public void testCheckSequenceInParameters() {
        final List<String> CurrentLocationItemsInDropDownMenu = new HomePage(getDriver())
                .moveToProjectName(NEW_PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .clickCheckboxProjectParameterized()
                .clickAddParameterOfBuildButton()
                .clickChoiceParameterButton()
                .enteringParametersIntoProject()
                .saveConfigAndGoToProject()
                .getSideMenu()
                .clickMenuBuildWithParameters()
                .collectDropDownMenu();

        Assert.assertEquals(CurrentLocationItemsInDropDownMenu, List.of("This Is The Default Value", "2", "3"));
    }

    @Test(dependsOnMethods = "testCheckSequenceInParameters")
    public void testBuildPipelineWithParameters() {
        final List<String> listOfNameAndDescriptionBuildParameters = new HomePage(getDriver())
                .clickPipelineName(NEW_PIPELINE_NAME)
                .getSideMenu()
                .clickMenuBuildWithParameters()
                .clickBuildButton()
                .refreshPage()
                .clickLinkLastBuild()
                .clickMenuParameters()
                .collectListBuildParameters();

        Assert.assertEquals(listOfNameAndDescriptionBuildParameters,
                List.of("Checking Name Display\nChecking Description Display"));
    }

    @Test(dependsOnMethods = "testDeletePipelineFromSideMenu")
    public void testPipelineCheckDiscardOld30builds() {
        final List<Integer> checkingDisplayLast30Builds = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(PIPELINE_NAME)
                .setPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickCheckBoxDiscardOldBuilds()
                .saveConfigAndGoToProject()
                .getSideMenu()
                .clickMenuBuildMultipleTimes(31)
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
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuMyView()
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorPipelineConfigure()
                .enteringParametersToDisplayLatestBuilds("3", "1")
                .saveConfigAndGoToProject()
                .getSideMenu()
                .clickMenuBuildPipelineButton()
                .waitForBuildNumber(32)
                .refreshPage()
                .getNumbersBuildsList();

        Assert.assertEquals(checkingDisplayLast3Builds, List.of(32, 31, 30));
    }

    @Test
    public void testCheckLinkHelpMenuAdvancedProjectOptions() {
        final String checkTransitionPluginPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(PIPELINE_NAME)
                .setPipelineProjectType()
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
                .clickLinkDashboard()
                .moveToProjectName(PIPELINE_NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorDelete()
                .isProjectPresentAfterDelete(PIPELINE_NAME);

        Assert.assertTrue(check);
    }

    @Test
    public void test404PageAfterDeletedPipeline() {
        final String titleOfPage404 = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(PIPELINE_NAME)
                .setPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickLinkDashboard()
                .navigateToPreviousCreatedPipeline(PIPELINE_NAME)
                .getSideMenu()
                .clickMenuDeleteProjectAndConfirm()
                .switchToPage404()
                .getTitleOfPage404();

        Assert.assertEquals(titleOfPage404, "Error 404 Not Found");
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
                .clickMenuNewItem()
                .setProjectName(name)
                .getErrorMessageItemNameInvalid();

        Assert.assertEquals(errorText, String.format("» ‘%s’ is an unsafe character", name));
    }

    @Test
    public void testDeleteAllPipelinesFromScriptConsole() {
        final String name = TestUtils.getRandomStr(7);

        final boolean check = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setPipelineProjectType()
                .setProjectName(name)
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickScriptConsole()
                .useDeleteAllProjectScript()
                .clickRunButton()
                .goHome()
                .isProjectPresentAfterDelete(name);

        Assert.assertTrue(check);
    }
}
