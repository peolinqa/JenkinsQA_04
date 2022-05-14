import runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OlPolezhaevaTest extends BaseTest {

    @Test
    public void testFCKrasnodar(){

        getDriver().get("https://fckrasnodar.ru/ru/");

        WebElement academy = getDriver().findElement(By.cssSelector("li.parent.link-163>a"));
        academy.click();

        WebElement academyBranches = getDriver().findElement(By.xpath("//*[@id='page-163']/main/div/nav/ul/li[2]/a"));

        Assert.assertEquals(academyBranches.getText(), "ФИЛИАЛЫ АКАДЕМИИ");
    }
}
