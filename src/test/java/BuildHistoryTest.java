import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";
    private static final String EDIT_BUILD_NAME = TestUtils.getRandomStr(5);
    private static final String BUILD_DESCRIPTION = TestUtils.getRandomStr(5);

    private String buildName;

    @Test
    public void testBuildIsOnProjectPage() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setFreestyleProjectType()
                .setProjectName(PROJECT_NAME)
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .getSideMenu()
                .clickMenuBuildNow();

        buildName = new FreestyleProjectPage(getDriver()).getTextBuildName();

        Assert.assertTrue(freestyleProjectPage.buildNumberIsDisplayed());
    }

    @Test(dependsOnMethods = "testBuildIsOnProjectPage")
    public void testBuildIsOnBuildHistoryPage() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory();

        Assert.assertTrue(buildHistoryPage.isProjectOnBoardBuildHistory(PROJECT_NAME));
        Assert.assertTrue(buildHistoryPage.isBuildOnBoardBuildHistory(buildName));
    }

    @Test(dependsOnMethods = "testBuildIsOnBuildHistoryPage")
    public void testBuildHistoryChanges() {
        String changesHeader = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory()
                .clickLinkBuildLastCreated()
                .getSideMenu()
                .clickMenuChanges()
                .getBuildHeaderText();

        Assert.assertEquals(changesHeader, "Changes");
    }

    @Test(dependsOnMethods = "testBuildHistoryChanges")
    public void testBuildHistoryConsoleOutput() {
        String consoleHeader = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory()
                .clickBuildDropDownMenu(PROJECT_NAME, buildName.substring(1))
                .clickMenuSelectorConsoleOutput()
                .getBuildHeaderText();

        Assert.assertEquals(consoleHeader, "Console Output");
    }

    @Test(dependsOnMethods = "testBuildHistoryConsoleOutput")
    public void testEditBuildInformation() {
        FreestyleBuildPage freestyleBuildPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory()
                .clickLinkBuildLastCreated()
                .getSideMenu()
                .clickEditBuildInfoButton()
                .editBuildName(EDIT_BUILD_NAME)
                .editBuildDescription(BUILD_DESCRIPTION)
                .clickSaveButton();

        Assert.assertEquals(freestyleBuildPage.getBuildHeaderName(), EDIT_BUILD_NAME);
        Assert.assertEquals(freestyleBuildPage.getBuildDescriptionText(), BUILD_DESCRIPTION);
    }

    @Test(dependsOnMethods = {"testEditBuildInformation"})
    public void testVerifyBuildInformationOnPaneBuildHistory() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickFreestyleName(PROJECT_NAME);

        Assert.assertEquals(freestyleProjectPage.getTextBuildName(), EDIT_BUILD_NAME);
        Assert.assertEquals(freestyleProjectPage.getTextBuildDescription(), BUILD_DESCRIPTION);
    }
}
