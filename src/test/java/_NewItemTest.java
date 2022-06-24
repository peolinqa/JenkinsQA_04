import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.List;

public class _NewItemTest extends BaseTest {

    private static final String DESCRIPTION_FIELD = "description";
    private static final String GITHUB_URL = "_.projectUrlStr";
    private static final String DESCRIPTION_INPUT = "New Project created by TA";
    private static final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";

    public void copyFromFreestyleProject(String nameNew, String nameCopyFrom) {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(By.id("name")).sendKeys(nameNew);
        ProjectUtils.Dashboard.NewItem.FreestyleProject.click(getDriver());
        WebElement copFromButton = getDriver().findElement(By.id("from"));
        new Actions(getDriver()).pause(500).moveToElement(copFromButton).perform();
        copFromButton.sendKeys(nameCopyFrom);
        ProjectUtils.clickOKButton(getDriver());
    }

    @Test
    public void testCopyDataFromExistingItemNegative() {
        ProjectUtils.createFreestyleProjectWithRandomName(getDriver());

        copyFromFreestyleProject("NJ3", "NJ4");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");
    }

    @Test
    public void testCopyDataFromExistingItemPositive() {
        ProjectUtils.createFreestyleProjectWithName(getDriver(), "NJ");
        ProjectUtils.openProject(getDriver(), "NJ");
        ProjectUtils.Dashboard.Project.Configure.click(getDriver());

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name(DESCRIPTION_FIELD))).sendKeys(DESCRIPTION_INPUT);
        getDriver().findElement(By.name("githubProject")).click();
        getDriver().findElement(By.name(GITHUB_URL)).sendKeys(URL_INPUT);
        ProjectUtils.clickSaveButton(getDriver());

        ProjectUtils.clickDashboard(getDriver());
        copyFromFreestyleProject("NJ2", "NJ");
        ProjectUtils.openProject(getDriver(), "NJ2");
        ProjectUtils.Dashboard.Project.Configure.click(getDriver());

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(getDriver().findElement(By.name(DESCRIPTION_FIELD)).getText(), DESCRIPTION_INPUT);
        asserts.assertEquals(getDriver().findElement(By.name(GITHUB_URL)).getAttribute("value"), URL_INPUT);
        asserts.assertAll();
    }

    @Test
    public void testCheckLabelStyle() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        List<WebElement> labels = TestUtils.getList(getDriver(),By.xpath("//li/label"));

        for (WebElement value : labels) {
            SoftAssert asserts = new SoftAssert();
            asserts.assertEquals(value.getCssValue("font-weight"), "700");
            asserts.assertEquals(value.getCssValue("font-size"), "16px");
            asserts.assertEquals(value.getCssValue("color"), "rgba(51, 51, 51, 1)");
            asserts.assertAll();
        }
    }

    @Test
    public void testCheckDescriptionStyle() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        List<WebElement> descriptions = TestUtils.getList(getDriver(),By.xpath("//div[@class='desc']"));
        for (WebElement value : descriptions) {
            SoftAssert asserts = new SoftAssert();
            asserts.assertEquals(value.getCssValue("font-size"), "14px");
            asserts.assertEquals(value.getCssValue("color"), "rgba(51, 51, 51, 1)");
            asserts.assertAll();
        }
    }

    @Test
    public void testCheckIconAvailabilityDisplaying() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        List<WebElement> icons = TestUtils.getList(getDriver(),By.xpath("//div[@class='icon']/img"));
        for (WebElement icon : icons) {
            SoftAssert asserts = new SoftAssert();
            asserts.assertTrue(icon.isDisplayed());
            asserts.assertTrue(icon.isEnabled());
            asserts.assertAll();
        }
    }

    @Test
    public void testCheckLabelDisplayingOnNewItemPage() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        String[] expectedIcons = {
                "Freestyle project",
                "Pipeline",
                "Multi-configuration project",
                "Folder",
                "Multibranch Pipeline",
                "Organization Folder",
                };

        List<WebElement> iconsText = getDriver().findElements(By.xpath("//li[@role='radio']/label/span[@class='label']"));
        for(int i = 0; i < expectedIcons.length; i++){
            Assert.assertEquals(iconsText.get(i).getText(), expectedIcons[i]);
        }
    }

    @Test
    public void testOKButtonErrorDisplaying() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(By.cssSelector("div.btn-decorator")).click();

        String actualError = getDriver().findElement(
                By.cssSelector("#itemname-required.input-validation-message")).getText();

        Assert.assertEquals(actualError, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testCheckBreadcrumbs() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        List<WebElement> breadcrumbs = TestUtils.getList(getDriver(),By.xpath("//ul[@id='breadcrumbs']/li"));

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(getDriver().getTitle(), "New Item [Jenkins]");
        asserts.assertEquals(breadcrumbs.get(0).getText(), "Dashboard");
        asserts.assertEquals(breadcrumbs.get(2).getText(), "All");
        asserts.assertAll();
    }
}
