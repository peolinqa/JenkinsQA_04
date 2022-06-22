import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FooterTest extends BaseTest {

    @Test
    public void testFooterCheckLinksRestApi() {
        getDriver().findElement(By.xpath("//a[@href='api/']")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "REST API");
    }

    @Test
    public void testFooterCheckLinksJenkinsVersion() {

        Assert.assertTrue(getDriver().findElement(By.xpath("//a[@rel='noopener noreferrer']")).isDisplayed());

    }
}
