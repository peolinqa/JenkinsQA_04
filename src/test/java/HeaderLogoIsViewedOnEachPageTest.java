import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HeaderLogoIsViewedOnEachPageTest extends BaseTest {


    public void verifyLogo(String uri){
        String currentUrl = getDriver().getCurrentUrl();
        if(uri != null){
            getDriver().get(currentUrl + uri);
        }

        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertEquals(logo.getAttribute("src"), currentUrl + "static/51d19e46/images/svgs/logo.svg");

    }

    @Test
    public void testLogoIsViewedOnMainPage(){
        verifyLogo(null);

    }

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
}
