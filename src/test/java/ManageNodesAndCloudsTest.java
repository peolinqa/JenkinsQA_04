import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class ManageNodesAndCloudsTest extends BaseTest {

    private static final String COMPUTER_NAME = "first test node 456";

    @Test
    public void testCheckBuildQueueAndClick() {
        HomePage homePage = new HomePage(getDriver());

        if (homePage.getSizeOfListElementsBuildsInQueue() > 0) {
            Assert.assertEquals(homePage.getBuildQueueToggleAttrTitle(), "collapse");

            homePage.clickBuildQueueToggleButton();
            Assert.assertEquals(homePage.getBuildQueueToggleAttrTitle(), "expand");
        } else {
            Assert.assertEquals(homePage.getBuildQueueToggleAttrTitle(), "expand");

            homePage.clickBuildQueueToggleButton();
            Assert.assertEquals(homePage.getBuildQueueToggleAttrTitle(), "collapse");
        }
    }

    @Test
    public void testCheckBuildExecutorStatusAndClick() {
        HomePage homePage = new HomePage(getDriver());

        if (homePage.getSizeOfListElementsBuildExecutorStatus() > 0) {
            Assert.assertEquals(homePage.getBuildExecutorToggleAttrTitle(), "collapse");

            homePage.clickBuildExecutorToggleButton();
            Assert.assertEquals(homePage.getBuildExecutorToggleAttrTitle(), "expand");
        } else {
            Assert.assertEquals(homePage.getBuildExecutorToggleAttrTitle(), "expand");

            homePage.clickBuildExecutorToggleButton();
            Assert.assertEquals(homePage.getBuildExecutorToggleAttrTitle(), "collapse");
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
                .getComputerNamesList();

        Assert.assertTrue(computerNames.contains(COMPUTER_NAME));
    }

    //todo: fix assert
    @Test(dependsOnMethods = "testCreateNewNodeWithValidName")
    public void testCheckDeleteNode1() {
        List<String> computerNames = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageNodesAndClouds()
                .clickDropDownMenu(COMPUTER_NAME)
                .clickMenuSelectorDeleteAgent()
                .confirmDelete()
                .getComputerNamesList();

        Assert.assertFalse(computerNames.contains(COMPUTER_NAME));
    }
}
