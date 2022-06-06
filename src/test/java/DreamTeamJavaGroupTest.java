import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DreamTeamJavaGroupTest extends BaseTest {

    public static final String FOOTER_REST_API = "//div[@class='page-footer__links rest_api hidden-xs']";
    public static final String REST_API_PAGE_HEADER = "//div[@id='main-panel']/h1";

    @Test
    public void testTC_132_001_FooterCheckLinksFelix_IX() {

        getDriver().findElement(By.xpath(FOOTER_REST_API)).click();
        Assert.assertEquals(getDriver().findElement(By.xpath(REST_API_PAGE_HEADER)).getText(), "REST API");
    }
}
