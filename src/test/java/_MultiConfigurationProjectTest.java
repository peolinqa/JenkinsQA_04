import model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.List;

import static runner.ProjectUtils.ProjectType.MultiConfigurationProject;

public class _MultiConfigurationProjectTest extends BaseTest {

    private static final String NAME_TO_DELETE = "TestToDelete";
    private static final String RANDOM_NAME = TestUtils.getRandomStr(5);
    private static final String EDITED_RANDOM_NAME = "New " + RANDOM_NAME;
    private static final String DESCRIPTION_TEXT = "This is a description for a Multi-ConfigurationProject";

    @Test
    public void testCreateMultiConfigFolder() {
        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_NAME)
                .setProjectTypeMultiConfiguratio()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .getProjectName();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigFolder")
    public void testBuildNow() {
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
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
                .clickAdnGoToRenamePage()
                .setNewProjectName(EDITED_RANDOM_NAME)
                .clickRenameAndGoToMultiConfigurationProject()
                .getProjectName();

        Assert.assertEquals(newProjectName, EDITED_RANDOM_NAME);
    }

    @Test
    public void testRenameMultiConfigurationProjectErrorSameName() {
        ErrorPage error = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .clickAdnGoToRenamePage()
                .setInvalidNameAndGoToErrorPage();

        Assert.assertEquals(error.getErrorHeader(), "Error");
        Assert.assertEquals(error.getErrorMessage(), "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorSameName")
    public void testRenameMultiConfigurationProjectErrorEmptyName() {
        ErrorPage error = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .clickAdnGoToRenamePage()
                .setEmptyNameAndGoToErrorPage();

        Assert.assertEquals(error.getErrorHeader(), "Error");
        Assert.assertEquals(error.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectErrorSameName")
    public void testRenameMultiConfigurationProjectErrorInvalidName() {
        final String[] invalidName =
                new String[]{"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", "|", "<", ">", "?", "", " "};

        RenamePage rename = new HomePage(getDriver())
                .clickMultiConfigurationProjectName(EDITED_RANDOM_NAME)
                .clickAdnGoToRenamePage();

        for (String unsafeChar : invalidName) {
            rename.setNewProjectName(unsafeChar)
                    .setInvalidNameAndGoToErrorPage();

            String expectedResult = "‘" + unsafeChar + "’ is an unsafe character";
            if ("&" == unsafeChar) {
                expectedResult = "‘&amp;’ is an unsafe character";
            } else if (unsafeChar == "<") {
                expectedResult = "‘&lt;’ is an unsafe character";
            } else if (unsafeChar == ">") {
                expectedResult = "‘&gt;’ is an unsafe character";
            } else if (unsafeChar == "" || unsafeChar == " ") {
                expectedResult = "No name is specified";
            }

            ErrorPage errorPage = new ErrorPage(getDriver());

            Assert.assertEquals(errorPage.getErrorMessage(), expectedResult);

            getDriver().navigate().back();
        }
    }

    @Test
    public void testDeleteMultiConfigFolder() {
        ProjectUtils.createProject(getDriver(), MultiConfigurationProject, NAME_TO_DELETE);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.openProject(getDriver(), NAME_TO_DELETE);
        ProjectUtils.Dashboard.Project.DeleteMultiConfigurationProject.click(getDriver());
        getDriver().switchTo().alert().accept();

        boolean isPresent = false;
        List<WebElement> projectsOnDashboard = TestUtils.getList(getDriver(),
                By.xpath("//table[@id='projectstatus']//tbody//td[3]"));
        for (WebElement jobs : projectsOnDashboard) {
            if (jobs.getText().contains(NAME_TO_DELETE)) {
                isPresent = true;
            }
        }
        Assert.assertFalse(isPresent);
    }
}

