import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;
import java.util.Set;

public class _MultiConfigurationProjectTest extends BaseTest {

    private static final String RANDOM_NAME = TestUtils.getRandomStr(5);
    private static final String EDITED_RANDOM_NAME = "New " + RANDOM_NAME;
    private static final String DESCRIPTION_TEXT = "This is a description for a Multi-ConfigurationProject";

    @Test
    public void testCreateMultiConfigFolder() {
        String projectName = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(RANDOM_NAME)
                .setProjectTypeMultiConfiguration()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .getProjectName();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testWorkspaceWithoutBuildPerformed")
    public void testBuildNow() {
        MultiConfigurationProjectConsolePage consolePage = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickBuildNow()
                .clickTooltipStatus();

        Assert.assertTrue(consolePage.tooltipStatusSuccessIsDisplayed());
    }

    @Test(dependsOnMethods = {"testCreateMultiConfigFolder", "testBuildNow", "testBuildNowInDisabledProject", "testAddDescription"})
    public void testCheckSubMenuConfigureAfterCreatingProject() {
        final String DiscardOldBuildsText = "This determines when, if ever, build records for this project should be discarded. " +
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
                .projectMenuSelector(RANDOM_NAME)
                .clickConfigureFromDropdownMenuAndGoToMultiConfigurationConfig();

        Assert.assertTrue(newMultiConfigurationConfigPage.helpButtonDiscardOldBuildsIsVisible());
        Assert.assertEquals(newMultiConfigurationConfigPage.getAttributeHelpButtonDiscardOldBuilds("title"),
                "Help for feature: Discard old builds");
        Assert.assertEquals(newMultiConfigurationConfigPage.clickHelpButtonDiscardOldBuilds().getTextDiscardOldBuildsHiddenTextArea(),
                DiscardOldBuildsText);
    }

    @Test(dependsOnMethods = "testBuildNow")
    public void testBuildNowInDisabledProject() {
        boolean BuildNowInDisabledProject = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickDisableProjectButton()
                .isDisplayedBuildNowButton();

        Assert.assertFalse(BuildNowInDisabledProject);
    }

    @Test(dependsOnMethods = "testBuildNowInDisabledProject")
    public void testAddDescription() {
        String description = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickAddDescription()
                .setDescription(DESCRIPTION_TEXT)
                .saveConfigAndGoToMultiConfigurationProject()
                .getDescription();

        Assert.assertEquals(description, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = {"testCheckSubMenuConfigureAfterCreatingProject"})
    public void testRenameMultiConfigurationProject() {
        String newProjectName = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickRenameAndGoToRenamePage()
                .setNewProjectName(EDITED_RANDOM_NAME)
                .clickRenameAndGoToProjectPage()
                .getProjectName();

        Assert.assertEquals(newProjectName, EDITED_RANDOM_NAME);
    }

    @Test(dependsOnMethods = {"testRenameMultiConfigurationProject"})
    public void testRenameMultiConfigurationProjectErrorSameName() {
        ErrorPage error = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .clickRenameAndGoToRenamePage()
                .setInvalidNameAndGoToErrorPage();

        Assert.assertEquals(error.getErrorHeader(), "Error");
        Assert.assertEquals(error.getErrorMessage(), "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorSameName")
    public void testRenameMultiConfigurationProjectErrorEmptyName() {
        ErrorPage error = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .clickRenameAndGoToRenamePage()
                .setEmptyNameAndGoToErrorPage();

        Assert.assertEquals(error.getErrorHeader(), "Error");
        Assert.assertEquals(error.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorEmptyName")
    public void testRenameMultiConfigurationProjectErrorInvalidName() {
        final String[] invalidName =
                new String[]{"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", "|", "<", ">", "?", "", " "};

        RenamePage<MultiConfigurationProjectPage> rename = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .clickRenameAndGoToRenamePage();

        for (String unsafeChar : invalidName) {
            rename.setNewProjectName(unsafeChar)
                    .setInvalidNameAndGoToErrorPage();

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

            Assert.assertEquals(errorPage.getErrorMessage(), expectedResult);

            getDriver().navigate().back();
        }
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorInvalidName")
    public void testDeleteMultiConfigFolder() {
        HomePage homePage = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .clickDeleteProjectAndConfirm();

        Assert.assertFalse(homePage.isItemPresent(EDITED_RANDOM_NAME));
    }

    @Test(dependsOnMethods = "testCreateMultiConfigFolder")
    public void testWorkspaceWithoutBuildPerformed() {
        final String h1HeaderError = "Error: no workspace";
        final Set<String> expectedErrorsText = Set.of(
                "A project won't have any workspace until at least one build is performed.",
                "Run a build to have Jenkins create a workspace.");

        MultiConfigurationProjectWorkspacePage errorMessages = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(RANDOM_NAME)
                .clickDefaultButton()
                .clickWorkspaceButton();

        Assert.assertEquals(errorMessages.getH1Header(), h1HeaderError);
        Assert.assertEquals(errorMessages.getErrorMessages(), expectedErrorsText);
    }
}
