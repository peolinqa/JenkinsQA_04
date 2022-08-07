import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";
    private static final String EDIT_BUILD_NAME = TestUtils.getRandomStr(5);
    private static final String BUILD_DESCRIPTION = TestUtils.getRandomStr(5);

    private String buildName;

    @Test
    public void testBuildIsOnProjectPage() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectTypeFreestyle()
                .setProjectName(PROJECT_NAME)
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFreestyleProject()
                .getSideMenu()
                .clickMenuBuildNow();

        buildName = new FreestyleProjectPage(getDriver()).getBuildName();

        Assert.assertTrue(freestyleProjectPage.buildNumberIsDisplayed());
    }

    @Test(dependsOnMethods = "testBuildIsOnProjectPage")
    public void testBuildIsOnBuildHistoryPage() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory();

        Assert.assertTrue(buildHistoryPage.checkProjectIsOnBoard(PROJECT_NAME));
        Assert.assertTrue(buildHistoryPage.checkBuildIsOnBoard(buildName));
    }

    @Test(dependsOnMethods = "testBuildIsOnBuildHistoryPage")
    public void testBuildHistoryChanges() {
        String changesHeader = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory()
                .clickBuildName()
                .getSideMenu()
                .clickChangesAndGoToChangesPage()
                .getPageHeader();

        Assert.assertEquals(changesHeader, "Changes");
    }

    @Test(dependsOnMethods = "testBuildHistoryChanges")
    public void testBuildHistoryConsole() {
        String consoleHeader = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory()
                .clickBuildDropDownMenu(PROJECT_NAME, buildName.substring(1))
                .clickMenuDropDownConsole()
                .getPageHeader();

        Assert.assertEquals(consoleHeader, "Console Output");
    }

    @Test(dependsOnMethods = "testBuildHistoryConsole")
    public void testEditBuildInformation() {
        FreestyleBuildPage freestyleBuildPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory()
                .clickBuildName()
                .clickEditBuildInfoButton()
                .editBuildName(EDIT_BUILD_NAME)
                .editBuildDescription(BUILD_DESCRIPTION)
                .clickSaveButton();

        Assert.assertEquals(freestyleBuildPage.getBuildHeader(), EDIT_BUILD_NAME);
        Assert.assertEquals(freestyleBuildPage.getBuildDescription(), BUILD_DESCRIPTION);
    }

    @Test(dependsOnMethods = {"testEditBuildInformation"})
    public void testVerifyChangesOnProjectPage() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickFreestyleName(PROJECT_NAME);

        Assert.assertEquals(freestyleProjectPage.getBuildName(), EDIT_BUILD_NAME);
        Assert.assertEquals(freestyleProjectPage.getBuildDescription(), BUILD_DESCRIPTION);
    }
}
