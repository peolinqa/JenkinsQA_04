import model.FreestyleProjectPage;
import model.HomePage;
import model.LastBuildPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class _BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";
    private static final String BUILD_PROJECT_NAME = "NewFreestyleProject";
    private static final String BUILD_NAME = "New build 123";
    private static final String BUILD_DESCRIPTION = "Build 123 description test";

    private String buildNumber;

    @Test
    public void testBuildIsOnProjectPage() {
        boolean buildNumberIsDisplayed = new HomePage(getDriver())
                .clickNewItem()
                .setProjectTypeFreestyle()
                .setProjectName(PROJECT_NAME)
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFreestyleProject()
                .clickBuildButton()
                .buildNumberIsDisplayed();

        buildNumber = new  FreestyleProjectPage(getDriver()).getBuildNumber();

        Assert.assertTrue(buildNumberIsDisplayed);
    }

    @Test(dependsOnMethods = "testBuildIsOnProjectPage")
    public void testBuildIsOnBuildHistoryPage() {
        boolean result = new HomePage(getDriver())
                .clickBuildHistory()
                .checkProjectIsOnBoard(PROJECT_NAME);

        Assert.assertTrue(result);
    }

    @Test(dependsOnMethods = "testBuildIsOnBuildHistoryPage")
    public void testBuildHistoryChanges() {
        String changesHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildNumber)
                .clickChangesAndGoToChangesPage()
                .getPageHeader();

        Assert.assertEquals(changesHeader, "Changes");
    }

    @Test (dependsOnMethods = "testBuildHistoryChanges")
    public void testBuildHistoryConsole() {
        String consoleHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildNumber)
                .clickConsoleAndGoToConsolePage()
                .getPageHeader();

        Assert.assertEquals(consoleHeader, "Console Output");
    }

    @Test
    public void testVerifyChangeOnBuildStatusPage() {
        String buildName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(BUILD_PROJECT_NAME)
                .setProjectTypeFreestyle()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFreestyleProject()
                .clickBuildButton()
                .waitForBuildToComplete()
                .clickDashboardButton()
                .clickFreestyleName(BUILD_PROJECT_NAME)
                .selectLastBuild()
                .clickEditBuildInfoButton()
                .enterBuildName(BUILD_NAME)
                .enterBuildDescription(BUILD_DESCRIPTION)
                .clickSaveButton()
                .getBuildName();

        String buildDescription = new LastBuildPage(getDriver())
                .getBuildDescription();

        Assert.assertEquals(buildName, BUILD_NAME);
        Assert.assertEquals(buildDescription, BUILD_DESCRIPTION);
    }

    @Test(dependsOnMethods = {"testVerifyChangeOnBuildStatusPage"})
    public void testVerifyChangeOnProjectStatusPage() {
        String buildName = new HomePage(getDriver())
                .clickFreestyleName(BUILD_PROJECT_NAME)
                .selectLastBuild()
                .clickBackToProjectButton()
                .getBuildName();

        String descriptionName = new FreestyleProjectPage(getDriver())
                .getBuildDescription();

        Assert.assertEquals(buildName,BUILD_NAME);
        Assert.assertEquals(descriptionName,BUILD_DESCRIPTION);
    }

    @Test(dependsOnMethods = {"testVerifyChangeOnProjectStatusPage"})
    public void testVerifyChangeOnBuildHistoryPage() {
        String buildNameChange = new HomePage(getDriver())
                .clickFreestyleName(BUILD_PROJECT_NAME)
                .selectLastBuild()
                .clickBackToProjectButton()
                .clickBackToDashboard()
                .clickBuildHistory()
                .getBuildName();

        Assert.assertEquals(buildNameChange,BUILD_NAME);
    }
}