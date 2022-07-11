import model.HomePage;
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
        String title = new HomePage(getDriver())
                .getJenkinsIOPageTitle();
        Assert.assertEquals(title, "Jenkins");
    }
}
