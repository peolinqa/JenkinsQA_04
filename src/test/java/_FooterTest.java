import model.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FooterTest extends BaseTest {

    @Test
    public void testFooterCheckLinksRestApi(){
       String  footerCheckLinksRestApi = new HomePage(getDriver())
               .goToApiPage()
               .getFooterApiText();
       Assert.assertEquals(footerCheckLinksRestApi,"REST API");
    }

    @Test
    public void testFooterCheckLinksJenkinsVersion() {

        Assert.assertTrue(getDriver().findElement(By.xpath("//a[@rel='noopener noreferrer']")).isDisplayed());

    }
}
