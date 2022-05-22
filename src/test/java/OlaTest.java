import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class OlaTest extends BaseTest  {
    @Test
    public void testCheckEmail() {

        getDriver().get("https://www.99-bottles-of-beer.net/");
        WebElement privacy = getDriver().findElement(By.xpath("//ul[@id='submenu']/li[4]/a"));
        privacy.click();

        WebElement email = getDriver().findElement(By.xpath("//div[@id='main']/p"));
        String actualResult = email.getText();

        Assert.assertEquals(actualResult, "os@ls-la.net");
    }
}
