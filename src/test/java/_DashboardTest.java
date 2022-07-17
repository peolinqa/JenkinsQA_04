import model.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;
import java.util.List;
import java.util.stream.Collectors;

public class _DashboardTest extends BaseTest {

    private static final By XPATH_DISAPPEARING_BUTTON = By.xpath("//div[@id='menuSelector']");
    private static final String DASHBOARD_XPATH = "//a[contains(text(),'Dashboard')]";

    private static final List<String> EXPECTED_ICONS_DESCRIPTIONS = List.of(
            "The project has never been built.", "The first build is in progress.",
            "The project is disabled.", "The project is disabled, but a build is in progress.",
            "The last build was aborted.", "The last build was aborted. A new build is in progress.",
            "The last build was successful.", "The last build was successful. A new build is in progress.",
            "The last build was successful but unstable. This is primarily used to represent test failures.",
            "The last build was successful but unstable. A new build is in progress.",
            "The last build failed.",
            "The last build failed. A new build is in progress.",
            "Project health is over 80%. You can hover the mouse over the project’s icon for a more detailed explanation.", "" +
                    "Project health is over 60% and up to 80%. You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is over 40% and up to 60%. You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is over 20% and up to 40%. You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is 20% or less. You can hover the mouse over the project’s icon for a more detailed explanation.");

    private static final List<String> EXPECTED_ITEMS = List.of(
            "New Item",
            "People",
            "Build History",
            "Manage Jenkins",
            "My Views",
            "Lockable Resources",
            "New View");

    @Test
    /*
     * To pass this test you should have "Lockable Resources" plugin installed on local machine
     * */
    public void testCommonCheckDropDownMenu() {
        WebElement dashboard = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DASHBOARD_XPATH)));
        getActions().moveToElement(dashboard).build().perform();

        WebElement button = getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_DISAPPEARING_BUTTON));
        getActions().moveToElement(button).click().build().perform();

        List<String> actualItems = getDriver().findElements(By.xpath("//ul[@class='first-of-type']/li"))
                .stream()
                .filter(s -> !s.getText().isEmpty())
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(actualItems, EXPECTED_ITEMS);
    }

    @Test
    public void testCheckVisibleAndEnabledLinkIconLegend() {
        HomePage homePage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(TestUtils.getRandomStr(10))
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFolderPage()
                .goHome();

        Assert.assertTrue(homePage.isVisibleIconLegend());
        Assert.assertTrue(homePage.isEnabledIconLegend());
    }

    @Test(dependsOnMethods = "testCheckVisibleAndEnabledLinkIconLegend")
    public void checkIconDescription() {
        List<String> actualIconsDescriptions = new HomePage(getDriver()).clickLinkIconLegend().getTextIconsDescriptions();

        Assert.assertEquals(actualIconsDescriptions, EXPECTED_ICONS_DESCRIPTIONS);
    }
}