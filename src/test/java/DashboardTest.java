import model.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.List;

public class DashboardTest extends BaseTest {

    private static final List<String> EXPECTED_ICONS_DESCRIPTIONS = List.of(
            "The project has never been built.", "The first build is in progress.",
            "The project is disabled.", "The project is disabled, but a build is in progress.",
            "The last build was aborted.", "The last build was aborted. A new build is in progress.",
            "The last build was successful.", "The last build was successful. A new build is in progress.",
            "The last build was successful but unstable. This is primarily used to represent test failures.",
            "The last build was successful but unstable. A new build is in progress.",
            "The last build failed.",
            "The last build failed. A new build is in progress.",
            "Project health is over 80%. " +
                    "You can hover the mouse over the project’s icon for a more detailed explanation.", "" +
            "Project health is over 60% and up to 80%. " +
                    "You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is over 40% and up to 60%. " +
                    "You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is over 20% and up to 40%. " +
                    "You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is 20% or less. " +
                    "You can hover the mouse over the project’s icon for a more detailed explanation.");

    @Test
    /**
     To pass this test you should have "Lockable Resources" plugin installed on local machine
     */

    public void testCommonCheckDropDownMenu() {
        List<String> expectedListOfDashboardDropdownMenuElements = List.of(
                "New Item",
                "People",
                "Build History",
                "Manage Jenkins",
                "My Views",
                "Lockable Resources",
                "New View");

        List<String> listOfDashboardDropdownMenuElements = new HomePage(getDriver())
                .clickDashboardDropdownMenu()
                .getListOfDashboardDropdownMenuElements();

        Assert.assertEquals(listOfDashboardDropdownMenuElements, expectedListOfDashboardDropdownMenuElements);
    }

    @Test
    public void testCheckVisibleAndEnabledLinkIconLegend() {
        HomePage homePage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(TestUtils.getRandomStr(10))
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .goHome();

        Assert.assertTrue(homePage.isVisibleIconLegend());
        Assert.assertTrue(homePage.isEnabledIconLegend());
    }

    @Test(dependsOnMethods = "testCheckVisibleAndEnabledLinkIconLegend")
    public void checkIconDescription() {
        List<String> actualIconsDescriptions = new HomePage(getDriver())
                .clickLinkIconLegend()
                .getIconDescriptionsText();

        Assert.assertEquals(actualIconsDescriptions, EXPECTED_ICONS_DESCRIPTIONS);
    }
}
