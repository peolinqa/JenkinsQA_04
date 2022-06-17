import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class _HeaderTest extends BaseTest {

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
}
