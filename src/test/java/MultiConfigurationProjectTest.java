import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.Set;

public class MultiConfigurationProjectTest extends BaseTest {

    private static final String RANDOM_NAME = TestUtils.getRandomStr(5);
    private static final String EDITED_RANDOM_NAME = "New " + RANDOM_NAME;
    private static final String DESCRIPTION_TEXT = "This is a description for a Multi-ConfigurationProject";

    @Test
    public void testCreateMultiConfigProject() {
        String projectName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_NAME)
                .setMultiConfigurationProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .getProjectNameText();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testWorkspaceWithoutBuildPerformed")
    public void testBuildNow() {
        MultiConfigurationProjectPage consolePage = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuBuildNow()
                .clickIconBuildStatus();

        Assert.assertTrue(consolePage.tooltipStatusSuccessIsDisplayed());
    }

    @Test(dependsOnMethods = {"testCreateMultiConfigProject", "testBuildNow", "testBuildNowInDisabledProject", "testAddDescription"})
    public void testCheckSubMenuConfigureAfterCreatingProject() {
        final String discardOldBuildsText = "This determines when, if ever, build records for this project should be discarded. " +
                "Build records include the console output, archived artifacts, and any other metadata related " +
                "to a particular build.\n" +
                "Keeping fewer builds means less disk space will be used in the Build Record Root Directory," +
                " which is specified on the Configure System screen.\n" +
                "Jenkins offers two options for determining when builds should be discarded:\n" +
                "Build age: discard builds when they reach a certain age; for example, seven days old.\n" +
                "Build count: discard the oldest build when a certain number of builds already exist.\n" +
                "These two options can be active at the same time, so you can keep builds for 14 days, " +
                "but only up to a limit of 50 builds, for example. If either limit is exceeded, then any " +
                "builds beyond that limit will be discarded.\n" +
                "You can also ensure that important builds are kept forever, regardless of the " +
                "setting here — click the Keep this build forever button on the build page.\n" +
                "The last stable and last successful build are also excluded from these rules.\n" +
                "In the Advanced section, the same options can be specified, but specifically for build " +
                "artifacts. If enabled, build artifacts will be discarded for any builds which exceed the " +
                "defined limits. The builds themselves will still be kept; only the associated artifacts, " +
                "if any, will be deleted.\n" +
                "For example, if a project builds some software and produces a large installer, which is " +
                "archived, you may wish to always keep the console log and information about which source " +
                "control commit was built, while for disk space reasons, you may want to keep only " +
                "the last three installers that were built.\n" +
                "This can make sense for projects where you can easily recreate the same artifacts later by building " +
                "the same source control commit again.\n" +
                "Note that Jenkins does not discard items immediately when this configuration is updated, " +
                "or as soon as any of the configured values are exceeded; these rules are evaluated " +
                "each time a build of this project completes.";

        MultiConfigurationConfigPage newMultiConfigurationConfigPage = new HomePage(getDriver())
                .clickProjectDropDownMenu(RANDOM_NAME)
                .clickMenuSelectorMultiConfProjectConfigure();

        Assert.assertTrue(newMultiConfigurationConfigPage.isHelpButtonDiscardOldBuildsVisible());
        Assert.assertEquals(newMultiConfigurationConfigPage.getHelpDiscardOldBuildsAttribute("title"),
                "Help for feature: Discard old builds");
        Assert.assertEquals(newMultiConfigurationConfigPage.clickBtnHelpDiscardOldBuilds().getDiscardOldBuildsHiddenText(),
                discardOldBuildsText);
    }

    @Test(dependsOnMethods = "testBuildNow")
    public void testBuildNowInDisabledProject() {
        boolean BuildNowInDisabledProject = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickDisableProjectButton()
                .isMenuBuildNowDisplayed();

        Assert.assertFalse(BuildNowInDisabledProject);
    }

    @Test(dependsOnMethods = "testBuildNowInDisabledProject")
    public void testAddDescription() {
        String description = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickAddDescriptionButton()
                .setTextareaDescription(DESCRIPTION_TEXT)
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(description, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = {"testCheckSubMenuConfigureAfterCreatingProject"})
    public void testRenameMultiConfigurationProject() {
        String newProjectName = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuRename()
                .setNewProjectName(EDITED_RANDOM_NAME)
                .clickRename()
                .getProjectNameText();

        Assert.assertEquals(newProjectName, EDITED_RANDOM_NAME);
    }

    @Test(dependsOnMethods = {"testRenameMultiConfigurationProject"})
    public void testRenameMultiConfigurationProjectErrorSameName() {
        ErrorPage error = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .getSideMenu()
                .clickMenuRename()
                .clickRenameAndGoToErrorPage();

        Assert.assertEquals(error.getErrorHeaderText(), "Error");
        Assert.assertEquals(error.getErrorMessageText(), "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorSameName")
    public void testRenameMultiConfigurationProjectErrorEmptyName() {
        ErrorPage error = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .getSideMenu()
                .clickMenuRename()
                .setEmptyName();

        Assert.assertEquals(error.getErrorHeaderText(), "Error");
        Assert.assertEquals(error.getErrorMessageText(), "No name is specified");
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorEmptyName")
    public void testRenameMultiConfigurationProjectErrorInvalidName() {
        final String[] invalidName =
                new String[]{"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", "|", "<", ">", "?", "", " "};

        RenamePage<MultiConfigurationProjectPage, MultiConfigurationProjectPageSideMenuFrame> rename = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .getSideMenu()
                .clickMenuRename();

        for (String unsafeChar : invalidName) {
            rename.setNewProjectName(unsafeChar)
                    .clickRenameAndGoToErrorPage();

            String expectedResult = "‘" + unsafeChar + "’ is an unsafe character";
            switch (unsafeChar) {
                case "&":
                    expectedResult = "‘&amp;’ is an unsafe character";
                    break;
                case "<":
                    expectedResult = "‘&lt;’ is an unsafe character";
                    break;
                case ">":
                    expectedResult = "‘&gt;’ is an unsafe character";
                    break;
                case "":
                case " ":
                    expectedResult = "No name is specified";
                    break;
            }

            ErrorPage errorPage = new ErrorPage(getDriver());

            Assert.assertEquals(errorPage.getErrorMessageText(), expectedResult);

            getDriver().navigate().back();
        }
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorInvalidName")
    public void testDeleteMultiConfigFolder() {
        HomePage homePage = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .getSideMenu()
                .clickMenuDeleteProject();

        Assert.assertFalse(homePage.isItemPresent(EDITED_RANDOM_NAME));
    }

    @Test(dependsOnMethods = "testCreateMultiConfigProject")
    public void testWorkspaceWithoutBuildPerformed() {
        final String h1HeaderError = "Error: no workspace";
        final Set<String> expectedErrorsText = Set.of(
                "A project won't have any workspace until at least one build is performed.",
                "Run a build to have Jenkins create a workspace.");

        MultiConfigurationProjectWorkspacePage errorMessages = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickLinkDefault()
                .clickLinkWorkspace();

        Assert.assertEquals(errorMessages.getH1Header(), h1HeaderError);
        Assert.assertEquals(errorMessages.getErrorMessagesList(), expectedErrorsText);
    }

}
