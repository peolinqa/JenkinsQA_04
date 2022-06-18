import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class _DashboardTest extends BaseTest {
    private static final By XPATH_DISAPPEARING_BUTTON = By.xpath("//div[@id='menuSelector']");
    private final static String DASHBOARD_XPATH = "//a[contains(text(),'Dashboard')]";
    private final static String JOB_INPUT_NAME_ID = "name";
    private final static String EV_JOB_NAME = "First Job";
    private final static String NEW_ITEM_LINK_TEXT = "New Item";
    private final static String PROJECT_TYPES = "Freestyle project";

    String[] ICONS_DESCRIPTIONS = {
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
            "Project health is 20% or less. You can hover the mouse over the project’s icon for a more detailed explanation."};

    @Test
    public void testCommonCheckDropDownMenu() {
        final List<String> expectedItems = List.of("New Item", "People", "Build History", "Manage Jenkins",
                "My Views", "Lockable Resources", "New View");

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DASHBOARD_XPATH)));
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver()
                .findElement(By.xpath(DASHBOARD_XPATH))).build().perform();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_DISAPPEARING_BUTTON));
        action.moveToElement(getDriver()
                .findElement(XPATH_DISAPPEARING_BUTTON)).click().build().perform();

        List<String> actualItems = getDriver().findElements(By.xpath("//ul[@class='first-of-type']/li"))
                .stream()
                .filter(s -> !s.getText().isEmpty())
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertEquals(actualItems, expectedItems);
    }

        public void clickOKButton() {
            getDriver().findElement(By.id("ok-button")).click();
        }

        public void createNewItem() {
            String jobName = EV_JOB_NAME;
            getDriver().findElement(By.linkText(NEW_ITEM_LINK_TEXT)).click();
            getDriver().findElement(By.id(JOB_INPUT_NAME_ID)).sendKeys(jobName);
            NewItemPageCategoryHoveringExperienceTest.clickProjectItem(getDriver(), PROJECT_TYPES);
            clickOKButton();
            getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        }

        @BeforeMethod
        public void deleteNewJob() {
        _FolderTest.deleteJobsWithPrefix(getDriver(), EV_JOB_NAME);
        }

        @Test
        public void testCheckLinkIconLegendEP() {
            createNewItem();
            getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
            WebElement iconLegendVisible = getDriver().findElement(By.xpath("//a[@href='/legend']"));

            Assert.assertTrue(iconLegendVisible.isDisplayed());

            getDriver().findElement(By.xpath("//a[@href='/legend']")).click();
            List<String> iconsTableDescriptions = getDriver().findElements(By.xpath("//table[@id='legend-table']//tbody/tr/td"))
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
            Assert.assertEquals(iconsTableDescriptions, List.of(ICONS_DESCRIPTIONS));

            getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
            getDriver().findElement(By.xpath("//span[text()='Build History']")).click();
            WebElement iconLegendVisiblePageBuildHistory = getDriver().findElement(By.xpath("//a[@href='/legend']"));

            Assert.assertTrue(iconLegendVisiblePageBuildHistory.isDisplayed());
        }
    }