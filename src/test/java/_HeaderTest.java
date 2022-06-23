import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.TestUtils;

import java.util.List;

public class _HeaderTest extends BaseTest {

    private static final By HEADER = By.id("header");
    private static final By HEADER_ICON = By.id("jenkins-head-icon");

    public void verifyPositionOfElements(By locator, String attribute, String... expectedResult) {
        List<WebElement> elementList = TestUtils.getList(getDriver(), locator);

        SoftAssert asserts = new SoftAssert();
        asserts.assertNotNull(elementList);
        asserts.assertEquals(elementList.size(), expectedResult.length);

        for(int i = 0; i < expectedResult.length; i++){
            asserts.assertEquals(elementList.get(i).getAttribute(attribute), expectedResult[i]);
        }
    }

    public void verifyImageOrder(String uri){
        String currentUrl = getDriver().getCurrentUrl();
        if(uri != null){
            getDriver().get(currentUrl + uri);
        }
        verifyPositionOfElements(By.xpath("//div[@class='logo']/a/img"), "id",
                "jenkins-head-icon","jenkins-name-icon");
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

        WebElement logo = getDriver().findElement(HEADER_ICON);

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
    public void testHeaderPositionOfElementsUI(){
        verifyPositionOfElements(By.xpath("//header/div"), "class",
                "page-header__brand","searchbox hidden-xs","login page-header__hyperlinks");
        verifyPositionOfElements(By.xpath("//div[@class='logo']/a/img"), "id",
                "jenkins-head-icon","jenkins-name-icon");

        verifyPositionOfElements(By.xpath("//header/div[@class='login page-header__hyperlinks']/div"),"id",
                "visible-am-insertion", "visible-sec-am-insertion");

        verifyPositionOfElements(By.xpath("//header/div[@class='login page-header__hyperlinks']/a"),"href",
                getDriver().getCurrentUrl() + "user/admin", getDriver().getCurrentUrl() + "logout");
    }
}
