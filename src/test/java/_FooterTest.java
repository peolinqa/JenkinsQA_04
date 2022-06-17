import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FooterTest extends BaseTest {

    public static final String FOOTER_REST_API = "//a[@href='api/']";
    public static final String REST_API_PAGE_URL = "http://localhost:8080/api/";

    @Test
    public void testFooterCheckLinksRestApi() {
        getDriver().findElement(By.xpath(FOOTER_REST_API)).click();
        Assert.assertEquals(getDriver().getCurrentUrl(), REST_API_PAGE_URL);
    }
}
