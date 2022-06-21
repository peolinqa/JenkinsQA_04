import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

import java.util.List;

public class _HeaderTest extends BaseTest {

    private final By HEADER = By.id("header");

    public void verifyImageOrder(String uri){
        String currentUrl = getDriver().getCurrentUrl();
        if(uri != null){
            getDriver().get(currentUrl + uri);
        }
        List<WebElement> images = getDriver().findElements(By.xpath("//div[@class='logo']/a/img"));
        Assert.assertNotNull(images);
        Assert.assertEquals(images.size(), 2);
        Assert.assertEquals(images.get(0).getAttribute("id"), "jenkins-head-icon");
        Assert.assertEquals(images.get(1).getAttribute("id"), "jenkins-name-icon");
    }

    public void clickableLogo(String uri){
        String mainPage = getDriver().getCurrentUrl();
        if(uri != null){
            getDriver().get(mainPage + uri);
        }
        WebElement logoLink = getDriver().findElement(By.id("jenkins-home-link"));

        Assert.assertEquals(logoLink.getAttribute("href"), mainPage);
    }

    public void verifyLogo(String uri){
        String currentUrl = getDriver().getCurrentUrl();
        if(uri != null){
            getDriver().get(currentUrl + uri);
        }

        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertTrue(logo.getAttribute("src").contains("/images/svgs/logo.svg"));
    }

    @Test
    public void testHeaderLogoIsConsistentOnMainPage() { verifyImageOrder(null); }

    @Test
    public void testHeaderLogoIsConsistentOnNewItemPage(){
        verifyImageOrder("view/all/newJob");
    }

    @Test
    public void testHeaderLogoIsConsistentOnPeoplePage(){
        verifyImageOrder("asynchPeople/");
    }

    @Test
    public void testHeaderLogoIsConsistentOnManageJenkinsPage(){
        verifyImageOrder("manage");
    }
    @Test
    public void testHeaderLogoIsConsistentOnMyViewsPage() {
        verifyImageOrder("me/my-views/view/all/");
    }

    @Test
    public void testHeaderLogoIsConsistentOnNewViewPage() {
        verifyImageOrder("user/admin/my-views/newView");
    }

    @Test
    public void testHeaderLogoIsClickableOnPageToHomepage(){
        clickableLogo(null);
    }

    @Test
    public void testHeaderLogoIsClickableFromNewItemPageToHomepage(){
        clickableLogo("view/all/newJob");
    }

    @Test
    public void testHeaderLogoIsClickableFromPeoplePageToHomepage(){
        clickableLogo("asynchPeople/");
    }

    @Test
    public void testHeaderLogoIsClickableFromManageJenkinsPageToHomepage(){
        clickableLogo("manage");
    }

    @Test
    public void testLogoIsViewedOnMainPage() { verifyLogo(null); }

    @Test
    public void testLogoIsViewedOnNewItemPage() {
        verifyLogo("view/all/newJob");
    }

    @Test
    public void testLogoIsViewedOnPeoplePage() {
        verifyLogo("asynchPeople/");
    }

    @Test
    public void testLogoIsViewedOnManageJenkinsPage() {
        verifyLogo("manage");
    }

    @Test
    public void testLogoIsViewedOnMyViewsPage() {
        verifyLogo("me/my-views/view/all/");
    }

    @Test
    public void testLogoIsViewedOnNewViewPage() {
        verifyLogo("user/admin/my-views/newView");
    }

    @Test
    public void testHeaderLogoIsImage() {
        Assert.assertEquals(getDriver().findElement(By.id("jenkins-head-icon")).getTagName(), "img");
    }

    @Test
    public void testHeaderLogoImageExtensionIsSvg() {
        Assert.assertTrue(getDriver().findElement(By.id("jenkins-head-icon")).getAttribute("src").contains(".svg"));
    }

    @Test
    public void testHeaderDesignUI(){
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("background-color"), "rgba(0, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("display"), "flex");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("height"), "56px");
        Assert.assertEquals(getDriver().findElement(HEADER).getCssValue("align-items"), "center");
    }

    @Test
    public void testHeaderPositionOfElementsUI(){
        List<WebElement> divHeaders = getDriver().findElements(By.xpath("//header/div"));

        SoftAssert asserts = new SoftAssert();
        asserts.assertNotNull(divHeaders);
        asserts.assertEquals(divHeaders.size(), 3);
        asserts.assertEquals(divHeaders.get(0).getAttribute("class"), "page-header__brand");
        asserts.assertEquals(divHeaders.get(1).getAttribute("class"), "searchbox hidden-xs");
        asserts.assertEquals(divHeaders.get(2).getAttribute("class"), "login page-header__hyperlinks");

        List<WebElement> logoImgHeaders = getDriver().findElements(By.xpath("//a[@id='jenkins-home-link']/img"));
        asserts.assertNotNull(logoImgHeaders);
        asserts.assertEquals(logoImgHeaders.size(), 2);
        asserts.assertEquals(logoImgHeaders.get(0).getAttribute("id"), "jenkins-head-icon");
        asserts.assertEquals(logoImgHeaders.get(1).getAttribute("id"), "jenkins-name-icon");

        List<WebElement> divHeaderHyperlinks = getDriver().findElements(By.xpath(
                "//header/div[@class='login page-header__hyperlinks']/div"));
        asserts.assertNotNull(divHeaderHyperlinks);
        asserts.assertEquals(divHeaderHyperlinks.size(), 2);
        asserts.assertEquals(divHeaderHyperlinks.get(0).getAttribute("id"), "visible-am-insertion");
        asserts.assertEquals(divHeaderHyperlinks.get(1).getAttribute("id"), "visible-sec-am-insertion");

        List<WebElement> aHeaderHyperlinks = getDriver().findElements(By.xpath(
                "//header/div[@class='login page-header__hyperlinks']/a"));
        asserts.assertNotNull(aHeaderHyperlinks);
        asserts.assertEquals(aHeaderHyperlinks.size(), 2);
        asserts.assertEquals(aHeaderHyperlinks.get(0).getAttribute("href"),
                getDriver().getCurrentUrl() + "user/admin");
        asserts.assertEquals(aHeaderHyperlinks.get(1).getAttribute("href"),
                getDriver().getCurrentUrl() + "logout");

        asserts.assertAll();
    }
}
