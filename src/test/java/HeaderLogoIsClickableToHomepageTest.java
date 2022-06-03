import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HeaderLogoIsClickableToHomepageTest extends BaseTest {

    public void clickableLogo(String uri){
        String mainPage = getDriver().getCurrentUrl();
        if(uri != null){
            getDriver().get(mainPage + uri);
        }

        WebElement logoLink = getDriver().findElement(By.id("jenkins-home-link"));

       Assert.assertEquals(logoLink.getAttribute("href"), mainPage);
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
}
