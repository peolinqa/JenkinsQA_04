import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FooterTest extends BaseTest {

    @Test
    public void testFooterCheckLinksRestApi() {
        getDriver().findElement(By.xpath("//a[@href='api/']")).click();
        Assert.assertEquals(getDriver().getTitle(), "Remote API [Jenkins]");
    }
}
