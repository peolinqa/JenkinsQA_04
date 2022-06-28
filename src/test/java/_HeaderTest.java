import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.List;

public class _HeaderTest extends BaseTest {

    private static final By HEADER = By.id("header");
    private static final By HEADER_ICON = By.id("jenkins-head-icon");
    private static final By MENU_SELECTOR_XPATH = By.cssSelector(".login");
    private static final SoftAssert ASSERTS = new SoftAssert();

    private static List<WebElement> getMenuItems(WebDriver driver){
       return TestUtils.getList(driver,By.xpath("//div[@class='task ']//a"));
    }

    public void verifyPositionOfElements(By locator, String attribute, String... expectedResult) {
        List<WebElement> elementList = TestUtils.getList(getDriver(), locator);

        ASSERTS.assertNotNull(elementList);
        ASSERTS.assertEquals(elementList.size(), expectedResult.length);

        for(int i = 0; i < expectedResult.length; i++){
            ASSERTS.assertEquals(elementList.get(i).getAttribute(attribute), expectedResult[i]);
        }
        ASSERTS.assertAll();
    }

    private void menuSelector(WebDriver driver) {

        getActions().moveToElement(getDriver().findElement(MENU_SELECTOR_XPATH)).perform();

        driver.findElement(By.cssSelector("div[id='menuSelector']")).click();
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnAllPages() {
        getMenuItems(getDriver());
        for (int i = 1; i <= getMenuItems(getDriver()).size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();

            ASSERTS.assertTrue(getDriver().findElement(HEADER).isDisplayed());
            ASSERTS.assertEquals(getDriver().findElement(HEADER).getLocation().toString(), "(0, 0)");
            getDriver().navigate().back();
            ASSERTS.assertAll();
        }
    }

    @Test
    public void testVerifyImageOrderOnAllPages() {
        getMenuItems(getDriver());
        for (int i = 1; i <= getMenuItems(getDriver()).size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();
            verifyPositionOfElements(By.xpath("//div[@class='logo']/a/img"), "id",
                    "jenkins-head-icon", "jenkins-name-icon");
            getDriver().navigate().back();
        }
    }

    @Test
    public void testHeaderLogoIsClickableOnAllPagesToHomepage(){
        String currentUrl = getDriver().getCurrentUrl();
        getMenuItems(getDriver());
        for (int i = 1; i <= getMenuItems(getDriver()).size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();

            Assert.assertEquals(getDriver().findElement(By.id("jenkins-home-link")).getAttribute("href"), currentUrl);
            getDriver().navigate().back();
        }
    }

    @Test
    public void testLogoIsViewedOnAllPages() {
        getMenuItems(getDriver());
        for (int i = 1; i <= getMenuItems(getDriver()).size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();
            WebElement logo = getDriver().findElement(HEADER_ICON);
            Assert.assertTrue(logo.getAttribute("src").contains("/images/svgs/logo.svg"));
            getDriver().navigate().back();
        }
    }

    @Test
    public void testHeaderLogoIsImage() {
        Assert.assertEquals(getDriver().findElement(HEADER_ICON).getTagName(), "img");
    }

    @Test
    public void testHeaderLogoImageExtensionIsSvg() {
        Assert.assertTrue(getDriver().findElement(HEADER_ICON).getAttribute("src").contains(".svg"));
    }

    @Test
    public void testHeaderDesignUI(){
        ASSERTS.assertEquals(getDriver().findElement(HEADER).getCssValue("background-color"), "rgba(0, 0, 0, 1)");
        ASSERTS.assertEquals(getDriver().findElement(HEADER).getCssValue("display"), "flex");
        ASSERTS.assertEquals(getDriver().findElement(HEADER).getCssValue("height"), "56px");
        ASSERTS.assertEquals(getDriver().findElement(HEADER).getCssValue("align-items"), "center");
        ASSERTS.assertAll();
    }

    @Test
    public void testHeaderPositionOfElementsUI() {
        String currentUrl = getDriver().getCurrentUrl();
        getMenuItems(getDriver());
        for (int i = 1; i <= getMenuItems(getDriver()).size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();

            verifyPositionOfElements(By.xpath("//header/div"), "class",
                    "page-header__brand", "searchbox hidden-xs", "login page-header__hyperlinks");

            verifyPositionOfElements(By.xpath("//div[@class='logo']/a/img"), "id",
                    "jenkins-head-icon", "jenkins-name-icon");

            verifyPositionOfElements(By.xpath("//header/div[@class='login page-header__hyperlinks']/div"), "id",
                    "visible-am-insertion", "visible-sec-am-insertion");

            verifyPositionOfElements(By.xpath("//header/div[@class='login page-header__hyperlinks']/a"), "href",
                      currentUrl + "user/admin", currentUrl + "logout");

            getDriver().navigate().back();
        }
    }

    @Test
    public void testCheckSearchPanel() {

        TestUtils.clearAndSend(getDriver(), By.id("search-box"), "TryToFindSomething" + Keys.ENTER);

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                "Search for 'TryToFindSomething'");
    }

    @Test
    public void testCheckAdminPage() {

        getDriver().findElement(MENU_SELECTOR_XPATH).click();

        Assert.assertTrue(getDriver().findElement(By.cssSelector("#main-panel > div:nth-child(4)")).getText().contains("Jenkins User ID:"));
    }

    @Test
    public void testCheckExpandMenuBuilds() {

        menuSelector(getDriver());
        ProjectUtils.Dashboard.Header.Builds.click(getDriver());

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText().contains("Builds for"));
    }

    @Test
    public void testCheckExpandMenuConfigure() {

        menuSelector(getDriver());
        ProjectUtils.Dashboard.Header.Configure.click(getDriver());

        Assert.assertTrue(getDriver().findElement(By.id("yui-gen2-button")).getText().contains("Add new Token"));
    }

    @Test
    public void testCheckExpandMenuMyViews() {

        menuSelector(getDriver());
        ProjectUtils.Dashboard.Header.MyViews.click(getDriver());

        Assert.assertTrue(getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[5]")).getText().contains("My Views"));
    }
}
