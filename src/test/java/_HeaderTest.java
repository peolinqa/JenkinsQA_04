import org.openqa.selenium.By;
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

    private static List<WebElement> getMenuItems(WebDriver driver){
       return TestUtils.getList(driver,By.xpath("//div[@class='task ']//a"));
    }

    public void verifyPositionOfElements(By locator, String attribute, String... expectedResult) {
        List<WebElement> elementList = TestUtils.getList(getDriver(), locator);

        SoftAssert asserts = new SoftAssert();
        asserts.assertNotNull(elementList);
        asserts.assertEquals(elementList.size(), expectedResult.length);

        for(int i = 0; i < expectedResult.length; i++){
            asserts.assertEquals(elementList.get(i).getAttribute(attribute), expectedResult[i]);
        }
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnAllPages() {
        getMenuItems(getDriver());
        for (int i = 1; i <= getMenuItems(getDriver()).size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();

            Assert.assertTrue(getDriver().findElement(HEADER).isDisplayed());
            Assert.assertEquals(getDriver().findElement(HEADER).getLocation().toString(), "(0, 0)");
            getDriver().navigate().back();
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
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("background-color"), "rgba(0, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("display"), "flex");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("height"), "56px");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("align-items"), "center");
    }

    @Test
    public void testHeaderPositionOfElementsUI() {
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
                    getDriver().getCurrentUrl() + "user/admin", getDriver().getCurrentUrl() + "logout");

            getDriver().navigate().back();
        }
    }

}
