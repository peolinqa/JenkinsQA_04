
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AndreyDTest extends BaseTest {

    @Test
    public void test(){

        getDriver().get("https://ebay.com");
        getDriver().findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys("coffee maker");
        getDriver().findElement(By.xpath("//*[@id=\"gh-btn\"]")).click();
        String res = getDriver().findElement(By.xpath("//*[contains(text(),'Keurig K130 B130 In Room Brewing System Coffee Maker')]")).getText();
        System.out.println("res:");
        System.out.println(res);
        Assert.assertEquals(1,1);
    }
}
