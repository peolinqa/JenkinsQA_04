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
        List<String> textComputerNames = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageNodesAndClouds()
                .newNodeButtonClick()
                .createNewNodeWithPermanentAgentOption(COMPUTER_NAME)
                .clickSaveButton()
                .getTextComputerNamesFromTable();

        Assert.assertTrue(textComputerNames.contains(COMPUTER_NAME));
    }


    @Test(dependsOnMethods = "testCreateNewNodeWithValidName")
    public void testCheckDeleteNode() {
        ManageNodesAndCloudsPage manageNodesAndCloudsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageNodesAndClouds();

        List<WebElement> computerNames = manageNodesAndCloudsPage.getComputerNames();

        for (WebElement name : computerNames) {
            if (name.getText().equals(COMPUTER_NAME)) {
                manageNodesAndCloudsPage
                        .menuSelectorHiddenButtonClick(name)
                        .chooseDeleteMenuAfterClickMenuSelector(name)
                        .confirmToDeleteComputerNode();
                break;
            }
        }

        List<String> computerNamesText = manageNodesAndCloudsPage.getTextComputerNamesFromTable();
        Assert.assertFalse(computerNamesText.contains(COMPUTER_NAME));
    }
}
