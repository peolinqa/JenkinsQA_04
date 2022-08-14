import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FooterTest extends BaseTest {

    @Test
    public void testCheckLinkRestApi() {
        String mainTitleCheckRestApi = new HomePage(getDriver())
                .goToApiPage()
                .getMainTitleFromApiPage();

        Assert.assertEquals(mainTitleCheckRestApi, "REST API");
    }

    @Test
    public void testCheckLinkJenkinsVersion() {
        String title = new HomePage(getDriver())
                .getJenkinsIOPageTitle();

        Assert.assertEquals(title, "Jenkins");
    }
}
