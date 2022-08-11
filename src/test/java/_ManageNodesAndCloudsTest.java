import model.HomePage;
import model.ManageNodesAndCloudsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class _ManageNodesAndCloudsTest extends BaseTest {

    private static final String COMPUTER_NAME = "first test node 456";

    @Test
    public void testCheckBuildQueueAndClick() {
        HomePage homePage = new HomePage(getDriver());

        if (homePage.getSizeOfListForElementsBuildsInQueue() > 0) {
            Assert.assertEquals(homePage.getTitleBuildQueueToggleButton(), "collapse");

            homePage.clickBuildQueueToggleButton();
            Assert.assertEquals(homePage.getTitleBuildQueueToggleButton(), "expand");
        } else {
            Assert.assertEquals(homePage.getTitleBuildQueueToggleButton(), "expand");

            homePage.clickBuildQueueToggleButton();
            Assert.assertEquals(homePage.getTitleBuildQueueToggleButton(), "collapse");
        }
    }

    @Test
    public void testCheckBuildExecutorStatusAndClick() {
        HomePage homePage = new HomePage(getDriver());

        if (homePage.getSizeOfListForElementsBuildExecutorStatus() > 0) {
            Assert.assertEquals(homePage.getTitleBuildExecutorToggleButton(), "collapse");

            homePage.clickBuildExecutorToggleButton();
            Assert.assertEquals(homePage.getTitleBuildExecutorToggleButton(), "expand");
        } else {
            Assert.assertEquals(homePage.getTitleBuildExecutorToggleButton(), "expand");

            homePage.clickBuildExecutorToggleButton();
            Assert.assertEquals(homePage.getTitleBuildExecutorToggleButton(), "collapse");
        }
    }

    @Test
    public void testCreateNewNodeWithValidName() {
        List<String> computerNames = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageNodesAndClouds()
                .getSideMenu()
                .clickMenuNewNode()
                .createNewNodeWithPermanentAgentOption(COMPUTER_NAME)
                .clickSaveButton()
                .getComputerNames();

        Assert.assertTrue(computerNames.contains(COMPUTER_NAME));
    }

    @Test(dependsOnMethods = "testCreateNewNodeWithValidName")
    public void testCheckDeleteNode1() {
        List<String> computerNames = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageNodesAndClouds()
                .clickDropDownMenu(COMPUTER_NAME)
                .selectMenuDeleteAgentAndGoToManageNodesAndCloudsPage()
                .confirmDeleteAndGoManageNodesAndCloudsPage()
                .getComputerNames();

        Assert.assertFalse(computerNames.contains(COMPUTER_NAME));
    }
}
