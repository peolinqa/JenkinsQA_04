import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class JuliaAnelTest extends BaseTest {

    @Test
    public void testSoundcloud () {

        getDriver().get("https://www.soundcloud.com");

        WebElement search = getDriver().findElement(By.xpath(
                "//span[@class='frontContent__search g-dark']//input[@name='q']"
        ));
        search.sendKeys("Kendrick Lamar");

        Assert.assertEquals(search.getAttribute("value"), "Kendrick Lamar");

        search.sendKeys(Keys.ENTER);
    }
}
