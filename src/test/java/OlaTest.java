import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class OlaTest extends BaseTest  {
    @Test
    public void testMvnRepositoryHomePageFindText() throws InterruptedException {

        getDriver().get("https://mvnrepository.com/");
        Thread.sleep(2000);
        WebElement h1 = getDriver().findElement(By.xpath("//div[@id='maincontent']/h1"));
        String actualResult = h1.getText();

        Assert.assertEquals(actualResult, "What's New in Maven");
    }
}
