import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TatauchTest extends BaseTest {

    @Test

    public void tatsianaAuchynnikTest() throws InterruptedException {

        getDriver().get("https://www.selenium.dev/");
        WebElement readMoreButton = getDriver().findElement(By.xpath("//*[@class='nav-link']"));
        readMoreButton.click();
        getDriver().get("https://www.selenium.dev/documentation/webdriver/");
        Thread.sleep(500);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.selenium.dev/documentation/webdriver/");
    }
}
